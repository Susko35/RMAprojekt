package com.susnjara.rmaprojekt;

import com.google.gson.annotations.SerializedName;

public class Translate {

    @SerializedName("en")
    private String en;
    @SerializedName("def")
    private String def;

    public Translate(String data) {
        this.en = en;
        this.def = def;
    }

    public String getEn() {
        return en;
    }
    public String getDef() {
        return def;
    }
}
