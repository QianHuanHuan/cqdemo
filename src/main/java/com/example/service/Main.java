package com.example.service;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import org.jsoup.Connection.Method;

public class Main {
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        Map<String, String> postData = new HashMap<String, String>();
        postData.put("mblNo", "18679999967");
        postData.put("logPswd", "Q111111");
        Map<String, String> cookie;


        Connection connection = Jsoup.connect("https://www.cuohepai.com/mam/user/v1/login.do");
        connection.timeout(10000).header("Content-Type","application/json; charset=UTF-8");
        connection.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        connection.header("Accept-Encoding", "gzip, deflate, sdch");
        connection.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
        connection.header("Accept-Language", "zh-CN,zh;q=0.8");
        connection.data(postData);
        connection.method(Method.POST);

        cookie = connection.execute().cookies();

        System.out.println(cookie);
        String labHtml;
        labHtml = Jsoup.connect("https://www.cuohepai.com/mam/management/message/loadMsgList.do").cookies(cookie).timeout(10000).get().html();

        System.out.println(labHtml);

    }
}

