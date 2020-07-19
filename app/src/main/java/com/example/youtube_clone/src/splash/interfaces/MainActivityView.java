package com.example.youtube_clone.src.splash.interfaces;

import com.example.youtube_clone.src.splash.models.DefaultResponse;

public interface MainActivityView {

    void validateSuccess(DefaultResponse.Result result);

    void validateFailure(String message);


}
