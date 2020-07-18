package com.example.youtube_clone.src.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.youtube_clone.R;
import com.example.youtube_clone.src.BaseActivity;
import com.example.youtube_clone.src.main.models.SignInResponse;
import com.example.youtube_clone.src.splash.MainService;
import com.example.youtube_clone.src.splash.interfaces.MainActivityView;
import com.example.youtube_clone.src.splash.models.DefaultResponse;


public class MainActivity extends BaseActivity implements MainActivityView {

    final MainService mainService = new MainService(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tryGetTest();
    }



    private void tryGetTest() {
        mainService.getVideoPathAndQuery();
    }

    @Override
    public void validateSuccess(DefaultResponse.Result[] result) {
        Intent intent = new Intent(this, com.example.youtube_clone.src.main.MainActivity.class);
        intent.putExtra("result",result);
        startActivity(intent);
        finish();
    }

    @Override
    public void validateFailure(@Nullable String message) {
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }




}

