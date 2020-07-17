package com.example.youtube_clone.src.main.Home.models;

import com.google.gson.annotations.SerializedName;

public class SignInResponse {
    // 서버에서 제공하는 response가 단일 형태가 아닐 경우, 30분 부터 참고
  public class SignInResult{

        @SerializedName("jwt")
        private String jwt;

        @SerializedName("message")
        private String message;
        public String getMessage() {
            return message;
        }
    }

    //SerializedName을 이용해서 json 파싱
    //server에서 주는 이름과 SerializeName을 같게 해두면 자동으로 parsing이 된다
    @SerializedName("code")
    private int code;

    @SerializedName("Message")
    private String message;

    @SerializedName("result")
    private SignInResult result;

 ;

    public int get() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public SignInResult getResult(){ return result; }

}