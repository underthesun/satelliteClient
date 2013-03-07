/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 */
package communication;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.ClientJFrame;
import utils.Constants;

/**
 *
 * @author martin
 */
public class Communication {

    private Constants constant;
    private boolean isConnected = false;
    private static String serverIp;
    private static int serverPort;
    private static int serverLoginPort;
    private static int serverMessagePort;
    private static String bizBoardIP;
    private static int bizBoardPort;
    private String clientId;
    private static int clientPort;
    private DatagramSocket clientSocket = null;
    private ClientJFrame clientFrame;
    private KeepConnection keepConnection;
    private SNRQuery snrQuery;
    private SNRReport snrReport;

//    private static int count = 0;
//    private static int countConfirmed = -1;
    public Communication(ClientJFrame c) {
        clientFrame = c;
        loadConstants();
        while (true) {
            try {
                clientSocket = new DatagramSocket(clientPort);
                System.out.println("bind port: " + clientPort);
                break;
            } catch (IOException ioe) {
                ioe.printStackTrace();
                clientPort++;
            }
        }
        keepConnection = new KeepConnection(this, clientId);
        snrQuery = new SNRQuery(this);
        snrReport = new SNRReport(this);
        Timer t = new Timer();
        t.schedule(keepConnection, 0, 3000);
        t.schedule(snrQuery, 0, 1000 * constant.getFpQuerySNR());
        t.schedule(snrReport, 0, 1000 * constant.getFpReportSNR());
        new Thread(new MessageServer(clientSocket, this)).start();
    }

    private void loadConstants() {
        constant = clientFrame.getConstants();
        serverIp = constant.getRemoteIP();
        serverPort = constant.getRemotePort();
        clientId = constant.getId();
//        serverLoginPort = constant.getRemoteLoginPort();
//        serverMessagePort = constant.getRemoteMessagePort();
        bizBoardIP = constant.getBizBoardIP();
        bizBoardPort = constant.getBizBoardPort();
        clientPort = constant.getLocalPort();
    }

    public void setConnectedState(boolean b) {
        isConnected = b;
    }

    public boolean getConnectedState() {
        return isConnected;
    }

    public void connectServer() {
        String data = "connect:" + clientId;
        sendPacket(data, serverIp, serverPort);
    }

    public void sendKeep(String str) {
        sendPacket(str, serverIp, serverPort);
    }

    public void sendRequest(String str) {
        sendPacket(str, serverIp, serverPort);
    }

    public void sendMessage(String str) {
        sendPacket(str, serverIp, serverPort);
    }

    public void sendBizBoardQuery() {
        String str = "F7:01";
        sendPacket(str, bizBoardIP, bizBoardPort);
    }

    public void sendBizBoardConf(String str) {
        sendPacket(str, bizBoardIP, bizBoardPort);
    }

    public void sendSNRQuery() {
        String id = clientFrame.getSNRQueryingId();
        if (!id.equals("")) {
            String str = "snr:" + clientId + ":1:" + id;
            sendPacket(str, serverIp, serverPort);
            System.out.println("send-->" + str);
        }
    }

    public void reportSNR() {
        if (!constant.getSigNoiseLocal().equals("")) {
            String str = "snr:" + clientId + ":0:" + constant.getSigNoiseLocal();
            sendPacket(str, serverIp, serverPort);
        }
    }

    public void sendPacket(String str, String ip, int port) {
//        byte[] data = str.getBytes();
        try {
            ByteArrayOutputStream ostream = new ByteArrayOutputStream();
            DataOutputStream dataStream = new DataOutputStream(ostream);
            dataStream.writeUTF(str);
            dataStream.close();
            byte[] data = ostream.toByteArray();
            DatagramPacket dp = new DatagramPacket(data, data.length, InetAddress.getByName(ip), port);
            clientSocket.send(dp);
        } catch (IOException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void parseMessage(String data) {
        int indexOfColon = data.indexOf(":");
        String command = data.substring(0, indexOfColon);
        String str = data.substring(indexOfColon + 1);

        if (command.equals("connect")) {
            if (str.equals("ok")) {
                isConnected = true;
                clientFrame.connected();
                keepConnection.resetCount();
            }
        } else if (command.equals("keep")) {
            int c = Integer.parseInt(str);
            keepConnection.setConfirmedCount(c);
        } else if (command.equals("request")) {
            // System.out.println(data);
            String[] strs = str.split(":");

            String id = "";
            //String idCalled = "";
            String type = strs[1];
//            String serial = strs[2];
            //String s = str.substring(str.indexOf(":") + 1);
//            System.out.println("mb,running here");
            if (type.equals("5")) {
                id = strs[2];
            } else {
                id = strs[0];
            }

            if (id.equals(constant.getId())) {
                //System.out.println("id: " + id);
                //System.out.println(str);
//                clientFrame.processBizEcho(type, serial);
                clientFrame.processBizEcho(str);
            }
        } else if (command.equals("message")) {
            clientFrame.appendMessage(str);
        } else if (command.equals("snr")) {
            String[] strs = str.split(":");
            String idQuerying = strs[0];
            String idQueryed = strs[1];
            String snr = strs[2];
            if (idQuerying.equals(constant.getId())) {
                clientFrame.updateSNR(idQueryed, snr);
            }
        } else if (command.equals("F7")) {
            String[] strs = str.split(":");
            if (strs[0].equals("01")) {
                String snr = strs[1];
                constant.setSigNoiseLocal(snr);
                clientFrame.updateSNR(snr);
            }
        }
    }

    public void unconnected() {
        clientFrame.unconnected();
    }
}
