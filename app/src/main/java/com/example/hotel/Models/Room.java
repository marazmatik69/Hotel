package com.example.hotel.Models;

public class Room {
    private String number;
    private String category;
    private String price;
    private String n;
    private String info;
    private String url;

    public Room() {
    }

    public Room(String category, String price, String n, String info, String url) {
        this.number = number;
        this.category = category;
        this.price = price;
        this.n = n;
        this.info = info;
        this.url = url;

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
