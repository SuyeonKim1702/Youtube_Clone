package com.example.youtube_clone.src.account.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PostIdTokenResponse implements Serializable {
    //SerializedName을 이용해서 json 파싱
    //server에서 주는 이름과 SerializeName을 같게 해두면 자동으로 parsing이 된다

    public class Result implements Serializable{

        public int getUserIdx() {
            return userIdx;
        }
        @SerializedName("userIdx")
        int userIdx;

        public int getCommentsIdx() {
            return commentsIdx;
        }
        @SerializedName("CommentsIdx")
        int commentsIdx;

        public int getVideoIdx() {
            return videoIdx;
        }

        @SerializedName("VideoIdx")
        int videoIdx;

        public String getCommentText() {
            return commentText;
        }

        @SerializedName("commentText")
        String commentText;

        public int getLikesCount() {
            return likesCount;
        }

        @SerializedName("LikesCount")
        int likesCount;

        public int getLikesStatus() {
            return likesStatus;
        }

        @SerializedName("LikesStatus")
        int likesStatus;

        public int getCmtReplyCount() {
            return cmtReplyCount;
        }

        @SerializedName("CmtReplyCount")
        int cmtReplyCount;

    }

    @SerializedName("result")
    private Result[] result;

    public Result[] getResult() { return result;}

}

