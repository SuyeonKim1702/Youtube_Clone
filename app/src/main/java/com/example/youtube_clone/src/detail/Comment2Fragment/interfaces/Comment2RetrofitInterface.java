package com.example.youtube_clone.src.detail.Comment2Fragment.interfaces;

import com.example.youtube_clone.src.detail.Comment1Fragment.models.CommentData;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.CommentIndex;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.DeleteCommentResponse;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.GetCommentResponse;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.NewCommentData;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.PatchCommentResponse;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.PostCommentResponse;
import com.example.youtube_clone.src.detail.Comment2Fragment.models.DeleteReplyResponse;
import com.example.youtube_clone.src.detail.Comment2Fragment.models.GetReplyResponse;
import com.example.youtube_clone.src.detail.Comment2Fragment.models.NewReplyData;
import com.example.youtube_clone.src.detail.Comment2Fragment.models.PatchReplyResponse;
import com.example.youtube_clone.src.detail.Comment2Fragment.models.PostReplyResponse;
import com.example.youtube_clone.src.detail.Comment2Fragment.models.ReplyData;
import com.example.youtube_clone.src.detail.Comment2Fragment.models.ReplyIndex;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Comment2RetrofitInterface {


    @GET("/videos/{videoIdx}/comments/{commentIdx}")
    Call<GetReplyResponse> getReplyPathAndQuery(
            @Path("videoIdx") int videoIdx,
            @Path("commentIdx") int commentIdx,
            @Query("page") final int page
    );

    @POST("/videos/{videoIdx}/comments/{commentIdx}")
    Call<PostReplyResponse> postReplyPathAndQuery(
            @Path("videoIdx") int videoIdx,
            @Path("commentIdx") int commentIdx,
            @Body ReplyData body
    );

    @PATCH("/videos/{videoIdx}/comments/{commentIdx}")
    Call<PatchReplyResponse> patchReplyPathAndQuery(
            @Path("videoIdx") int videoIdx,
            @Path("commentIdx") int commentIdx,
            @Body NewReplyData body
    );

   @HTTP(method = "DELETE", path = "/videos/{videoIdx}/comments/{commentIdx}", hasBody = true)
    Call<DeleteReplyResponse> deleteReplyPathAndQuery(
           @Path("videoIdx") int videoIdx,
           @Path("commentIdx") int commentIdx,
           @Body ReplyIndex body
   );




}
