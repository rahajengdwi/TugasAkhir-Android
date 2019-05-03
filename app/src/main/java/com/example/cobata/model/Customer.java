package com.example.cobata.model;

public class Customer {
    String id;
    String name;
    String gambar;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public Customer() {
    }

    public Customer(String id, String name, String gambar) {
        this.id = id;
        this.name = name;
        this.gambar = gambar;
    }
}
