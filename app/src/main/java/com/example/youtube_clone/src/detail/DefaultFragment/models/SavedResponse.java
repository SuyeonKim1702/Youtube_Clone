package com.example.youtube_clone.src.detail.DefaultFragment.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SavedResponse implements Serializable {
    //SerializedName을 이용해서 json 파싱
    //server에서 주는 이름과 SerializeName을 같게 해두면 자동으로 parsing이 된다


    public boolean isSuccess() {
        return success;
    }

    @SerializedName("isSuccess")
    private boolean success;







    }

