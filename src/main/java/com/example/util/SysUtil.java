package com.example.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.regex.Pattern;

public class SysUtil {

    public static final String GBK = "GBK";
    public static final String UTF8 = "UTF-8";
    public static final String GB2312 = "GB2312";
    public static final String ISO = "ISO-8859-1";


    //字符串 编码类型
    private static final String[] ENCODLIST = new String[]{
            GBK,UTF8,GB2312,ISO
    };

    /**
     * 字符转码 >>
     *
     * @param s 转换参数
     * @return 结果值
     */
    public static String character(String s,String charset){
        String a = "";
        try {
            a = new String(s.getBytes(), charset);
        }catch (UnsupportedEncodingException u){
            u.printStackTrace();
        }
        return a;
    }


    /**
     * 查询字符串编码
     * @param str 字符串
     * @return 编码结果
     */
    public static String getEncoding(String str) {
        for (int i = 0; i < ENCODLIST.length; i++) {
             String encod = getEncoding(str,ENCODLIST[i]);
             if(encod!=null) return encod;
        }
        return "";
    }

    /**
     * 字符串编码查询
     * @param str 字符串
     * @param encode 编码
     * @return 结果
     */
    public static String getEncoding(String str,String encode){
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;
                return s;
            }
        } catch (Exception exception) {
        }
        return null;
    }


    /**
     * 数据转字符串 null 设置 自定义返回值
     *
     * @param ob 待转换参数
     * @param em 待转换为空 默认值
     * @return 返回转换后数据
     */
    public static String toStr(Object ob, String em) {
        String str = String.valueOf(ob);
        str = str.equals("null") ? em : str;
        return str;
    }

    /**
     * 数据转字符串 null 返回 ""
     *
     * @param ob 参数
     * @return 转换后数据
     */
    public static String toStr(Object ob) {
        return toStr(ob, "");
    }

    /**
     * 数据转int null返回 0
     *
     * @param ob 待转换参数
     * @return int结果
     */
    public static int toInt(Object ob) {
        String o = toStr(ob, "0");
        return Integer.valueOf(o);
    }

    /**
     * 数据转int null 设置 自定义返回
     *
     * @param ob 待转换参数
     * @param num 为空默认值
     * @return 结果
     */
    public static int toInt(Object ob, int num) {
        String o = toStr(ob, "" + num);
        return Integer.valueOf(o);
    }

    public static Double toDub(Object ob) {
        try {
            return Double.valueOf(toStr(ob));
        } catch (Exception e) {
            return 0.0;
        }
    }

    /**
     * 数据转 BigDecimal null 设置 自定义返回
     *
     * @param ob 待转换参数
     * @param num 为空默认值
     * @return 结果
     */
    public static BigDecimal toBig(Object ob, String num) {
        String o = toStr(ob, "" + num);
        return new BigDecimal(o);
    }

    /**
     * 数据转 BigDecimal
     *
     * @param ob 待转换参数
     * @return 结果
     */
    public static BigDecimal toBig(Object ob) {
        return toBig(ob, "0");
    }

    /**
     * 去除 文本内空格
     * @param ob 待去除空格的参数
     * @return 结果
     */
    public static String delBlank(Object ob){
        return toStr(ob).replace(" ","").trim();
    }

    /**
     * 判断一个字符串是否是数字。
     *
     * @param string
     * @return
     */
    public static boolean isNumber(String string) {
        if (string == null)
            return false;
        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
        return pattern.matcher(string).matches();
    }



    /**
     * 判断一个字符串是否是计算公式。
     *
     * @param string
     * @return
     */
    public static boolean isComputer(String string) {
        if (string == null)
            return false;
        Pattern pattern = Pattern.compile("^\\d+(\\.*\\d{0,2})([+*/-]\\d+(\\.*\\d{0,2}))+$");
        return pattern.matcher(string).matches();
    }
}
