package com.example.youtube_clone.src.splash.models;

import android.view.View;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DefaultResponse implements Serializable {
    //SerializedName을 이용해서 json 파싱
    //server에서 주는 이름과 SerializeName을 같게 해두면 자동으로 parsing이 된다

    public class Result implements Serializable{
        @SerializedName("TitleText")
        private String titleText;
        @SerializedName("UserId")
        private String channelName;
        @SerializedName("Views")
        private String ViewCount;
        @SerializedName("ThumUrl")
        private String ThumUrl;
        //추가해줄 것
        private String UploadDate;

        public String getTitle() {
            return titleText;
        }
        public String getChannelName() {
            return channelName;
        }
        public String getViewCount() {
            return ViewCount;
        }
        public String getUploadDate() {
            return UploadDate;
        }
        public String getThumUrl() {
            return ThumUrl;
        }
    }

    @SerializedName("result")
    private Result[] result;

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public Result[] getResult() {
        return result;
    }

}