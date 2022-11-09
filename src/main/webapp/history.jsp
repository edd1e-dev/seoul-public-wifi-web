<%@ page import="com.near.wifi.Database" %>
<%@ page import="com.near.wifi.History" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>와이파이 정보 구하기</title>
        <link href="history.css" rel="stylesheet">
        <script defer src="history.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    </head>
    <body>

    <h2 style="margin-bottom: 10px; font-weight: bold;">와이파이 정보 구하기</h2>

    <a href="http://localhost:8080" style="display: inline-block; margin-bottom: 40px;">홈</a>
    <p style="display: inline-block;">|</p>
    <a href="http://localhost:8080/history.jsp" style="display: inline-block;">위치 히스토리 목록</a>
    <p style="display: inline-block;">|</p>
    <a href="http://localhost:8080" style="display: inline-block;">Open API 와이파이 정보 가져오기</a>

    <br>

    <%
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            Database database = new Database();
            database.deleteHistory(id);
        }
    %>

    <div class="panel panel-default" style="margin-top: 10px;">
        <table class="table">
            <thead class="table-light">
            <th>ID</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>조회일자</th>
            <th>비고</th>
            </thead>
            <%
                Database database = new Database();
                List<History> historyList = database.getHistory();

                for (History history : historyList) {
                    out.write("<tr id='content'>");
                    out.write("<td>" + history.getId() + "</td>");
                    out.write("<td>" + history.getLocateX() + "</td>");
                    out.write("<td>" + history.getLocateY() + "</td>");
                    out.write("<td>" + history.getSearchDate() + "</td>");
                    out.write("<td><button type='button' id='delete' class='btn btn-secondary' style='width: 70px; height: 40px;' onclick='deleteHistory(this)'>삭제</button></td>");
                    out.write("</tr>");
                }
            %>
        </table>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js" integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk" crossorigin="anonymous"></script>
    </body>
</html>