package com.example.youtube_clone.src.main;

import android.graphics.drawable.Drawable;
import android.provider.MediaStore;

import java.io.Serializable;

public class ListViewItem implements Serializable {
    private String mThumUrl;
    private String mTitle;
    private String mChannelName;
    private String mViewCount ;
    private String mUploadDate ;
    private int mType;

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

    public void setType(int type) {
        this.mType = type;
    }

    public String getThumUrl() {
        return this.mThumUrl;
    }
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
}