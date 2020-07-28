package com.example.youtube_clone.src.detail.DefaultFragment.interfaces;

import com.example.youtube_clone.src.detail.Comment1Fragment.models.GetCommentResponse;
import com.example.youtube_clone.src.detail.DefaultFragment.models.LikesResponse;
import com.example.youtube_clone.src.detail.DefaultFragment.models.DetailResponse;
import com.example.youtube_clone.src.detail.DefaultFragment.models.SavedResponse;
import com.example.youtube_clone.src.detail.DefaultFragment.models.Subscribe;
import com.example.youtube_clone.src.detail.DefaultFragment.models.SubscribeResponse;
import com.example.youtube_clone.src.detail.models.Likes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DefaultRetrofitInterface {

    @PATCH("/videos/{idx}/likes")
    Call<LikesResponse> patchLikes(
            @Path("idx") int idx,
            @Body Likes body

    );

    @GET("/videos/{idx}")
    Call<DetailResponse> getDetailVideoPath(
            @Path("idx") int idx
    );


    @POST("/saved-videos/{idx}")
    Call<SavedResponse> postSaved(
            @Path("idx") int idx

    );

    @PATCH("/users/{idx}/subscribe")
    Call<SubscribeResponse> patchSubscribed(
            @Path("idx") int idx,
            @Body Subscribe body
    );


    @GET("/videos/{idx}/comments")
    Call<GetCommentResponse> getOneComment(
            @Path("idx") int idx,
            @Query("page") final int page,
            @Query("filter") final String filter
    );


}
