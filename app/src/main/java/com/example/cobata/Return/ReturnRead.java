package com.example.cobata.Return;

import com.google.gson.annotations.SerializedName;

public class ReturnRead {
    @SerializedName("id")
    String id;

    @SerializedName("name")
    String nama;

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @SerializedName("image")
    String image;

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getImage() {
        return image;
    }
}
