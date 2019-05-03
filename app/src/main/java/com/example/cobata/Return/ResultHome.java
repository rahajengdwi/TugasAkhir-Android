package com.example.cobata.Return;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultHome {
    public List<ReturnRead> getResult() {
        return result;
    }

    @SerializedName("result")
    List<ReturnRead> result;

}
