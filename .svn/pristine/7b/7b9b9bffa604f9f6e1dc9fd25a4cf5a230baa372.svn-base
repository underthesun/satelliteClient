/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package communication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author shuai
 */
public class MessageReceive implements Runnable{
    DatagramSocket socket = null;
    Communication comm;
    DatagramPacket dp = null;
    byte[] buffer = null;
    int bufLength =1024;
    
    public MessageReceive(DatagramSocket socket, Communication c){
        this.socket = socket;
        this.comm = c;
        buffer = new byte[bufLength];
        dp = new DatagramPacket(buffer, bufLength);
    }
    
    public void run(){
        while(true){
            try {
                socket.receive(dp);
                String data = new String(buffer, 0, dp.getLength());
//                System.out.println("Received: "+data);
                comm.parseMessage(data);
            } catch (IOException ex) {
                Logger.getLogger(MessageReceive.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
