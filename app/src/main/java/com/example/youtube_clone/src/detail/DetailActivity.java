package com.example.youtube_clone.src.detail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.youtube_clone.R;
import com.example.youtube_clone.src.BaseActivity;
import com.example.youtube_clone.src.detail.Comment1Fragment.DetailComment1Fragment;
import com.example.youtube_clone.src.detail.DefaultFragment.DetailDefaultFragment;
import com.example.youtube_clone.src.detail.interfaces.DetailActivityView;
import com.example.youtube_clone.src.detail.models.DetailResponse;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.DefaultTimeBar;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


public class DetailActivity extends BaseActivity implements DetailActivityView, View.OnClickListener {

    final DetailService detailService = new DetailService(this);
    public static int videoIdx;
    private PlayerView exoPlayerView;
    private SimpleExoPlayer player;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private DetailDefaultFragment defaultFragment = new DetailDefaultFragment();
    private DetailComment1Fragment comment1Fragment = new DetailComment1Fragment();
    private Boolean playWhenReady = true;
    public static InputMethodManager imm;
    private int currentWindow = 0;
    private Long playbackPosition = 0L;
    private static Handler timeHandler;
    TimeRunnable mTimeRunnable;
    Thread mThread = new Thread();
    boolean PLAY = true;
    String videoUrl;
    DefaultTimeBar tb;
    static DetailResponse.VideoInfo mVideoInfo;
    static int videoIndex;
    static int commentNum;
    PlayerControlView controller;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = new Intent(this.getIntent());
        videoIndex = intent.getIntExtra("videoIndex",-1);
        String total = intent.getStringExtra("runTime");

        if(videoIndex != -1){
            tryGetDetailInfo(videoIndex);
        }


        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        controller = findViewById(R.id.controller2);
         tb= controller.findViewById(R.id.exo_progress);
        exoPlayerView = findViewById(R.id.exo_detail_exoplayer);
        TextView currentTime = findViewById(R.id.exo_position);
        TextView durationTime = findViewById(R.id.exo_duration);

        ImageView iv = findViewById(R.id.iv_detail_subscriber_profile);
        ImageButton ib = findViewById(R.id.ib_detail_thumbUp);
        //tb.hideScrubber();

        durationTime.setText(total);

        fragmentManager = getSupportFragmentManager();


        Bundle bundle = new Bundle();
        bundle.putInt("videoIndex", videoIndex);
        DetailDefaultFragment defaultNewInstance = defaultFragment.newInstance();
        defaultNewInstance.setArguments(bundle);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frame_detail_fragment, defaultNewInstance).commit();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

       //min to millisec
        String[] tokens = total.split(":");
        int secondsToMs = Integer.parseInt(tokens[1]) * 1000;
        int minutesToMs = Integer.parseInt(tokens[0]) * 60000;
        long total2 = secondsToMs + minutesToMs;


        timeHandler = new Handler() {

            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(Message msg) {
                if(player!=null){
                    if(currentTime.isShown()){
                        tb.showScrubber();

                    }else{
                        tb.hideScrubber();

                    }
                    String current = currentTime.getText().toString();

                    int prop = (int) ((player.getCurrentPosition()) / (float) total2 * 100);



                }

            }

        };
    }


    class TimeRunnable implements Runnable {

        @Override
        public void run() {

            while (PLAY) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                timeHandler.sendEmptyMessage(0);

            }
        }

        }


    @Override
    protected void onStart() {
        super.onStart();
        if (Util.SDK_INT >= 24) {
            initializePlayer(videoUrl);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
        if ((Util.SDK_INT < 24 || player == null) && videoUrl != null) {
            initializePlayer(videoUrl);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT < 24) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        PLAY = false;
        if (Util.SDK_INT >= 24) {
            releasePlayer();
        }

    }

    private void releasePlayer() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        exoPlayerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    private void initializePlayer(String url) {
        player = new SimpleExoPlayer.Builder(this).build();
        exoPlayerView.setPlayer(player);
        controller.setPlayer(player);
        Uri uri = Uri.parse(url);
        MediaSource mediaSource = buildMediaSource(uri);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        player.prepare(mediaSource, false, false);
        mTimeRunnable = new TimeRunnable();
        mThread = new Thread(mTimeRunnable);
        mThread.start();

        }

    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(this, "youtube-clone");
        return new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_detail_fragment, fragment).addToBackStack(null).commit();
    }

    public void removeFragment(Fragment fragment) {
        fragmentManager.beginTransaction().remove(fragment).commit();
        fragmentManager.popBackStack();
    }

    public void removeFragment() {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){


        }
    }

    private void tryGetDetailInfo(int idx) {
        DetailService.getDetailVideoPath(idx);
    } // 처음 접속 시에는 페이지 1 호출

    @Override
    public void validateSuccess(DetailResponse.Result result) {
        //DetailDefaultFragment.getDetailInfo(result.getVideoInfo());
        videoUrl = result.getVideoInfo().getVideoUrl();
        videoIdx = result.getVideoInfo().getVideoIdx();
        commentNum = result.getVideoInfo().getCommentsCount();
        initializePlayer(videoUrl);

    }

    @Override
    public void validateFailure(@Nullable String message) {
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.detail_info_error) : message);

    }




}
