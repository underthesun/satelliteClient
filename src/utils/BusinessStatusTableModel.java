/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.AbstractTableModel;
import ui.ClientJFrame;

/**
 *
 * @author martin
 */
public class BusinessStatusTableModel extends AbstractTableModel {

    private ClientJFrame clientFrame;
    private ArrayList<Business> busilist;
    private String columnNames[] = {"主叫", "被叫", "带宽", "信道1", "信道2", "入网状况", "VoIP路数", "视频业务", "传真路数", "互联网带宽", "天线口径", "功放大小", "更新时间", "运行状况"};

    public BusinessStatusTableModel(ClientJFrame clientFrame) {
        this.clientFrame = clientFrame;
        busilist = new ArrayList<Business>();
//        loadTestData();
    }

    public void addRecord(Business b) {
        busilist.add(b);
        fireTableDataChanged();
    }

    public String isCaller(){
        String flag = "02";
        for(Business b : busilist){
            if(b.getAppStatus().equals("正在申请")||b.getAppStatus().equals("已批准")){
                if(b.getStationIDCalling().equals(clientFrame.getConstants().getId())){
                    flag = "01";
                }
            }
        }
        return flag;
    }
    
    public String getBandwidth() {
        String result = null;
        for (Business b : busilist) {
            String s = b.getAppStatus();
            if (s.equals("正在申请")) {
                Pattern p = Pattern.compile("[^0-9]");
                String bd = b.getBandwidth();
                Matcher m = p.matcher(bd);
                result = m.replaceAll("");
            }
        }
        return result;
    }

    public void setRunningStatus(String status) {
        for (Business b : busilist) {
            String s = b.getAppStatus();
            if (s.equals("已批准") || s.equals("已警告") || s.equals("正在申请")) {
                b.setAppStatus(status);
                fireTableDataChanged();
            }
        }
    }

    public void setRunningStatus(int serial, String status) {
        for (Business b : busilist) {
            if (b.getSerial() == serial) {
                b.setAppStatus(status);
                fireTableDataChanged();
            }
        }
    }

    private void loadTestData() {
        busilist.add(new Business("2", "1", "512", "xxdb", "xxdb", "入网", "2", "视频业务", "2", "2m", "2", "2", "sss", "dsd"));
    }

    // public ArraryList<BusiQueue> getBusiList(){
    //}
    @Override
    public int getRowCount() {
        return busilist.size();
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object list = null;
        Business biz = busilist.get(rowIndex);
        switch (columnIndex) {
            case 0:
                list = biz.getStationIDCalling();
                break;
            case 1:
                list = biz.getStationIDCalled();
                break;
            case 2:
                list = biz.getBandwidth();
                break;
            case 3:
                list = biz.getSigNoiseRatio1();
                break;
            case 4:
                list = biz.getSignoiseRatio2();
                break;
            case 5:
                list = biz.getRuningStatus();
                break;
            case 6:
                list = biz.getVoIPNum();
                break;
            case 7:
                String s = biz.getVideoBusi();
                if (s.equals("0")) {
                    list = "0";
                } else if (s.equals("1")) {
                    list = "标清";
                } else if (s.equals("2")) {
                    list = "高清";
                }
                break;
            case 8:
                list = biz.getFaxNum();
                break;
            case 9:
                list = biz.getInterBandwidth();
                break;
            case 10:
                list = biz.getAntennaCaliber();
                break;
            case 11:
                list = biz.getAmplifier();
                break;
            case 12:
                list = biz.getFreshTime();
                break;
            case 13:
                list = biz.getAppStatus();
                break;
        }
        return list;

        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getColumnName(int columnIndex) {
        String str = columnNames[columnIndex];
        return str;
    }

    public void statusChanged() {
        this.fireTableDataChanged();
    }

    public void updateSNR(String snr) {
        for (Business b : busilist) {
            if (b.getStationIDCalling().equals(clientFrame.getConstants().getId())) {
                if (b.getAppStatus().equals("已批准") || b.getAppStatus().equals("已警告")) {
                    b.setSigNoiseRatio1(snr);
                }
            } else if (b.getStationIDCalled().equals(clientFrame.getConstants().getId())) {
                if (b.getAppStatus().equals("已批准") || b.getAppStatus().equals("已警告")) {
                    b.setSigNoiseRatio2(snr);
                }
            }
        }
        fireTableDataChanged();
    }

    public void updateSNR(String id, String snr) {
        for (Business b : busilist) {
            if (b.getStationIDCalled().equals(id)) {
                if (b.getAppStatus().equals("已批准") || b.getAppStatus().equals("已警告")) {
                    b.setSigNoiseRatio2(snr);
                }
            } else if (b.getStationIDCalling().equals(id)) {
                if (b.getAppStatus().equals("已批准") || b.getAppStatus().equals("已警告")) {
                    b.setSigNoiseRatio1(snr);
                }
            }
        }
        fireTableDataChanged();
    }
    
    public String getSNRQueryingId(){
        String id= "";
        for (Business b : busilist) {
            String s = b.getAppStatus();
            if (s.equals("已批准") || s.equals("已警告") ) {
                if(b.getStationIDCalling().equals(clientFrame.getConstants().getId())){
                    id = b.getStationIDCalled();
                }else{
                    id = b.getStationIDCalling();
                }
            }
        }
        return id;
    }

    public ArrayList<String> getPermitted() {
        ArrayList<String> plist = new ArrayList<String>();
        for (Business b : busilist) {
            if (b.getAppStatus().equals("已批准")) {
                plist.add(b.getStationIDCalled());
            }
        }
        return plist;
    }

    public void bizReset() {
        for (Business b : busilist) {
            String s = b.getAppStatus();
            if (s.equals("已批准") || s.equals("已警告") || s.equals("正在申请")) {
                b.setAppStatus("已拆除");
            }
        }
    }
}
