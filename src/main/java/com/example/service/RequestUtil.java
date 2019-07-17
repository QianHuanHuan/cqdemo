package com.example.service;

import com.example.util.HttpRequest;

public class RequestUtil {

    /**
     * a=4&c=5 转换为JSON 数据格式
     * @param msg 待转换
     * @return 结果
     */
    public static String[] getUrl(String msg)  throws Exception{
        String[] url = msg.split("[?]");
        String[] ps = url[1].split("&");

        StringBuffer param = new StringBuffer("{");

        for (int i = 0; i < ps.length; i++) {
            if (i!= 0) param.append(",");
            param.append("\""+ps[i].split("=")[0]+"\":\""+ps[i].split("=")[1]+"\"");
        }
        param.append("}");
        url[1] = param.toString();
        return url;
    }

    /**
     * post请求
     * @param msg
     * @return
     */
    public static String loadPost(String msg)  throws Exception{
        //去除开始post
        msg = msg.substring(4);
        //是否有带参数
        if(msg.indexOf("?")>=0){
            //判断是否为JSON参数
            if(msg.indexOf("\"")>=0){
                System.out.println("S1");
                String[] url = msg.split("[?]");
                msg = HttpRequest.sendPost(url[0],url[1]);
            }else {
                String[] url = getUrl(msg);
                System.out.println("S2");
                msg = HttpRequest.sendPost(url[0], url[1]);
            }
        }else {
            System.out.println("S3");
            msg = HttpRequest.sendPost(msg,"");
        }
        //msg = HttpRequest.sendPost(msg, "{\"acceptor\":\"重庆诚雪货运代理有限公司\"}");
        return msg;
    }

    /**
     * get请求
     * @param msg
     * @return
     */
    public static String loadGet(String msg) throws Exception {
        //去除开始get
        msg = msg.substring(3);
        //是否有带参数
        if (msg.indexOf("?") >= 0) {
            System.out.println("K1");
            String[] url = msg.split("[?]");
            msg = HttpRequest.sendPost(url[0], url[1]);
        } else {
            System.out.println("K2");
            msg = HttpRequest.sendPost(msg, "");
        }
        return msg;
    }
}
