/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.util.TimerTask;

/**
 *
 * @author shuai
 */
public class KeepConnection extends TimerTask {

    private Communication comm;
    private String clientId;
    private static int count = 0;
    private static int countConfirmed = -1;
    private static int serverLoginPort = 6666;

    public KeepConnection(Communication c, String id) {
        comm = c;
        clientId = id;
    }

    public void setConfirmedCount(int c) {
        countConfirmed = c;
    }
    
    public void resetCount(){
        countConfirmed = count;
    }

    @Override
    public void run() {
//        throw new UnsupportedOperationException("Not supported yet.");

        if (count != countConfirmed && comm.getConnectedState()) {
            comm.unconnected();
            comm.setConnectedState(false);
        }
        if (comm.getConnectedState()) {
            String str = "keep:" + clientId + ":" + count;
            count++;
            comm.sendKeep(str);
        } else {
            comm.connectServer();
        }
    }
}
