package com.example.youtube_clone.src.detail;

public class SubComment {

    private String mCommentContent;
    private int ThumbUpOrNot;
    private String mChannelName;
    private String mUploadDate ;
    private int mThumbUpCount;
    private String mProfileImage;
    private int mCommentIdx;

    public int getmReplyIdx() {
        return mReplyIdx;
    }

    public void setmReplyIdx(int mReplyIdx) {
        this.mReplyIdx = mReplyIdx;
    }

    private int mReplyIdx;

    public int getmVideoIdx() {
        return mVideoIdx;
    }

    public void setmVideoIdx(int mVideoIdx) {
        this.mVideoIdx = mVideoIdx;
    }

    private int mVideoIdx;

    public int getmCommentIdx() {
        return mCommentIdx;
    }

    public void setmCommentIdx(int mCommentIdx) {
        this.mCommentIdx = mCommentIdx;
    }




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
