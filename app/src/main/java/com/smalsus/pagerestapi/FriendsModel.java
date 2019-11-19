package com.smalsus.pagerestapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FriendsModel
{
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("fName")
    @Expose
    private String fName;
    @SerializedName("lName")
    @Expose
    private String lName;
    @SerializedName("gender")
    @Expose
    private int gender;
    @SerializedName("emailID")
    @Expose
    private String emailID;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("videoId")
    @Expose
    private String videoId;
    @SerializedName("phoneNo")
    @Expose
    private String phoneNo;
    @SerializedName("location")
    @Expose
    private FriendsLocationModel friendsLocationModel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public FriendsLocationModel getFriendsLocationModel() {
        return friendsLocationModel;
    }

    public void setFriendsLocationModel(FriendsLocationModel friendsLocationModel) {
        this.friendsLocationModel = friendsLocationModel;
    }
}
