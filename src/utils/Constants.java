/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author shuai
 */
public class Constants {

    private static int bdVoIP;
    private static int bdFax;
    private static int bdVideoNormal;
    private static int bdVideoHD;
    private static String id;
    private static String remoteIP;
    private static int remotePort;
    private static int remoteLoginPort;
    private static int remoteMessagePort;
    private static int localPort;
    private static String bizBoardIP;
    private static int bizBoardPort;
    private String sigNoiseLocal = "";
    private String sigNoiseRemote = "";
    private static int siteNum;
    private static int fpReportSNR;
    private static int fpQuerySNR;

    public Constants() {
    }
    

    public static int getRemotePort() {
        return remotePort;
    }

    public static void setRemotePort(int remotePort) {
        Constants.remotePort = remotePort;
    }

    public static String getRemoteIP() {
        return remoteIP;
    }

    public static int getRemoteLoginPort() {
        return remoteLoginPort;
    }

    public static int getRemoteMessagePort() {
        return remoteMessagePort;
    }

    public static int getBdVoIP() {
        return bdVoIP;
    }

    public static void setBdVoIP(int bdVoIP) {
        Constants.bdVoIP = bdVoIP;
    }

    public static int getBdFax() {
        return bdFax;
    }

    public static void setBdFax(int bdFax) {
        Constants.bdFax = bdFax;
    }

    public static int getBdVideoNormal() {
        return bdVideoNormal;
    }

    public static void setBdVideoNormal(int bdVideoNormal) {
        Constants.bdVideoNormal = bdVideoNormal;
    }

    public static int getBdVideoHD() {
        return bdVideoHD;
    }

    public static void setBdVideoHD(int bdVideoHD) {
        Constants.bdVideoHD = bdVideoHD;
    }

    public static int getLocalPort() {
        return localPort;
    }

    public static String getBizBoardIP() {
        return bizBoardIP;
    }

    public static int getBizBoardPort() {
        return bizBoardPort;
    }

    public static String getId() {
        return id;
    }

    public static void setRemoteIP(String aRemoteIP) {
        remoteIP = aRemoteIP;
    }

    public static void setRemoteLoginPort(int aRemoteLoginPort) {
        remoteLoginPort = aRemoteLoginPort;
    }

    public static void setRemoteMessagePort(int aRemoteMessagePort) {
        remoteMessagePort = aRemoteMessagePort;
    }

    public static void setLocalPort(int localPort) {
        Constants.localPort = localPort;
    }

    public static void setBizBoardIP(String aBizBoardIP) {
        bizBoardIP = aBizBoardIP;
    }

    public static void setBizBoardPort(int aBizBoardPort) {
        bizBoardPort = aBizBoardPort;
    }

    public static void setId(String aId) {
        id = aId;
    }

    public String getSigNoiseLocal() {
        return sigNoiseLocal;
    }

    public void setSigNoiseLocal(String sigNoiseLocal) {
        this.sigNoiseLocal = sigNoiseLocal;
    }

    public String getSigNoiseRemote() {
        return sigNoiseRemote;
    }

    public void setSigNoiseRemote(String sigNoiseRemote) {
        this.sigNoiseRemote = sigNoiseRemote;
    }

    public static int getSiteNum() {
        return siteNum;
    }

    public static void setSiteNum(int siteNum) {
        Constants.siteNum = siteNum;
    }

    public static int getFpReportSNR() {
        return fpReportSNR;
    }

    public static void setFpReportSNR(int fpReportSNR) {
        Constants.fpReportSNR = fpReportSNR;
    }

    public static int getFpQuerySNR() {
        return fpQuerySNR;
    }

    public static void setFpQuerySNR(int fpQuerySNR) {
        Constants.fpQuerySNR = fpQuerySNR;
    }
    
}
