package com.near.wifi;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class NetworkManager {
    private int from = 1;
    private int to = 1000;

    public String getWifiInfo() {
        try {
            StringBuilder result;
            String url = "http://openapi.seoul.go.kr:8088/734f6f73656b696f39376945654858/xml/TbPublicWifiInfo/" + from + "/" + to + "/";

            OkHttpClient client = new OkHttpClient();
            Request.Builder builder = new Request.Builder().url(url).get();
            Request request = builder.build();
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (body != null) {
                    result = new StringBuilder(body.string());
                    if (result.toString().contains("해당하는 데이터가 없습니다.")) {
                        return null;
                    } else {
                        from += 1000;
                        to += 1000;
                        return result.toString();
                    }
                }
            } else {
                System.err.println("Error Occurred");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<WifiInfo> getWifiList() {
        NetworkManager networkManager = new NetworkManager();
        List<WifiInfo> wifiList = new ArrayList<>();

        String xml;

        do {
            xml = networkManager.getWifiInfo();

            if (xml == null) break;

            InputSource inputSource = new InputSource(new StringReader(xml));
            SAXParserFactory factory = SAXParserFactory.newInstance();

            try {
                SAXParser parser = factory.newSAXParser();
                Handler handler = new Handler();
                parser.parse(inputSource, handler);

                wifiList.addAll(handler.getWifiList());

            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            } catch (SAXException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (xml != null);

        return wifiList;
    }
}
