package com.example.youtube_clone.src.splash;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.example.youtube_clone.R;
import com.example.youtube_clone.src.BaseActivity;
import com.example.youtube_clone.src.splash.interfaces.MainActivityView;
import com.example.youtube_clone.src.splash.models.DefaultResponse;


public class SplashActivity extends BaseActivity implements MainActivityView {

    final SplashService splashService = new SplashService(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tryGetTest();
    }



    private void tryGetTest() {
        splashService.getVideoPathAndQuery(1);
    } // 처음 접속 시에는 페이지 1 호출

    @Override
    public void validateSuccess(DefaultResponse.Result result) {
            Intent intent = new Intent(this, com.example.youtube_clone.src.main.MainActivity.class);
            intent.putExtra("result",result);
            startActivity(intent);
            finish();

    }

    @Override
    public void validateFailure(@Nullable String message) {
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
        Intent intent = new Intent(this, com.example.youtube_clone.src.main.MainActivity.class);
        startActivity(intent);
        finish();
    }




}

