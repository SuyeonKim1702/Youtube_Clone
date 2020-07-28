package com.example.youtube_clone.src.detail.Comment1Fragment.interfaces;


import com.example.youtube_clone.src.detail.Comment1Fragment.models.DeleteCommentResponse;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.GetCommentResponse;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.PatchCommentResponse;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.PostCommentResponse;


public interface Comment1ActivityView {

    void validateSuccess(DeleteCommentResponse result);

    void validateSuccess(PatchCommentResponse result);

    void validateSuccess(PostCommentResponse result);

    void validateSuccess(GetCommentResponse.Result[] result);

    void validateFailure(String message);

    void validateFailure2(GetCommentResponse message);


}
