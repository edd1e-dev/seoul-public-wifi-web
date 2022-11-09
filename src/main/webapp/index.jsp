<%@ page import="com.near.wifi.NetworkManager" %>
<%@ page import="com.near.wifi.Database" %>
<%@ page import="com.near.wifi.WifiInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="com.near.wifi.Geometry" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>와이파이 정보 구하기</title>
        <link href="index.css" rel="stylesheet">
        <script defer src="index.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    </head>
    <body>

        <h2 style="margin-bottom: 10px; font-weight: bold;">와이파이 정보 구하기</h2>

        <a href="http://localhost:8080" style="display: inline-block; margin-bottom: 40px;">홈</a>
        <p style="display: inline-block;">|</p>
        <a href="http://localhost:8080/history.jsp" style="display: inline-block;">위치 히스토리 목록</a>
        <p style="display: inline-block;">|</p>
        <a href="http://localhost:8080/info.jsp" style="display: inline-block;">Open API 와이파이 정보 가져오기</a>

        <br>

        <%
            out.write("<p style='display: inline-block; font-size: 20px;'>LAT: </p>");

            String lat = request.getParameter("lat");
            String lnt = request.getParameter("lnt");


            if (lat != null && lnt != null && !lat.isEmpty() && !lnt.isEmpty()) {
                out.write("<input type='text' id='lat-input' class='form-control' placeholder='0.0' aria-label='0.0' style='display: inline-block; width: 250px;' value='" + lat + "'>");
            } else {
                out.write("<input type='text' id='lat-input' class='form-control' placeholder='0.0' aria-label='0.0' style='display: inline-block; width: 250px;'>");
            }

            out.write("<p style='display: inline-block; font-size: 20px;'>, LNT: </p>");

            if (lat != null && lnt != null && !lat.isEmpty() && !lnt.isEmpty()) {
                out.write("<input type='text' id='lnt-input' class='form-control' placeholder='0.0' aria-label='0.0' style='display: inline-block; width: 250px;' value='" + lnt + "'>");
            } else {
                out.write("<input type='text' id='lnt-input' class='form-control' placeholder='0.0' aria-label='0.0' style='display: inline-block; width: 250px;'>");
            }
        %>

        <button type="button" id="get-location" class="btn btn-secondary" style="display: inline-block; width: 180px; height: 47px; margin-left: 10px;">내 위치 가져오기</button>
        <button type="button" id="get-wifi" class="btn btn-secondary" style="display: inline-block; width: 180px; height: 47px;">근처 WI-FI 정보 보기</button>

        <div class="panel panel-default" style="margin-top: 10px;">
            <table class="table">
                <thead class="table-light">
                    <th>거리(Km)</th>
                    <th>관리번호</th>
                    <th>자치구</th>
                    <th>와이파이명</th>
                    <th>도로명주소</th>
                    <th>상세주소</th>
                    <th>설치위치(층)</th>
                    <th>설치유형</th>
                    <th>설치기관</th>
                    <th>서비스구분</th>
                    <th>망 종류</th>
                    <th>설치년도</th>
                    <th>실내외구분</th>
                    <th>WIFI접속환경</th>
                    <th>X좌표</th>
                    <th>Y좌표</th>
                    <th>작업일자</th>
                </thead>
                <%
                    if (lat != null && lnt != null && !lat.isEmpty() && !lnt.isEmpty()) {
                        Database database = new Database();
                        database.registerHistory(lat, lnt);
                        double d_lat = Double.parseDouble(lat);
                        double d_lnt = Double.parseDouble(lnt);
                        List<WifiInfo> wifiInfoList = database.getWifiInfo(d_lat, d_lnt);

                        for (WifiInfo wifiInfo : wifiInfoList) {
                            double distance = Geometry.distance(d_lat, d_lnt, Double.parseDouble(wifiInfo.getLocateX()), Double.parseDouble(wifiInfo.getLocateY()));
                            wifiInfo.setDistance(distance);
                        }

                        Collections.sort(wifiInfoList, new Comparator<WifiInfo>() {
                            @Override
                            public int compare(WifiInfo o1, WifiInfo o2) {
                                return Double.compare(o1.getDistance(), o2.getDistance());
                            }
                        });

                        for (int i = 0; i < 20; i++) {
                            WifiInfo wifiInfo = wifiInfoList.get(i);
                            out.write("<tr id='content'>");
                            out.write("<td>" + wifiInfo.getDistance() + "</td>");
                            out.write("<td>" + wifiInfo.getManageNumber() + "</td>");
                            out.write("<td>" + wifiInfo.getBorough() + "</td>");
                            out.write("<td>" + wifiInfo.getName() + "</td>");
                            out.write("<td>" + wifiInfo.getRoadAddress() + "</td>");
                            out.write("<td>" + wifiInfo.getDetailAddress() + "</td>");
                            out.write("<td>" + wifiInfo.getMountLocation() + "</td>");
                            out.write("<td>" + wifiInfo.getMountType() + "</td>");
                            out.write("<td>" + wifiInfo.getMountAgency() + "</td>");
                            out.write("<td>" + wifiInfo.getServiceType() + "</td>");
                            out.write("<td>" + wifiInfo.getNetworkType() + "</td>");
                            out.write("<td>" + wifiInfo.getMountDate() + "</td>");
                            out.write("<td>" + wifiInfo.getInOutDoor() + "</td>");
                            out.write("<td>" + wifiInfo.getWifiEnv() + "</td>");
                            out.write("<td>" + wifiInfo.getLocateX() + "</td>");
                            out.write("<td>" + wifiInfo.getLocateY() + "</td>");
                            out.write("<td>" + wifiInfo.getWorkDate() + "</td>");
                            out.write("</tr>");
                        }
                    } else {
                        out.write("<tfoot><tr><td colspan='17' style='text-align: center'>위치 정보를 입력한 후에 조회해 주세요.</td></tr></tfoot>");
                    }
                %>
            </table>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js" integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk" crossorigin="anonymous"></script>
    </body>
</html>