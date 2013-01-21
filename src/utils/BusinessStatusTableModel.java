/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author martin
 */
public class BusinessStatusTableModel extends AbstractTableModel {

    public ArrayList<Business> busilist;
    private String columnNames[] = {"远程站点", "带宽", "信道1", "信道2", "入网状况", "VoIP路数", "视频业务", "传真路数", "互联网带宽", "天线口径", "功放大小", "更新时间", "运行状况"};

    public BusinessStatusTableModel() {
        busilist = new ArrayList<Business>();
//        loadTestData();
    }

    public void addRecord(Business b) {
        busilist.add(b);
        fireTableDataChanged();
    }

    public String getBandwidth(int serial) {
        Pattern p = Pattern.compile("[^0-9]");
        String bd="";
        for (Business b : busilist) {
            if (b.getSerial() == serial) {
                bd = b.getBandwidth();
            }
        }
        
        Matcher m = p.matcher(bd);
        String result = m.replaceAll("");
        return result;
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
        busilist.add(new Business("1", "512", "xxdb", "xxdb", "入网", "2", "视频业务", "2", "2m", "2", "2", "sss", "dsd"));
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
        switch (columnIndex) {
            case 0:
                list = busilist.get(rowIndex).getStationID();
                break;
            case 1:
                list = busilist.get(rowIndex).getBandwidth();
                break;
            case 2:
                list = busilist.get(rowIndex).getSigNoiseRatio1();
                break;
            case 3:
                list = busilist.get(rowIndex).getSignoiseRatio2();
                break;
            case 4:
                list = busilist.get(rowIndex).getRuningStatus();
                break;
            case 5:
                list = busilist.get(rowIndex).getVoIPNum();
                break;
            case 6:
                list = busilist.get(rowIndex).getVideoBusi();
                break;
            case 7:
                list = busilist.get(rowIndex).getFaxNum();
                break;
            case 8:
                list = busilist.get(rowIndex).getInterBandwidth();
                break;
            case 9:
                list = busilist.get(rowIndex).getAntennaCaliber();
                break;
            case 10:
                list = busilist.get(rowIndex).getAmplifier();
                break;
            case 11:
                list = busilist.get(rowIndex).getFreshTime();
                break;
            case 12:
                list = busilist.get(rowIndex).getAppStatus();
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
        this.fireTableStructureChanged();
    }
    
    public void updateSNR(int snr){
        for(Business b:busilist){
            b.setSigNoiseRatio1(""+snr);
        }
        fireTableDataChanged();
    }
}
