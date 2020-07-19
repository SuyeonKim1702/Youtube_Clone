package com.example.youtube_clone.src.main;

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
import com.example.youtube_clone.src.main.Home.HomeFragment;
import com.example.youtube_clone.src.main.Inbox.InboxFragment;
import com.example.youtube_clone.src.main.Library.LibraryFragment;
import com.example.youtube_clone.src.main.Subscribe.SubscribeFragment;
import com.example.youtube_clone.src.main.interfaces.MainActivityView;
import com.example.youtube_clone.src.main.models.SignInResponse;
import com.example.youtube_clone.src.main.Explore.ExploreFragment;
import com.example.youtube_clone.src.splash.models.DefaultResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends BaseActivity implements MainActivityView {

    BottomNavigationView bottomNavigationView;

    final MainService mainService = new MainService(this);
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private InboxFragment fragmentInbox = new InboxFragment();
    private HomeFragment fragmentHome = new HomeFragment();
    private ExploreFragment fragmentExplore = new ExploreFragment();
    private SubscribeFragment fragmentSubscribe = new SubscribeFragment();
    private LibraryFragment fragmentLibrary = new LibraryFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        DefaultResponse.Result result = (DefaultResponse.Result) intent.getSerializableExtra("result");

        Bundle bundle = new Bundle();
        bundle.putSerializable("result",result);
        bundle.putInt("check",1);
        fragmentHome.setArguments(bundle);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();

        bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch(menuItem.getItemId())
            {
                case R.id.menu_item_home:
                    Bundle bundle = new Bundle();
                    bundle.putInt("check",0);
                    fragmentHome.setArguments(bundle);
                    transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();
                    break;
                case R.id.menu_item_explore:
                    transaction.replace(R.id.frameLayout, fragmentExplore).commitAllowingStateLoss();
                    break;
                case R.id.menu_item_subscribe:
                    transaction.replace(R.id.frameLayout, fragmentSubscribe).commitAllowingStateLoss();
                    break;
                case R.id.menu_item_inbox:
                    transaction.replace(R.id.frameLayout, fragmentInbox).commitAllowingStateLoss();
                    break;
                case R.id.menu_item_library:
                    transaction.replace(R.id.frameLayout, fragmentLibrary).commitAllowingStateLoss();
                    break;
            }
           // bottomNavigationView.setItemIconSize(18);
            return true;
        }
    }



       private void tryGetTest() {
        showProgressDialog();
        mainService.getTest();
    }

    private void tryPostSignIn() {
        showProgressDialog();
        mainService.postSignIn("dsafs","sdfsgsa"); // 소셜로그인에서는 어떤 방식으로 해야하지?
    }



    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();
        showCustomToast(text);
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    @Override
    public void signInSuccess(SignInResponse.SignInResult signInResult){
        hideProgressDialog();
        //하고 싶은 기능 넣기 ex) getJwt ..
    }

    }

