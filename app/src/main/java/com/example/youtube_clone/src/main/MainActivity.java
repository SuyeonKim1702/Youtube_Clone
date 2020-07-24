package com.example.youtube_clone.src.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.youtube_clone.R;
import com.example.youtube_clone.src.BaseActivity;
import com.example.youtube_clone.src.account.AccountActivity;
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
    ImageButton accountButton;
    int prePos;
    static Toolbar toolbar;

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

        toolbar = (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        Bundle bundle = new Bundle();
        bundle.putSerializable("result",result);
        bundle.putInt("check",1);
        prePos = 1;
        fragmentHome.setArguments(bundle);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();

        bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

        accountButton = findViewById(R.id.ib_main_account);
        accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AccountActivity.class);
                startActivity(intent);
            }
        });


    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch(menuItem.getItemId())
            {
                case R.id.menu_item_home:
                    if(prePos == 1) {
                        HomeFragment.moveToFirst();
                        toolbar.setElevation(50f);

                    }
                    prePos = 1;
                    Bundle bundle = new Bundle();
                    bundle.putInt("check",0);
                    fragmentHome.setArguments(bundle);
                    transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();
                    break;
                case R.id.menu_item_explore:
                    transaction.replace(R.id.frameLayout, fragmentExplore).commitAllowingStateLoss();
                    prePos = 2;
                    break;
                case R.id.menu_item_subscribe:
                    transaction.replace(R.id.frameLayout, fragmentSubscribe).commitAllowingStateLoss();
                    prePos = 3;
                    break;
                case R.id.menu_item_inbox:
                    transaction.replace(R.id.frameLayout, fragmentInbox).commitAllowingStateLoss();
                    prePos = 4;
                    break;
                case R.id.menu_item_library:
                    transaction.replace(R.id.frameLayout, fragmentLibrary).commitAllowingStateLoss();
                    prePos = 5;
                    break;
            }
           // bottomNavigationView.setItemIconSize(18);
            return true;
        }
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
    }

    }

