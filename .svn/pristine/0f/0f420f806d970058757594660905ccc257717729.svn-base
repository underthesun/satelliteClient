/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author martin
 */
//构造一个队列，存放业务状况
public class Business {

    private String remote_stationID;//远端站点
    private String bandwidth;//带宽，单位kbps
    private String signal_noise_ratio1;//信道1信噪比，单位db
    private String signal_noise_ratio2;//信道2信噪比，单位db
    private String runing_status;//远端站点入网状况
    private String VoIP_num;//VoIP路数
    private String video_business;//视频业务
    private String fax_num;//传真路数
    private String internet_bandwith;//网络带宽，单位kbps
    private String fresh_time;//刷新时间，格式时：分：秒
    private String busi_app_stutas;//业务申请状况
    private static int count;//请求编号
    private int serial;
    private static int privilege;

    public Business(String rid, String bw, String snr1, String snr2, String rs, String voipn, String vb, String fn, String ib, String ft, String bs) {
        this.remote_stationID = rid;
        this.bandwidth = bw;
        this.signal_noise_ratio1 = snr1;
        this.signal_noise_ratio2 = snr2;
        this.runing_status = rs;
        this.VoIP_num = voipn;
        this.video_business = vb;
        this.fax_num = fn;
        this.internet_bandwith = ib;
        this.fresh_time = ft;
        this.busi_app_stutas = bs;
        this.serial = count++;

    }

    public Business() {
        this.serial = count++;
    }

    public void setSerial(int s) {
        this.serial = s;
    }

    public int getSerial() {
        return serial;
    }

    public void setStationID(String sid) {
        this.remote_stationID = sid;
    }

    public void setBandwidth(String bandwidth) {
        this.bandwidth = bandwidth;
    }

    public void setSigNoiseRatio1(String signal_ratio1) {
        this.signal_noise_ratio1 = signal_ratio1;
    }

    public void setSignoiseRatio2(String signal_ratio2) {
        this.signal_noise_ratio2 = signal_ratio2;
    }

    public void setRuningStatus(String runinngstatus) {
        this.runing_status = runinngstatus;
    }

    public void setVoIPNum(String num) {
        this.VoIP_num = num;
    }

    public void setVideoBusi(String video) {
        this.video_business = video;
    }

    public void setFaxNum(String num) {
        this.fax_num = num;
    }

    public void setInterBandwidth(String bandwidth) {
        this.internet_bandwith = bandwidth;
    }

    public void setFreshTime(String time) {
        this.fresh_time = time;
    }

    public void setAppStatus(String status) {
        this.busi_app_stutas = status;
    }

    public String getStationID() {
        return this.remote_stationID;
    }

    public String getBandwidth() {
        return this.bandwidth;
    }

    public String getSigNoiseRatio1() {
        return this.signal_noise_ratio1;
    }

    public String getSignoiseRatio2() {
        return this.signal_noise_ratio2;
    }

    public String getRuningStatus() {
        return this.runing_status;
    }

    public String getVoIPNum() {
        return this.VoIP_num;
    }

    public String getVideoBusi() {
        return this.video_business;
    }

    public String getFaxNum() {
        return this.fax_num;
    }

    public String getInterBandwidth() {
        return this.internet_bandwith;
    }

    public String getFreshTime() {
        return this.fresh_time;
    }

    public String getAppStatus() {
        return this.busi_app_stutas;
    }
}
