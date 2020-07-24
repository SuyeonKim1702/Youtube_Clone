package com.example.youtube_clone.src.main;

import android.graphics.drawable.Drawable;
import android.provider.MediaStore;

import java.io.Serializable;
//
//상속 이용해서 변경 -> video, story ...
//서버에서 받아온 데이터를그대로 넣을 수 있음 -> 템플릿 잘못쓴 부분
public class RecyclerViewItem implements Serializable {
    private String mThumUrl;
    private String mTitle;
    private String mChannelName;
    private String mViewCount ;
    private String mUploadDate ;
    private int mType;
    private String mContentText;
    private String mImage;
    private String mThumbUpCount;
    private int mCommentCount;
    private String mProfileImage;
    private String mTimeline;

    public RecyclerViewItem() {
    }

    public void setTimeline(String timeline){ this.mTimeline = timeline ; }
    public void setThumUrl(String thumUrl) {
        this.mThumUrl = thumUrl ;
    }
    public void setTitle(String mTitle) {
       this.mTitle = mTitle;
    }
    public void setChannelName(String channelName) {
        this.mChannelName = channelName;
    }
    public void setUploadDate(String uploadDate) {
        this.mUploadDate = uploadDate;
    }
    public void setViewCount(String viewCount) {
        this.mViewCount = viewCount;
    }
    public void setContentText(String mContentText) { this.mContentText = mContentText; }
    public void setImage(String mImage) { this.mImage = mImage; }
    public void setCommentCount(int mCommentCount) { this.mCommentCount = mCommentCount; }
    public void setThumbUpCount(String mThumbUpCount) {
        this.mThumbUpCount = mThumbUpCount;
    }
    public void setType(int type) {
        this.mType = type;
    }
    public void setProfileImage(String mProfileImage) {
        this.mProfileImage = mProfileImage;
    }

    public String getmTimeline() { return this.mTimeline; }
    public String getThumUrl() { return this.mThumUrl; }
    public String getTitle() {
        return this.mTitle;
    }
    public String getUploadDate() {
        return this.mUploadDate ;
    }
    public String getViewCount() {
        return this.mViewCount ;
    }
    public String getChannelName() {
        return this.mChannelName;
    }
    public int getType() {
        return this.mType;
    }
    public String getContentText() { return mContentText; }
    public String getImage() { return mImage; }
    public String getThumbUpCount() { return mThumbUpCount; }
    public int getCommentCount() { return mCommentCount; }
    public String getProfileImage() { return mProfileImage; }

}