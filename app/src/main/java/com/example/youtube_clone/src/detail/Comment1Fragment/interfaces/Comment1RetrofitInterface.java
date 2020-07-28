package com.example.youtube_clone.src.detail.Comment1Fragment.interfaces;

import com.example.youtube_clone.src.detail.Comment1Fragment.models.CommentData;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.CommentIndex;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.DeleteCommentResponse;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.GetCommentResponse;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.NewCommentData;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.PatchCommentResponse;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.PostCommentResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Comment1RetrofitInterface {


    @GET("/videos/{idx}/comments")
    Call<GetCommentResponse> getCommentPathAndQuery(
            @Path("idx") int idx,
            @Query("page") final int page
    );

    @POST("/videos/{idx}/comments")
    Call<PostCommentResponse> postCommentPathAndQuery(
            @Path("idx") int idx,
            @Body CommentData body
    );

    @PATCH("/videos/{idx}/comments")
    Call<PatchCommentResponse> patchCommentPathAndQuery(
            @Path("idx") int idx,
            @Body NewCommentData body
    );

   @HTTP(method = "DELETE", path = "/videos/{idx}/comments", hasBody = true)
    Call<DeleteCommentResponse> deleteCommentPathAndQuery(
            @Path("idx") int idx,
            @Body CommentIndex body
    );




}
