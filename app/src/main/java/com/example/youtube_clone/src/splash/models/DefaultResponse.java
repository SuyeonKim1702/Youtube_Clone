package com.example.youtube_clone.src.splash.models;

import android.view.View;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DefaultResponse implements Serializable {
    //SerializedName을 이용해서 json 파싱
    //server에서 주는 이름과 SerializeName을 같게 해두면 자동으로 parsing이 된다

    public class Result implements Serializable{
        @SerializedName("video")
        Video[] video;

        @SerializedName("community")
        Community[] community;

        @SerializedName("storyVideo")
        Story[] story;

        public Video[] getVideo() {
            return video;
        }

        public Community[] getCommunity() {
            return community;
        }

        public Story[] getstory() {
            return story;
        }


    }

    public class Community implements Serializable{

        @SerializedName("UserId")
        private String channelName;
        @SerializedName("CreateAt")
        private String createAt;
        @SerializedName("MainText")
        private String mainText;
        @SerializedName("LikesCount")
        private String likesCount;
        @SerializedName("ImgUrl")
        private String imgUrl;
        @SerializedName("ProfileUrl")
        private String profileUrl;
        @SerializedName("CommentCount")
        private int commentCount;
        //추가해줄 것

        public String getChannelName() { return channelName; }
        public String getCreateAt() {
            return createAt;
        }
        public String getMainText() {
            return mainText;
        }
        public String getLikesCount() {
            return likesCount;
        }
        public String getImgUrl() {
            return imgUrl;
        }
        public String getProfileUrl() {
            return profileUrl;
        }
        public int getCommentCount() {
            return commentCount;
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


    public class Video implements Serializable{
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
        public String getPlayTime() {
            return playTime;
        }

    }

    @SerializedName("result")
    private Result result;

    public Result getResult() { return result;}





    }

