package com.example.youtube_clone.src.main.Home.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DefaultResponse implements Serializable {
    //SerializedName을 이용해서 json 파싱
    //server에서 주는 이름과 SerializeName을 같게 해두면 자동으로 parsing이 된다

    public class Result implements Serializable{
        @SerializedName("video")
        Video[] video;

        public Video[] getVideo() {
            return video;
        }

    }


    public class Video implements Serializable{
        @SerializedName("VideoIdx")
        private int videoIdx;
        @SerializedName("TitleText")
        private String titleText;
        @SerializedName("UserId")
        private String channelName;
        @SerializedName("Views")
        private String ViewCount;
        @SerializedName("ThumUrl")
        private String ThumUrl;
        @SerializedName("ProfileUrl")
        private String profileUrl;
        @SerializedName("CreateAt")
        private String createAt;
        @SerializedName("PlayTime")
        private String playTime;

        public int getVideoIndex() {
            return videoIdx;
        }
        public String getTitle() {
            return titleText;
        }
        public String getChannelName() {
            return channelName;
        }
        public String getViewCount() {
            return ViewCount;
        }
        public String getCreateAt() {
            return createAt;
        }
        public String getThumUrl() {
            return ThumUrl;
        }
        public String getProfileUrl() {
            return profileUrl;
        }
        public String getPlayTime() { return playTime; }
    }

    @SerializedName("result")
    private Result result;

    public Result getResult() { return result;}





}