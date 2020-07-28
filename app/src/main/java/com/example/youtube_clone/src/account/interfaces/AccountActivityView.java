package com.example.youtube_clone.src.account.interfaces;


import com.example.youtube_clone.src.account.models.PostIdTokenResponse;



public interface AccountActivityView {

    void validateSuccess(PostIdTokenResponse result);

    void validateFailure(String message);


}
