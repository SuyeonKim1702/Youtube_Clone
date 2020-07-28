package com.example.youtube_clone.src.detail.Comment2Fragment.interfaces;


import com.example.youtube_clone.src.detail.Comment1Fragment.models.DeleteCommentResponse;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.GetCommentResponse;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.PatchCommentResponse;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.PostCommentResponse;
import com.example.youtube_clone.src.detail.Comment2Fragment.models.DeleteReplyResponse;
import com.example.youtube_clone.src.detail.Comment2Fragment.models.GetReplyResponse;
import com.example.youtube_clone.src.detail.Comment2Fragment.models.PatchReplyResponse;
import com.example.youtube_clone.src.detail.Comment2Fragment.models.PostReplyResponse;


public interface Comment2ActivityView {

    void validateSuccess(DeleteReplyResponse result);

    void validateSuccess(PatchReplyResponse result);

    void validateSuccess(PostReplyResponse result);

    void validateSuccess(GetReplyResponse.Result[] result);

    void validateFailure(String message);




}
