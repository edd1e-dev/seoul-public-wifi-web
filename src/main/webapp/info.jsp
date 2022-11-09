<%@ page import="com.near.wifi.NetworkManager" %>
<%@ page import="com.near.wifi.WifiInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="com.near.wifi.Database" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Open API 와이파이 정보 가져오기</title>
</head>
<body>
    <%
        NetworkManager networkManager = new NetworkManager();
        List<WifiInfo> wifiList = networkManager.getWifiList();

        Database database = new Database();
        database.deleteWifiInfo();

        int index = 0;

        for (WifiInfo wifiInfo : wifiList) {
            database.registerWifiInfo(wifiInfo);
            index++;
        }

        out.write("<h2 style='text-align: center; margin: 0 auto; margin-top: 20px;'>" + index + "개의 WIFI 정보를 정상적으로 저장하였습니다.</h2>");
    %>
    <div style='text-align: center; margin: 0 auto; margin-top: 20px;'>
        <a href="http://localhost:8080">홈으로 가기</a>
    </div>
</body>
</html>
