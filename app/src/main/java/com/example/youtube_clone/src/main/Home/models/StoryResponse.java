package com.example.youtube_clone.src.main.Home.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StoryResponse implements Serializable {
    //SerializedName을 이용해서 json 파싱
    //server에서 주는 이름과 SerializeName을 같게 해두면 자동으로 parsing이 된다

    public class Result implements Serializable{

        @SerializedName("storyVideo")
        Story[] story;

        public Story[] getStory() {
            return story;
        }


    }


    public class Story implements Serializable{

        @SerializedName("UserId")
        private String channelName;
        @SerializedName("ProfileUrl")
        private String profileUrl;
        @SerializedName("ThumUrl")
        private String thumUrl;
        //추가해줄 것

        public String getChannelName() { return channelName; }

        public String getThumbUrl() {
            return thumUrl;
        }
        public String getProfileUrl() {
            return profileUrl;
        }

    }

    @SerializedName("result")
    private Result result;

    public Result getResult() { return result;}





}