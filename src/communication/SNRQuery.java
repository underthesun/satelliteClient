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
public class SNRQuery extends TimerTask {

    private Communication comm;

    public SNRQuery(Communication comm) {
        this.comm = comm;
    }

    @Override
    public void run() {
        comm.sendBizBoardQuery();
        comm.sendSNRQuery();
    }
//    public void BizBoardConf(String fp, String bd, String po, String ca){
//        comm.sendBizBoardQuery(ca);
//    }
}
