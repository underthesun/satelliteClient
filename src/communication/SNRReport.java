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
public class SNRReport extends TimerTask{
    private Communication comm;
    
    public SNRReport(Communication comm){
        this.comm = comm;
    }

    @Override
    public void run() {
        comm.reportSNR();
    }
}
