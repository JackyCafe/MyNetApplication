package com.ian.myapplication;

import java.io.Serializable;

public class SiteName implements Serializable {
    public String name;
    public String address;
    public String lon;
    public String lat;

    public SiteName(String name, String address, String lon, String lat) {
        this.name = name;
        this.address = address;
        this.lon = lon;
        this.lat = lat;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getLon() {
        return lon;
    }

    public String getLat() {
        return lat;
    }

    @Override
    public String toString() {
        return "SiteName{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", lon='" + lon + '\'' +
                ", lat='" + lat + '\'' +
                '}';
    }
}
