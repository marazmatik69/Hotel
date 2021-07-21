package com.example.hotel.Models;

public class Reservation {
    private String clientId;
    private String roomId;
    private String first;
    private String last;
    private String clientN;
    private String allDate;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getClientN() {
        return clientN;
    }

    public void setClientN(String clientN) {
        this.clientN = clientN;
    }

    public String getAllDate() {
        return allDate;
    }

    public void setAllDate(String allDate) {
        this.allDate = allDate;
    }

    public Reservation(String clientId, String roomId, String first, String last, String clientN, String allDate) {
        this.clientId = clientId;
        this.roomId = roomId;
        this.first = first;
        this.last = last;
        this.clientN = clientN;
        this.allDate = allDate;
    }
}
