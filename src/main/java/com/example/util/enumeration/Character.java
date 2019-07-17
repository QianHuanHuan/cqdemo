package com.example.util.enumeration;

public enum  Character {

    GBK("GBK"),
    UTF8("UTF-8"),
    GB2312("GB2312"),
    ISO("ISO-8859-1");

    private String chara;

    public String getChara(){
        return chara;
    }

    Character(String chara) {
        this.chara = chara;
    }
}
