package com.example.youtube_clone.src.splash.interfaces;

import com.example.youtube_clone.src.splash.models.DefaultResponse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainRetrofitInterface {

    @GET("/videos")
    Call<DefaultResponse> getVideoPathAndQuery(
            @Query("page") final int page
    );


}
