package com.example.youtube_clone.src.main.interfaces;

import com.example.youtube_clone.src.main.models.DefaultResponse;
import com.example.youtube_clone.src.main.models.SignInBody;
import com.example.youtube_clone.src.main.models.SignInResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainRetrofitInterface {
    //    @GET("/test")
    @GET("/test")  //get 방식이고 response의 type은 DefaultResponse이다
    Call<DefaultResponse> getTest();

    @GET("/test/{number}")
    Call<DefaultResponse> getTestPathAndQuery(
            @Path("number") int number,
            @Query("content") final String content
    );

    @POST("/test")
    Call<DefaultResponse> postTest(@Body RequestBody params); //requestbody는 okhttp에서 자동으로 제공해주는 애

    @POST("/user/token")
    Call<SignInResponse> signInTest(@Body SignInBody params); //SignInBody는 커스텀해서 만든 애, 서버에 넘겨주는 값의 타입
}
