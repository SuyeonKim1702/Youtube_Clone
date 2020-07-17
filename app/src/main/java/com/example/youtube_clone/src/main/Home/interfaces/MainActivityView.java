package com.example.youtube_clone.src.main.Home.interfaces;

import com.example.youtube_clone.src.main.Home.models.SignInResponse;

public interface MainActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void signInSuccess(SignInResponse.SignInResult signInResult);
}
