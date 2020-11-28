package com.example.snowtamdecoder;

public class SnowtamHash {

    private String code;
    private String value;
    private int icon;

    public SnowtamHash(String code, String value,int icon) {
        this.code = code;
        this.value = value;
        this.icon=icon;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "SnowtamHash{" +
                "code='" + code + '\'' +
                ", value='" + value + '\'' +
                ", icon=" + icon +
                '}';
    }
}
