package com.example.youtube_clone.src.detail.Comment2Fragment.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PostReplyResponse implements Serializable {
    //SerializedName을 이용해서 json 파싱
    //server에서 주는 이름과 SerializeName을 같게 해두면 자동으로 parsing이 된다

    public class Result implements Serializable{


        public int getReplyIdx() {
            return replyIdx;
        }

        @SerializedName("replyIdx")
        int replyIdx;

        public String getReplyText() {
            return replyText;
        }

        @SerializedName("replyText")
        String replyText;

    }

    @SerializedName("result")
    private Result result;

    @SerializedName("message")
    private String message;


    public String getMessage() { return message;}
    public Result getResult() { return result;}

}

