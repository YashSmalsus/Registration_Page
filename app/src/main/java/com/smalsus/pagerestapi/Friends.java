package com.smalsus.pagerestapi;

import android.content.Intent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Friends
{
    @SerializedName("uId")
    @Expose
    private String uId;
    @SerializedName("associateList")
    @Expose
    private List<FriendsModel> associateList;

    public Friends(String uId, List<FriendsModel> associateList) {
        this.uId = uId;
        this.associateList = associateList;
    }

    public String getuId() {
        return uId;
    }

    public List<FriendsModel> getAssociateList() {
        return associateList;
    }
}
