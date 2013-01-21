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
public class BizBoardQuery extends TimerTask{
    private Communication comm;
    
    public BizBoardQuery(Communication comm){
        this.comm = comm;
    }
    
    @Override
    public void run() {
        String query = "F7:01";
        comm.sendBizBoardQuery(query);
    }
//    public void BizBoardConf(String fp, String bd, String po, String ca){
//        comm.sendBizBoardQuery(ca);
//    }

}
