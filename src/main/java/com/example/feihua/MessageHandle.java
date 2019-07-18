package com.example.feihua;

import com.example.CQHandle;
import com.example.service.Computer;
import com.example.service.RequestUtil;
import com.example.util.HttpRequest;
import com.example.util.SysUtil;

public class MessageHandle extends CQHandle {




    public static void main(String[] args) {

        System.out.println(SysUtil.isComputer("9+9+9423+9+9+6+2*9-6/9/9/9/8/7*9+6"));

        //System.out.println(MessageHandle.loadInfo("apihttps://sit.cuohepai.com/cuohepai_bui/management/acceptance/findList?acceptor=重庆诚雪货运代理有限公司"));
        System.out.println(MessageHandle.loadInfo(("posthttps://sit.cuohepai.com/cuohepai_bui/management/acceptance/findList?acceptor=重庆诚雪货运代理有限公司")));
        //
        //System.out.println(MessageHandle.loadInfo(("apihttps://sit.cuohepai.com/cuohepai_bui/management/acceptance/findList?{ \"acceptor\": \"重庆诚雪货运代理有限公司\" }\n")));
    }

    /**
     * 消息处理
     * @param msg 输出参数
     * @return 返回信息
     */
    public static String loadInfo(String msg) {

        System.out.println(msg);

        try{

            //判断是否为计算公式
            if(SysUtil.isComputer(msg)){
                msg =  Computer.computerToStr(msg);
            }else if(msg.startsWith("get")){//获取get请求结果
                msg = RequestUtil.loadGet(msg);
            }else if(msg.startsWith("post")){//获取post请求结果
                msg = RequestUtil.loadPost(msg);
            }

        }catch (Exception e){
            e.printStackTrace();
            msg = e.getMessage();
        }

        return msg;//SysUtil.character(dens,SysUtil.UTF8);
    }


}
