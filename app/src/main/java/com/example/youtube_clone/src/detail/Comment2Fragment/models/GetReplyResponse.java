package com.example.youtube_clone.src.detail.Comment2Fragment.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetReplyResponse implements Serializable {
    //SerializedName을 이용해서 json 파싱
    //server에서 주는 이름과 SerializeName을 같게 해두면 자동으로 parsing이 된다

    public class Result implements Serializable{

        public int getUserIdx() {
            return userIdx;
        }
        @SerializedName("userIdx")
        int userIdx;

        public int getReplyIdx() {
            return replyIdx;
        }
        @SerializedName("CmtReplyIdx")
        int replyIdx;

        public int getCommentsIdx() {
            return commentsIdx;
        }

        @SerializedName("commentsIdx")
        int commentsIdx;

        public int getVideoIdx() {
            return videoIdx;
        }

        @SerializedName("VideoIdx")
        int videoIdx;

        public String getReplyText() {
            return replyText;
        }

        @SerializedName("ReplyText")
        String replyText;

        /*
        public String getUserId() {
            return userId;
        }

        @SerializedName("UserId")
        String userId;      */

        public int getLikesCount() {
            return likesCount;
        }

        @SerializedName("LikesCount")
        int likesCount;



        /*
        public String getProfileUrl() {
            return profileUrl;
        }

        @SerializedName("ProfileUrl")
        String profileUrl;    */

        /*
        public String getCreatedAt() {
            return createdAt;
        }

        @SerializedName("CreatedAt")
        String createdAt;    */

    }

    @SerializedName("result")
    private Result[] result;

    @SerializedName("code")
    private int code;

    public int getCode(){ return code; }

    public Result[] getResult() { return result;}

}

