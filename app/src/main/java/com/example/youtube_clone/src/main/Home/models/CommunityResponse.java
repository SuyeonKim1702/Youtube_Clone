package com.example.youtube_clone.src.main.Home.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CommunityResponse implements Serializable {
    //SerializedName을 이용해서 json 파싱
    //server에서 주는 이름과 SerializeName을 같게 해두면 자동으로 parsing이 된다

    public class Result implements Serializable{

        @SerializedName("community")
       Community[] community;


       public Community[] getCommunity() {
            return community;
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


    @SerializedName("result")
    private Result result;

    public Result getResult() { return result;}





}