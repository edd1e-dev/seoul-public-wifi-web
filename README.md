# seoul-public-wifi-web
서울시 공공 와이파이 API를 이용한 주변 와이파이 조회 웹사이트

패키지는 Maven을 이용하여 관리되었습니다.

사용된 라이브러리 목록
* OkHttp3
* Lombok
* SAX

데이터베이스는 SQLite를 이용하였으며
작성된 스키마는 [이 곳](https://www.dropbox.com/s/a8ig0xer83wrl9o/wifi.db?dl=0)에서 다운로드 하실 수 있습니다.<br>
다운로드 받은 데이터베이스 파일을 <code>C:\Users\Public</code> 폴더에 위치시켜야
정상적으로 프로그램을 실행할 수 있습니다.

## 구현 영상

[![Video Label](http://img.youtube.com/vi/HqnIPox10Qw/0.jpg)](https://youtu.be/HqnIPox10Qw)

## dbdiagram.io

### Table
![Untitled(1)](https://user-images.githubusercontent.com/56020202/204081517-9853f336-8306-4ec1-b21f-9b5f2f7e0c51.png) 

![Untitled(2)](https://user-images.githubusercontent.com/56020202/204081532-be760cf3-a069-46e1-917e-9af27108a5d0.png)

### Table Code
```
Table wifi.HISTORY {
  "ID" INTEGER
  "LOCATE_X" INTEGER
  "LOCATE_Y" INTEGER
  "SEARCH_DATE" TEXT
}

Table wifi.INFO {
 "MANAGE_NUMBER" TEXT
 "BOROUGH" TEXT
 "NAME" TEXT
 "ROAD_ADDRESS" TEXT
 "DETAIL_ADDRESS" TEXT
 "MOUNT_LOCATION" TEXT
 "MOUNT_TYPE" TEXT
 "MOUNT_AGENCY" TEXT
 "SERVICE_TYPE" TEXT
 "NETWORK_TYPE" TEXT
 "MOUNT_DATE" TEXT
 "IN_OUT_DOOR" TEXT
 "WIFI_ENV" TEXT
 "LOCATE_X" REAL
 "LOCATE_Y" REAL
 "WORK_DATE" TEXT
}
```
