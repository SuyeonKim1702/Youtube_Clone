package com.example.youtube_clone.src.detail.DefaultFragment.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Subscribe{
    int channelUserIdx;
    public Subscribe(int channelUserIdx){
        this.channelUserIdx = channelUserIdx;
    }
}