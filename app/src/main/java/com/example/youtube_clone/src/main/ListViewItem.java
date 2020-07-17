package com.example.youtube_clone.src.main;

import android.graphics.drawable.Drawable;
import android.provider.MediaStore;

public class ListViewItem {
    private Drawable profileImage ;
    private String title ;
    private String channelName ;
    private String viewCount ;
    private String uploadDate ;
    private MediaStore.Video video;

    public void setProfileImage(Drawable profileImage) {
        this.profileImage = profileImage ;
    }
    public void setTitle(String title) {
       this.title = title ;
    }
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }
    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public Drawable getProfileImage() {
        return this.profileImage ;
    }
    public String getTitle() {
        return this.title ;
    }
    public String getUploadDate() {
        return this.uploadDate ;
    }
    public String getViewCount() {
        return this.viewCount ;
    }
    public String getChannelName() {
        return this.channelName ;
    }
}