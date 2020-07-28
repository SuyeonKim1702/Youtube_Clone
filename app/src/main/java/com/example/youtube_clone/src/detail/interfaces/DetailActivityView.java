package com.example.youtube_clone.src.detail.interfaces;


import com.example.youtube_clone.src.detail.models.DetailResponse;

public interface DetailActivityView {

    void validateSuccess(DetailResponse.Result result);

    void validateFailure(String message);


}
