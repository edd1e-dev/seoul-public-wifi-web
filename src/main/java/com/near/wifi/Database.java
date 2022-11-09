package com.near.wifi;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Database {
    private static Connection conn = null;

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void getConnection() {
        if (conn == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Public\\wifi.db");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteWifiInfo() {
        getConnection();
        PreparedStatement pstmt;

        String sql = "DELETE FROM INFO";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void registerWifiInfo(WifiInfo wifiInfo) {
        try {
            getConnection();
            PreparedStatement pstmt;

            String sql = "INSERT INTO INFO (MANAGE_NUMBER, BOROUGH, NAME, " +
                    "ROAD_ADDRESS, DETAIL_ADDRESS, MOUNT_LOCATION, MOUNT_TYPE, " +
                    "MOUNT_AGENCY, SERVICE_TYPE, NETWORK_TYPE, MOUNT_DATE, " +
                    "IN_OUT_DOOR, WIFI_ENV, LOCATE_X, LOCATE_Y, WORK_DATE) VALUES " +
                    "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, wifiInfo.getManageNumber());
            pstmt.setString(2, wifiInfo.getBorough());
            pstmt.setString(3, wifiInfo.getName());
            pstmt.setString(4, wifiInfo.getRoadAddress());
            pstmt.setString(5, wifiInfo.getDetailAddress());
            pstmt.setString(6, wifiInfo.getMountLocation());
            pstmt.setString(7, wifiInfo.getMountType());
            pstmt.setString(8, wifiInfo.getMountAgency());
            pstmt.setString(9, wifiInfo.getServiceType());
            pstmt.setString(10, wifiInfo.getNetworkType());
            pstmt.setString(11, wifiInfo.getMountDate());
            pstmt.setString(12, wifiInfo.getInOutDoor());
            pstmt.setString(13, wifiInfo.getWifiEnv());
            pstmt.setString(14, wifiInfo.getLocateX());
            pstmt.setString(15, wifiInfo.getLocateY());
            pstmt.setString(16, wifiInfo.getWorkDate());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<WifiInfo> getWifiInfo(double lat, double lnt) {
        List<WifiInfo> wifiList = new ArrayList<>();
        try {
            getConnection();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM INFO";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String manageNumber = rs.getString("MANAGE_NUMBER");
                String borough = rs.getString("BOROUGH");
                String name = rs.getString("NAME");
                String roadAddress = rs.getString("ROAD_ADDRESS");
                String detailAddress = rs.getString("DETAIL_ADDRESS");
                String mountLocation = rs.getString("MOUNT_LOCATION");
                String mountType = rs.getString("MOUNT_TYPE");
                String mountAgency = rs.getString("MOUNT_AGENCY");
                String serviceType = rs.getString("SERVICE_TYPE");
                String networkType = rs.getString("NETWORK_TYPE");
                String mountDate = rs.getString("MOUNT_DATE");
                String inOutDoor = rs.getString("IN_OUT_DOOR");
                String wifiEnv = rs.getString("WIFI_ENV");
                String locateX = rs.getString("LOCATE_X");
                String locateY = rs.getString("LOCATE_Y");
                String workDate = rs.getString("WORK_DATE");
                WifiInfo wifiInfo = new WifiInfo(0.0, manageNumber, borough, name, roadAddress,
                        detailAddress, mountLocation, mountType, mountAgency, serviceType,
                        networkType, mountDate, inOutDoor, wifiEnv, locateX, locateY, workDate);
                wifiList.add(wifiInfo);
            }

            return wifiList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void registerHistory(String lat, String lnt) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String strDate = dtf.format(now);

            getConnection();
            PreparedStatement pstmt;

            String sql = "INSERT INTO HISTORY (LOCATE_X, LOCATE_Y, SEARCH_DATE) VALUES " +
                    "(?,?,?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, lat);
            pstmt.setString(2, lnt);
            pstmt.setString(3, strDate);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<History> getHistory() {
        List<History> historyList = new ArrayList<>();
        try {
            getConnection();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM HISTORY ORDER BY SEARCH_DATE DESC";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("ID");
                String locateX = rs.getString("LOCATE_X");
                String locateY = rs.getString("LOCATE_Y");
                String searchDate = rs.getString("SEARCH_DATE");
                History history = new History(id, locateX, locateY, searchDate);
                historyList.add(history);
            }

            return historyList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteHistory(String id) {
        getConnection();
        PreparedStatement pstmt;

        String sql = "DELETE FROM HISTORY where ID = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
