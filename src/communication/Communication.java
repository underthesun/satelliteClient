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

/**
 *
 * @author martin
 */
public class Communication {

    private boolean isConnected = false;
    private String serverIp;
    private static int serverLoginPort = 6666;
    private static int serverMessagePort = 6667;
    private String clientId;
    private static int clientPort = 6668;
    private DatagramSocket clientSocket = null;
    private ClientJFrame clientFrame;
    private KeepConnection keepConnection;
//    private static int count = 0;
//    private static int countConfirmed = -1;

    public Communication(ClientJFrame c) {
        clientFrame = c;
        serverIp = clientFrame.getServerIp();
        clientId = clientFrame.getClientId();

        while (true) {
            try {
                clientSocket = new DatagramSocket(clientPort);
                System.out.println("bind port: " + clientPort);
                break;
            } catch (IOException ioe) {
//                ioe.printStackTrace();
                clientPort++;
            }
        }

        keepConnection = new KeepConnection(this, clientId);
        Timer t = new Timer();
        t.schedule(keepConnection, 0, 3000);
        new Thread(new MessageReceive(clientSocket, this)).start();
    }

    public void setConnectedState(boolean b) {
        isConnected = b;
    }

    public boolean getConnectedState() {
        return isConnected;
    }

    public void connectServer() {
        String data = "connect:" + clientId;
        sendPacket(data, serverLoginPort);
    }

    public void sendKeep(String str) {
        sendPacket(str, serverLoginPort);
    }

    public void sendRequest(String str) {
        sendPacket(str, serverMessagePort);
    }

    public void sendMessage(String str) {
        sendPacket(str, serverMessagePort);
    }

    public void sendPacket(String str, int port) {
        byte[] data = str.getBytes();
        try {
            DatagramPacket dp = new DatagramPacket(data, data.length, InetAddress.getByName(serverIp), port);
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
            String[] strs = str.split(":");
            String type = strs[0];
            String serial = strs[1];
            clientFrame.processBizEcho(type, serial);
        } else if (command.equals("message")) {
            clientFrame.appendMessage(str);
        }
    }

    public void unconnected() {
        clientFrame.unconnected();
    }
}
