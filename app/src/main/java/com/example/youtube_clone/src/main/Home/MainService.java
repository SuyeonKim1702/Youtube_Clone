package com.example.youtube_clone.src.main.Home;

import com.example.youtube_clone.src.main.Home.interfaces.MainActivityView;
import com.example.youtube_clone.src.main.Home.interfaces.MainRetrofitInterface;
import com.example.youtube_clone.src.main.Home.models.DefaultResponse;
import com.example.youtube_clone.src.main.Home.models.SignInBody;
import com.example.youtube_clone.src.main.Home.models.SignInResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.youtube_clone.src.ApplicationClass.getRetrofit;

class MainService {
    private final MainActivityView mMainActivityView;

    MainService(final MainActivityView mainActivityView) {
        this.mMainActivityView = mainActivityView;
    }



//로그인 함수를 새로 만들었음



}
