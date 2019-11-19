package com.smalsus.pagerestapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FriendsLocationModel {
    @SerializedName("long")
    @Expose
    private double longitude;
    @SerializedName("lat")
    @Expose
    private double latitude;
    @SerializedName("user_country")
    @Expose
    private String user_country;
    @SerializedName("user_city")
    @Expose
    private String user_city;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getUser_country() {
        return user_country;
    }

    public void setUser_country(String user_country) {
        this.user_country = user_country;
    }

    public String getUser_city() {
        return user_city;
    }

    public void setUser_city(String user_city) {
        this.user_city = user_city;
    }
}
