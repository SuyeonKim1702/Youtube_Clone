package com.example.youtube_clone.src.main.Home.interfaces;

import com.example.youtube_clone.src.main.Home.models.CommunityResponse;
import com.example.youtube_clone.src.main.Home.models.DefaultResponse;
import com.example.youtube_clone.src.main.Home.models.StoryResponse;

public interface MainActivityView {

    void validateSuccess(DefaultResponse.Result result, int pageNum);

    void validateSuccess(StoryResponse.Result result, int pageNum);

    void validateSuccess(CommunityResponse.Result result, int pageNum);

    void validateFailure(String message);


}
