package com.example.feihua;

import com.example.CQHandle;
import com.example.service.Computer;
import com.example.util.HttpRequest;
import com.example.util.SysUtil;

public class MessageHandle extends CQHandle {




    public static void main(String[] args) {

        System.out.println(SysUtil.isComputer("9+9+9423+9+9+6+2*9-6/9/9/9/8/7*9+6"));

        //System.out.println(MessageHandle.loadInfo("apihttps://sit.cuohepai.com/cuohepai_bui/management/acceptance/findList?acceptor=重庆诚雪货运代理有限公司"));
        System.out.println(MessageHandle.loadInfo(("apihttps://sit.cuohepai.com/cuohepai_bui/management/acceptance/findList?acceptor=重庆诚雪货运代理有限公司")));
        //
        //System.out.println(MessageHandle.loadInfo(("apihttps://sit.cuohepai.com/cuohepai_bui/management/acceptance/findList?{ \"acceptor\": \"重庆诚雪货运代理有限公司\" }\n")));
    }

    /**
     * 消息处理
     * @param msg 输出参数
     * @return 返回信息
     */
    public static String loadInfo(String msg) {
        //https://www.cuohepai.com/mam/order/v1/mainOrderPages.do
        //https://sit.cuohepai.com/cuohepai_bui/robot/test/order/heartbeat
        //https://sit.cuohepai.com/cuohepai_bui/management/acceptance/findList
        System.out.println(msg);
        try{

            //判断是否为计算公式
            if(SysUtil.isComputer(msg)){
                System.out.println("T1");
                msg =  Computer.computerInfo(msg)+"";
            }else if(msg.startsWith("get")){
                //去除开始get
                msg = msg.substring(3);
                //是否有带参数
                if(msg.indexOf("?")>=0){
                    System.out.println("K1");
                    String[] url = msg.split("[?]");
                    msg = HttpRequest.sendPost(url[0],url[1]);
                }else {
                    System.out.println("K2");
                    msg = HttpRequest.sendPost(msg,"");
                }
            }else if(msg.startsWith("api")){
                //去除开始api
                msg = msg.substring(3);
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
            }else if(msg.startsWith("tst")){
                msg = "https://sit.cuohepai.com/cuohepai_bui/management/acceptance/findList?acceptor=重庆诚雪货运代理有限公司";
                String[] url = getUrl(msg);
                System.out.println("S2-A");
                msg = HttpRequest.sendPost(url[0], url[1]);
            }
        }catch (Exception e){
            e.printStackTrace();
            msg = e.getMessage();
        }
        return msg;//SysUtil.character(dens,SysUtil.UTF8);
    }

    /**
     * a=4&c=5 转换为JSON 数据格式
     * @param msg 待转换
     * @return 结果
     */
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
