package com.susnjara.rmaprojekt;

import com.google.gson.annotations.SerializedName;

public class Translate {

    @SerializedName("en")
    private String en;
    @SerializedName("def")
    private String def;
    @SerializedName("error")
    private String error;

    public Translate(String en, String def, String error) {
        this.error = error;
        this.en = en;
        this.def = def;
    }

    public String getEn() {
        return en;
    }
    public String getDef() {
        return def;
    }

    public String getError() {
        return error;
    }
}
