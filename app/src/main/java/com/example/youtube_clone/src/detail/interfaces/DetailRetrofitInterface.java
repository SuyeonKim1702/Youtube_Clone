package com.example.youtube_clone.src.detail.interfaces;

import com.example.youtube_clone.src.detail.models.DetailResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DetailRetrofitInterface {

    @GET("/videos/{idx}")
    Call<DetailResponse> getDetailVideoPath(
            @Path("idx") int idx
    );


}
