package com.example.youtube_clone.src.main.Home.interfaces;

import com.example.youtube_clone.src.main.Home.models.DefaultResponse;

public interface MainActivityView {

    void validateSuccess(DefaultResponse.Result result, int pageNum);

    void validateFailure(String message);


}
