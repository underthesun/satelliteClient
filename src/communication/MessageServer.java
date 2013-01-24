/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shuai
 */
public class MessageServer implements Runnable {

    DatagramSocket socket = null;
    Communication comm;
    DatagramPacket dp = null;
    byte[] buffer = null;
    int bufLength = 1024;

    public MessageServer(DatagramSocket socket, Communication c) {
        this.socket = socket;
        this.comm = c;
        buffer = new byte[bufLength];
        dp = new DatagramPacket(buffer, bufLength);
    }

    public void run() {
        while (true) {
            try {
                socket.receive(dp);
//                System.out.println("Received: "+data);
                DataInputStream istream = new DataInputStream(new ByteArrayInputStream(dp.getData()));
//                String data = new String(buf, 0, dataReceived.getLength(),"utf-8");
                String data = istream.readUTF();
                comm.parseMessage(data);
            } catch (IOException ex) {
                Logger.getLogger(MessageServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
