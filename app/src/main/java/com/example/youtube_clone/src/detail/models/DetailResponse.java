package com.example.youtube_clone.src.detail.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DetailResponse implements Serializable {
    //SerializedName을 이용해서 json 파싱
    //server에서 주는 이름과 SerializeName을 같게 해두면 자동으로 parsing이 된다

    public class Result implements Serializable{
        @SerializedName("videoInfo")
        VideoInfo videoInfo;

        public VideoInfo getVideoInfo() {
            return videoInfo;
        }

    }


    public class VideoInfo implements Serializable{
        public int getVideoIdx() {
            return videoIdx;
        }
        @SerializedName("VideoIdx")
        private int videoIdx;

        public int getUserIdx() {
            return userIdx;
        }
        @SerializedName("UserIdx")
        private int userIdx;

        public String getUserId() {
            return userId;
        }
        @SerializedName("UserId")
        private String userId;

        public String getTitleText() {
            return titleText;
        }

        @SerializedName("TitleText")
        private String titleText;

        public String getMainText() {
            return mainText;
        }

        @SerializedName("MainText")
        private String mainText;

        public int getViews() {
            return views;
        }

        @SerializedName("Views")
        private int views;

        public int getLikesCount() {
            return likesCount;
        }

        @SerializedName("LikesCount")
        private int likesCount;

        public int getDislikesCount() {
            return dislikesCount;
        }

        @SerializedName("DislikesCount")
        private int dislikesCount;

        public int getSubscribeCount() {
            return subscribeCount;
        }

        @SerializedName("SubscribeCount")
        private int subscribeCount;

        public int getCommentsCount() {
            return commentsCount;
        }

        @SerializedName("CommentsCount")
        private int commentsCount;

        public int getLikesStatus() {
            return LikesStatus;
        }

        @SerializedName("LikeStatus")
        private int LikesStatus;

        public String getVideoUrl() {
            return videoUrl;
        }

        @SerializedName("VideoUrl")
        private String videoUrl;

        public String getProfileUrl() {
            return ProfileUrl;
        }

        @SerializedName("ProfileUrl")
        private String ProfileUrl;

        public String getCreatedAt() {
            return createdAt;
        }

        @SerializedName("CreatedAt")
        private String createdAt;




    }

    @SerializedName("result")
    private Result result;

    public Result getResult() { return result;}





    }

