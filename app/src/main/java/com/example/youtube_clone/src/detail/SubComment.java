package com.example.youtube_clone.src.detail;

public class SubComment {

    private String mCommentContent;
    private int ThumbUpOrNot;
    private String mChannelName;
    private String mUploadDate ;
    private int mThumbUpCount;
    private String mProfileImage;


    public int isThumbUpOrNot() {
        return ThumbUpOrNot;
    }

    public void setThumbUpOrNot(int thumbUpOrNot) { //0 미설정 1 좋아요 2 싫어요
        ThumbUpOrNot = thumbUpOrNot;
    }




    public String getmChannelName() {
        return mChannelName;
    }

    public void setmChannelName(String mChannelName) {
        this.mChannelName = mChannelName;
    }

    public String getmUploadDate() {
        return mUploadDate;
    }

    public void setmUploadDate(String mUploadDate) {
        this.mUploadDate = mUploadDate;
    }



    public int getmThumbUpCount() {
        return mThumbUpCount;
    }

    public void setmThumbUpCount(int mThumbUpCount) {
        this.mThumbUpCount = mThumbUpCount;
    }



    public String getmProfileImage() {
        return mProfileImage;
    }

    public void setmProfileImage(String mProfileImage) {
        this.mProfileImage = mProfileImage;
    }

    public String getmCommentContent() {
        return mCommentContent;
    }

    public void setmCommentContent(String mCommentContent) {
        this.mCommentContent = mCommentContent;
    }


}
