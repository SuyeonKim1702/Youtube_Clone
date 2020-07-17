package com.example.youtube_clone.src.main.models;

import com.google.gson.annotations.SerializedName;

public class SignInBody {
    //SerializedName을 이용해서 json 파싱
    //server에서 주는 이름과 SerializeName을 같게 해두면 자동으로 parsing이 된다
    @SerializedName("id")
    private String id;

    @SerializedName("message")
    private String message;

    public SignInBody(String id, String message) {
        this.id = id;
        this.message = message;
    }
}