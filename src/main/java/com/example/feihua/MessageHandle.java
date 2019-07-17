package com.example.feihua;

import com.example.CQHandle;
import com.example.service.Computer;
import com.example.util.HttpRequest;
import com.example.util.SysUtil;

import java.util.Map;

public class MessageHandle extends CQHandle {






    public static void main(String[] args) {

        System.out.println(SysUtil.isComputer("9+9+9423+9+9+6+2*9-6/9/9/9/8/7*9+6"));

        //System.out.println(MessageHandle.loadInfo("apihttps://sit.cuohepai.com/cuohepai_bui/management/acceptance/findList?acceptor=重庆诚雪货运代理有限公司"));
       // System.out.println(MessageHandle.loadInfo(("apihttps://sit.cuohepai.com/cuohepai_bui/management/acceptance/findList?acceptor=重庆诚雪货运代理有限公司")));
        //
        System.out.println(MessageHandle.loadInfo(("apihttps://sit.cuohepai.com/cuohepai_bui/management/acceptance/findList?{ \"acceptor\": \"重庆诚雪货运代理有限公司\" }\n")));
    }

    public static String loadInfo(String msg) {
        //https://www.cuohepai.com/mam/order/v1/mainOrderPages.do
        //https://sit.cuohepai.com/cuohepai_bui/robot/test/order/heartbeat
        //https://sit.cuohepai.com/cuohepai_bui/management/acceptance/findList
        try{

            if(SysUtil.isComputer(msg)){
                msg =  Computer.computerInfo(msg)+"";
            }else if(msg.startsWith("get")){
                msg = msg.substring(3);
                System.out.println(msg);
                if(msg.indexOf("?")>=0){
                    String[] url = msg.split("[?]");
                    msg = HttpRequest.sendPost(url[0],url[1]);
                }else {
                    msg = HttpRequest.sendPost(msg,"");
                }
            }else if(msg.startsWith("api")){
                msg = msg.substring(3);
                System.out.println(msg);
                if(msg.indexOf("?")>=0){
                    if(msg.indexOf("?")>=0){
                        String[] url = msg.split("[?]");
                        msg = HttpRequest.sendPost(url[0],url[1]);
                    }else {
                        String[] url = getUrl(msg);
                        msg = HttpRequest.sendPost(url[0], url[1]);
                    }
                }else {
                    msg = HttpRequest.sendPost(msg,"");
                }
                //msg = HttpRequest.sendPost(msg, "{\"acceptor\":\"重庆诚雪货运代理有限公司\"}");
            }
        }catch (Exception e){
            e.printStackTrace();
            msg = e.getMessage();
        }
        return msg;//SysUtil.character(dens,SysUtil.UTF8);
    }

    public static String[] getUrl(String msg){
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

}
