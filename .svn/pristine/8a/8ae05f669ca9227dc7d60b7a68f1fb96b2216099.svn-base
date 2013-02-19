/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package communication;

import java.util.ArrayList;
import java.util.TimerTask;

/**
 * 
 * @author shuai
 */
public class SNRQuery extends TimerTask{
    private Communication comm;
    
    public SNRQuery(Communication comm){
        this.comm = comm;
    }

    @Override
    public void run() {
//        throw new UnsupportedOperationException("Not supported yet.");
        sendQuery();
    }
    
    public void sendQuery(){
        ArrayList<String> plist = comm.getPermittedSites();
        for(String s: plist){
            comm.sendSNRQuery(s);
        }
    }

}
