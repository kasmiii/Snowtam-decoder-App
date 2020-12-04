package com.example.snowtamdecoder;

public class SnowtamDecode {

    private String info;
    private String infoDecode;
    private int flag;

    public SnowtamDecode(String info, String infoDecode, int flag) {
        this.info = info;
        this.infoDecode = infoDecode;
        this.flag = flag;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfoDecode() {
        return infoDecode;
    }

    public void setInfoDecode(String infoDecode) {
        this.infoDecode = infoDecode;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "SnowtamDecode{" +
                "info='" + info + '\'' +
                ", infoDecode='" + infoDecode + '\'' +
                ", flag=" + flag +
                '}';
    }
}
