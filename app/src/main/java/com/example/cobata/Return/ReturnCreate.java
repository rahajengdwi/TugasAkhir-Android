package com.example.cobata.Return;

import com.google.gson.annotations.SerializedName;

public class ReturnCreate {
    @SerializedName("status")
    String status;

    @SerializedName("message")
    String message;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
