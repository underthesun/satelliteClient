/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author shuai
 */
public class Configuarator {

    private DocumentBuilderFactory factory;
    private DocumentBuilder docBuilder;
    private Document doc;

    public Configuarator() {
        factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Configuarator.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            doc = docBuilder.parse(new File("src/utils/config.xml"));
//            doc = docBuilder.parse(new File("conf/test.xml"));
        } catch (SAXException ex) {
            Logger.getLogger(Configuarator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Configuarator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getId() {
        NodeList nl = doc.getElementsByTagName("id");
        return nl.item(0).getFirstChild().getNodeValue();
    }

    public String getRemoteIP() {
        NodeList nl = doc.getElementsByTagName("remoteIP");
        return nl.item(0).getFirstChild().getNodeValue();
    }

    public String getRemotePort() {
        NodeList nl = doc.getElementsByTagName("remotePort");
        return nl.item(0).getFirstChild().getNodeValue();
    }
//
//    public String getRemoteLoginPort() {
//        NodeList nl = doc.getElementsByTagName("remoteLoginPort");
//        return nl.item(0).getFirstChild().getNodeValue();
//    }
//
//    public String getRemoteMessagePort() {
//        NodeList nl = doc.getElementsByTagName("remoteMessagePort");
//        return nl.item(0).getFirstChild().getNodeValue();
//    }

    public String getLocalPort() {
        NodeList nl = doc.getElementsByTagName("localPort");
        return nl.item(0).getFirstChild().getNodeValue();
    }

    public String getBusinessBoardIP() {
        NodeList nl = doc.getElementsByTagName("businessBoardIP");
        return nl.item(0).getFirstChild().getNodeValue();
    }

    public String getBusinessBoardPort() {
        NodeList nl = doc.getElementsByTagName("businessBoardPort");
        return nl.item(0).getFirstChild().getNodeValue();
    }

    public String getBdVoIP() {
        NodeList nl = doc.getElementsByTagName("bdVoIP");
        return nl.item(0).getFirstChild().getNodeValue();
    }

    public String getBdFax() {
        NodeList nl = doc.getElementsByTagName("bdFax");
        return nl.item(0).getFirstChild().getNodeValue();
    }

    public String getBdVideoNormal() {
        NodeList nl = doc.getElementsByTagName("bdVideoNormal");
        return nl.item(0).getFirstChild().getNodeValue();
    }

    public String getBdVideoHD() {
        NodeList nl = doc.getElementsByTagName("bdVideoHD");
        return nl.item(0).getFirstChild().getNodeValue();
    }

    public String getSiteNum() {
        NodeList nl = doc.getElementsByTagName("siteNum");
        return nl.item(0).getFirstChild().getNodeValue();
    }
//    public void getEmployee(){
//        NodeList nl = doc.getElementsByTagName("employee");
//        int length = nl.getLength();
//            for(int i=0; i<length;i++){
//                Node n = nl.item(i);
//                if(n.getNodeType() == Node.ELEMENT_NODE){
//                    Element e = (Element) n;
//                    System.out.println("name: " + e.getElementsByTagName("name").item(0).getFirstChild().getNodeValue());
//                    System.out.println("gender: "+e.getElementsByTagName("gender").item(0).getFirstChild().getNodeValue());
//                    System.out.println("age: "+e.getElementsByTagName("age").item(0).getFirstChild().getNodeValue());
//                }
//            }
//    }
//    public static void main(String[] args) {
//        Configuarator conf = new Configuarator();
//        System.out.println(conf.getRemoteIP());
//        System.out.println(conf.getRemoteLoginPort());
//        System.out.println(conf.getRemoteMessagePort());
//        System.out.println(conf.getBusinessBoardIP());
//        System.out.println(conf.getBusinessBoardPort());
//    }
}
