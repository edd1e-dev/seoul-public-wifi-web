package com.near.wifi;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WifiInfo {
    private double distance;
    private String manageNumber;
    private String borough;
    private String name;
    private String roadAddress;
    private String detailAddress;
    private String mountLocation;
    private String mountType;
    private String mountAgency;
    private String serviceType;
    private String networkType;
    private String mountDate;
    private String inOutDoor;
    private String wifiEnv;
    private String locateX;
    private String locateY;
    private String workDate;

    public WifiInfo(double distance, String manageNumber, String borough, String name, String roadAddress, String detailAddress, String mountLocation, String mountType, String mountAgency, String serviceType, String networkType, String mountDate, String inOutDoor, String wifiEnv, String locateX, String locateY, String workDate) {
        this.distance = distance;
        this.manageNumber = manageNumber;
        this.borough = borough;
        this.name = name;
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
        this.mountLocation = mountLocation;
        this.mountType = mountType;
        this.mountAgency = mountAgency;
        this.serviceType = serviceType;
        this.networkType = networkType;
        this.mountDate = mountDate;
        this.inOutDoor = inOutDoor;
        this.wifiEnv = wifiEnv;
        this.locateX = locateX;
        this.locateY = locateY;
        this.workDate = workDate;
    }

    public WifiInfo() {

    }
}
