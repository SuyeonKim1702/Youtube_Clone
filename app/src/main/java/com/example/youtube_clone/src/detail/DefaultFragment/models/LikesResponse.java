package com.example.youtube_clone.src.detail.DefaultFragment.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LikesResponse implements Serializable {
    //SerializedName을 이용해서 json 파싱
    //server에서 주는 이름과 SerializeName을 같게 해두면 자동으로 parsing이 된다

    public class Result implements Serializable{
        @SerializedName("userIdx")
        int userIdx;

        @SerializedName("videoIdx")
        int videoIdx;

        public int getUserIdx() {
            return userIdx;
        }
        public int getVideoIdx() {
            return videoIdx;
        }

    }

    @SerializedName("result")
    private Result result;

    @SerializedName("isSuccess")
    private Boolean success;

    public Result getResult() { return result;}
    public boolean IsSuccess() {return success;}





    }

