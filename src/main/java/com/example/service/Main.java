package com.example.service;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;

import org.jsoup.Connection.Method;

public class Main {
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        Map<String, String> postData = new HashMap<String, String>();
        postData.put("keyWords", "官方测试");
        Map<String, String> cookie;
        cookie = Jsoup.connect("http://lolbox.duowan.com/playerSearch.php").timeout(10000).data(postData).method(Method.POST).execute().cookies();

        System.out.println(cookie);
        String labHtml;
        labHtml = Jsoup.connect("http://lolbox.duowan.com/playerList.php").cookies(cookie).timeout(10000).get().html();

        System.out.println(labHtml);

    }
}

