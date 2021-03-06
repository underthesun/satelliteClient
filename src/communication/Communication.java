/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 */
package communication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
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
    private static int serverLoginPort;
    private static int serverMessagePort;
    private static String bizBoardIP;
    private static int bizBoardPort;
    private String clientId;
    private static int clientPort;
    private DatagramSocket clientSocket = null;
    private ClientJFrame clientFrame;
    private KeepConnection keepConnection;
    private BizBoardQuery bizBoardQuery;
    
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
        bizBoardQuery = new BizBoardQuery(this);
        Timer t = new Timer();
        t.schedule(keepConnection, 0, 3000);
        t.schedule(bizBoardQuery, 0, 2000);
        new Thread(new MessageReceive(clientSocket, this)).start();
    }

    private void loadConstants() {
        constant = clientFrame.getConstants();
        serverIp = constant.getRemoteIP();
        clientId = constant.getId();
        serverLoginPort = constant.getRemoteLoginPort();
        serverMessagePort = constant.getRemoteMessagePort();
        bizBoardIP = constant.getBizBoardIP();
        bizBoardPort = constant.getBizBoardPort();
        clientId = constant.getId();
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
        sendPacket(data, serverIp, serverLoginPort);
    }

    public void sendKeep(String str) {
        sendPacket(str, serverIp, serverLoginPort);
    }

    public void sendRequest(String str) {
        sendPacket(str, serverIp, serverMessagePort);
    }

    public void sendMessage(String str) {
        sendPacket(str, serverIp, serverMessagePort);
    }

    public void sendBizBoardQuery(String str) {
//        System.out.println("Biz:"+str);
        sendPacket(str, bizBoardIP, bizBoardPort);
    }

    public void sendPacket(String str, String ip, int port) {
        byte[] data = str.getBytes();
        try {
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
            System.out.println(data);
            String[] strs = str.split(":");
            String id = strs[0];
//            String type = strs[1];
//            String serial = strs[2];
            String s = str.substring(str.indexOf(":") + 1);
            if (id.equals(constant.getId())) {
                System.out.println("id: " + id);
                System.out.println(s);
//                clientFrame.processBizEcho(type, serial);
                clientFrame.processBizEcho(s);
            }
        } else if (command.equals("message")) {
            clientFrame.appendMessage(str);
        } else if (command.equals("F7")) {
            String[] strs = str.split(":");
            if (strs[0].equals("03")) {
                sendBizBoardQuery(data);
            } else if (strs[0].equals("01")) {
                int snr = Integer.parseInt(strs[1]);
                constant.setSigNoiseLocal(snr);
                clientFrame.updateSNR(snr);
            }
        }
    }

    public void unconnected() {
        clientFrame.unconnected();
    }
}
