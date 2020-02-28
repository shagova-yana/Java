package com.company;

import com.sun.jndi.cosnaming.IiopUrl;

import java.net.MalformedURLException;

public class Address extends IiopUrl.Address {
    private String street;
    private String home;
    private String room;

    public Address(String street, String home, String room) {
        this.street = street;
        this.home = home;
        this.room = room;
    }

    public String getHome() {
        return home;
    }
    public String getRoom() {
        return room;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public void setHome(String home) {
        int h = Integer.parseInt(home.trim());
        if (h > 0)
            this.home = home;
        else throw new NumberFormatException();
    }
    public void setRoom(String room) {
        int r = Integer.parseInt(room.trim());
        if (r > 0)
            this.room = room;
        else throw new NumberFormatException();
    }
}
