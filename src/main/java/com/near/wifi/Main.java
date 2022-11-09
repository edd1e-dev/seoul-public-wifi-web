package com.near.wifi;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        NetworkManager networkManager = new NetworkManager();

        String xml;
        int index = 0;
        do {
            xml = networkManager.getWifiInfo();

            if (xml == null) break;

            InputSource inputSource = new InputSource(new StringReader(xml));
            SAXParserFactory factory = SAXParserFactory.newInstance();

            try {
                SAXParser parser = factory.newSAXParser();
                Handler handler = new Handler();
                parser.parse(inputSource, handler);

                List<WifiInfo> list = handler.getWifiList();

                Database database = new Database();

                for (WifiInfo w : list) {
                    System.out.println(index + " / " + w);
                    index++;
                    // database.addItem(w);
                }
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            } catch (SAXException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (xml != null);

        double distance = Geometry.distance(37.552788, 126.89939, 37.5544069, 126.8998666);
        System.out.println(distance);

        distance = Geometry.distance(37.5544069, 126.8998666, 37.552788, 126.89939);
        System.out.println(distance);

        new Database().registerHistory("1234", "5678");
        new Database().deleteHistory("1");
    }
}
