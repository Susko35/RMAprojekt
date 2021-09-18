package com.susnjara.rmaprojekt;

import com.google.gson.annotations.SerializedName;

public class Definition {

    @SerializedName("data")
    private String data;

    public Definition(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
