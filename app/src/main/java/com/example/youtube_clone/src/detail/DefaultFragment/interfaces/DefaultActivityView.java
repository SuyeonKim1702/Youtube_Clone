package com.example.youtube_clone.src.detail.DefaultFragment.interfaces;


import com.example.youtube_clone.src.detail.Comment1Fragment.models.GetCommentResponse;
import com.example.youtube_clone.src.detail.DefaultFragment.models.LikesResponse;
import com.example.youtube_clone.src.detail.DefaultFragment.models.DetailResponse;
import com.example.youtube_clone.src.detail.DefaultFragment.models.SavedResponse;
import com.example.youtube_clone.src.detail.DefaultFragment.models.SubscribeResponse;
import com.example.youtube_clone.src.main.Home.models.DefaultResponse;
import com.example.youtube_clone.src.main.Home.models.StoryResponse;

import retrofit2.Call;


public interface DefaultActivityView {

    void validateSuccess(GetCommentResponse.Result[] result);

    void validateSuccess(SubscribeResponse subscribeResponse);

    void validateSuccess(SavedResponse savedResponse);

    void validateSuccess(DefaultResponse.Result result);

    void validateSuccess(StoryResponse.Result result);

    void validateSuccess(DetailResponse.Result result);

    void validateSuccess(LikesResponse response);

    void validateFailure(String message);

}
