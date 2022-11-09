package com.near.wifi;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class Handler extends DefaultHandler {
    private List<WifiInfo> wifiList;
    private WifiInfo wifiInfo;
    private String str;

    public Handler() {
        wifiList = new ArrayList<>();
    }

    public void startElement(String uri, String localName, String name, Attributes att) {
        if(name.equals("X_SWIFI_MGR_NO")) {
            wifiInfo = new WifiInfo();
            wifiList.add(wifiInfo);
        }
    }

    public void endElement(String uri, String localName, String name) {
        switch (name) {
            case "X_SWIFI_MGR_NO":
                wifiInfo.setManageNumber(str);
                break;
            case "X_SWIFI_WRDOFC":
                wifiInfo.setBorough(str);
                break;
            case "X_SWIFI_MAIN_NM":
                wifiInfo.setName(str);
                break;
            case "X_SWIFI_ADRES1":
                wifiInfo.setRoadAddress(str);
                break;
            case "X_SWIFI_ADRES2":
                wifiInfo.setDetailAddress(str);
                break;
            case "X_SWIFI_INSTL_FLOOR":
                wifiInfo.setMountLocation(str);
                break;
            case "X_SWIFI_INSTL_TY":
                wifiInfo.setMountType(str);
                break;
            case "X_SWIFI_INSTL_MBY":
                wifiInfo.setMountAgency(str);
                break;
            case "X_SWIFI_SVC_SE":
                wifiInfo.setServiceType(str);
                break;
            case "X_SWIFI_CMCWR":
                wifiInfo.setNetworkType(str);
                break;
            case "X_SWIFI_CNSTC_YEAR":
                wifiInfo.setMountDate(str);
                break;
            case "X_SWIFI_INOUT_DOOR":
                wifiInfo.setInOutDoor(str);
                break;
            case "X_SWIFI_REMARS3":
                wifiInfo.setWifiEnv(str);
                break;
            case "LAT":
                wifiInfo.setLocateX(str);
                break;
            case "LNT":
                wifiInfo.setLocateY(str);
                break;
            case "WORK_DTTM":
                wifiInfo.setWorkDate(str);
                break;
        }
    }
    public void characters(char[] ch, int start, int length) {
        str = new String(ch, start, length);
    }

    public List<WifiInfo> getWifiList() {
        return wifiList;
    }
}
