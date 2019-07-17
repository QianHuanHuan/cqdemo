package com.example.service;

import com.example.util.SysUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 计算
 */
public class Computer {


    public static void main(String[] args) {
        //double as = Computer.computerInfo("3+2-5*2/6+6+2*8++2+3+4+6-86*9*7*88*5+66");
        //double as = Computer.computerInfo("2+2+2+10/2*5+6*2*2*2*2*5/3+6+5-4/8+2+36/3+2+3+5+8/2*30");
        System.out.println(SysUtil.isComputer("3+6+9-8*8+55+96-84.4*85*8+666+66*88+11+6+6*8*8*9/8/7/5*6+8*9+6"));
        double as = Computer.computerInfo("3+6+9-8*8+55+96-84*85*8+666+66*88+11+6+6*8*8*9/8/7/5*6+8*9+6+9/8-9+5+6");
        //2+2+2+25+160+6+5-0.5+2+12
        System.out.println();
        System.out.println(as);
       /* Computer.computerInfo("121+555+66+1-2*999-34/8");
        System.out.println(Computer.add("6+3+44+52"));
        System.out.println(Computer.reduce("6-3-5-10"));
        System.out.println(Computer.ride("6*3*6"));
        System.out.println(Computer.except("9/3/2"));*/
    }


    public static double computerInfos(String formula){
        double val = 0.0;



        return val;
    }

    /**
     * 处理计算结果
     * @param formula 公式
     * @return 值
     */
    public static double computerInfo(String formula) {
        int len = formula.length();
        StringBuffer buffer = new StringBuffer();
        double val = 0.0;
        // 去除空格
        formula = formula.replace(" ", "");
        // listSum 数值
        List<Double> listSum = new ArrayList<>();
        // listType 算式符号
        List<String> listType = new ArrayList<>();

        for (int i = 0; i <= len; i++) {
            String ks = "";
            //获取单个字符
            if (i != len) {
                ks = formula.substring(i, i + 1);
            }
            //判断是否为数字 如果是拼接为一个完整数字
            if (SysUtil.isNumber(ks)) {
                buffer.append(ks);
            } else {
                //把拼接数字转double存入集合
                listSum.add(SysUtil.toDub(buffer.toString()));
                //把运算符存入集合
                listType.add(ks);
                buffer = new StringBuffer();
            }
        }

        //开始,个数
        List<Integer[]> listAj = loadListInt(listType);


        //处理后 数值
        List<Double> listSumX = new ArrayList<>();
        //处理后 运算符
        List<String> listTypeX = new ArrayList<>();

        //每轮乘除运算 数值
        List<Double> listSumS = new ArrayList<>();
        //每轮乘除运算 运算符
        List<String> listTypeS = new ArrayList<>();

        for (int i = 0; i < listSum.size(); i++) {

            Double end = listSum.get(i);//值
            String type = listType.get(i);//运算符

            for (int j = 0; j < listAj.size(); j++) {
                //判断是否序号等于 乘除运算开始的序号
                if(i == listAj.get(j)[0]){
                    //清空
                    listSumS = new ArrayList<>();
                    listTypeS = new ArrayList<>();
                    // 循环写入 乘除运算
                    for (int k = 0; k <= listAj.get(j)[1]; k++) {
                        //i+k 获取运算符下标
                        listSumS.add(listSum.get(i+k));
                        listTypeS.add(listType.get(i+k));
                        System.out.println("公式:"+listSum.get(i+k)+listType.get(i+k));
                    }
                    //获取乘除运算结果
                    end = divisionList(listSumS,listTypeS);
                    //获取当前位置的运算符
                    type = listType.get(i+listAj.get(j)[1]);
                    //i 加上 因乘除跳过的步数
                    i+=listAj.get(j)[1];
                    break;
                }
            }
            //写入处理后的值
            listSumX.add(end);
            listTypeX.add(type);
        }

        //获取和
        val = subtractionList(listSumX,listTypeX);

        return val;
    }

    /***
     * 获取 等级运算 先乘除后加减 获取乘除的位置
     * @param listType 运算符集合
     * @return 乘除的位置
     */
    public static List<Integer[]> loadListInt(List<String> listType){
        List<Integer[]> listAj = new ArrayList<>();
        int j =0;
        for (int i = 0; i < listType.size(); i++) {
            if(listType.get(i).equals("*")||listType.get(i).equals("/")){
                j++;
            }else {
                if(j!=0){
                    //<j>保存连续个数 和 <i-j>开始位数
                    listAj.add(new Integer[]{(i-j),j});
                    System.out.println("j:"+j+" I:"+(i-j));
                }
                j=0;
            }
        }
        return listAj;
    }

    /**
     * 加减运算
     * @param listSum 数字集合
     * @param listType 运算符集合
     * @return 结果值
     */
    public static Double subtractionList(List<Double> listSum,List<String> listType){
        Double val = 0.0;
        for (int i = 0; i < listSum.size() - 1; i++) {
            if (i == 0) val = listSum.get(i);
            //第二个值
            double dev = listSum.get(i + 1);
            //判断运算符
            switch (listType.get(i)) {
                case "+":
                    val = val + dev;
                    break;
                case "-":
                    val = val - dev;
                    break;
            }
            System.out.print(((i == 0) ? listSum.get(i) : "") + listType.get(i) + dev);
        }

        System.out.println("加减:"+val);
        return val;
    }

    /**
     * 乘除运算
     * @param listSum 数字集合
     * @param listType 运算符集合
     * @return 结果值
     */
    public static Double divisionList(List<Double> listSum,List<String> listType){
        Double val = 0.0;
        for (int i = 0; i < listSum.size() - 1; i++) {
            if (i == 0) val = listSum.get(i);
            //第二个值
            double dev = listSum.get(i + 1);
            //判断运算符
            switch (listType.get(i)) {
                case "*":
                    val = val * dev;
                    break;
                case "/":
                    val = val / dev;
                    break;
            }
            System.out.print(((i == 0) ? listSum.get(i) : "") + listType.get(i) + dev);
        }
        System.out.println("乘除:"+val);
        return val;
    }

    public static Double add(String formula) {
        Double[] dus = toDousAdd(formula);
        Double end = dus[0];
        for (int i = 1; i < dus.length; i++) {
            end += dus[i];
        }
        return end;
    }

    public static Double reduce(String formula) {
        Double[] dus = toDousReduce(formula);

        Double end = dus[0];
        for (int i = 1; i < dus.length; i++) {
            end -= dus[i];
        }
        return end;
    }

    public static Double ride(String formula) {
        Double[] dus = toDousRide(formula);

        Double end = dus[0];
        for (int i = 1; i < dus.length; i++) {
            end = end * dus[i];
        }
        return end;
    }

    public static Double except(String formula) {
        Double[] dus = toDousExcept(formula);

        Double end = dus[0];
        for (int i = 1; i < dus.length; i++) {
            end = end / dus[i];
        }
        return end;
    }


    private static Double[] toDous(String formula, String type) {
        String[] strs = formula.split(type);
        Double[] dubs = new Double[strs.length];
        for (int i = 0; i < strs.length; i++) {
            dubs[i] = SysUtil.toDub(strs[i]);
        }
        return dubs;
    }

    private static Double[] toDousAdd(String formula) {
        return toDous(formula, "[+]");
    }

    private static Double[] toDousReduce(String formula) {
        return toDous(formula, "[-]");
    }

    private static Double[] toDousRide(String formula) {
        return toDous(formula, "[*]");
    }

    private static Double[] toDousExcept(String formula) {
        return toDous(formula, "[/]");
    }

}
