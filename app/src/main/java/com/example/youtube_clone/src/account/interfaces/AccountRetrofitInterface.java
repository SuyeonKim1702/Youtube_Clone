package com.example.youtube_clone.src.account.interfaces;

import com.example.youtube_clone.src.account.models.PostIdTokenResponse;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.GetCommentResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AccountRetrofitInterface {


    @POST("/users")
    Call<PostIdTokenResponse> postIdToken(
            @Body String commentsText
    );



}
