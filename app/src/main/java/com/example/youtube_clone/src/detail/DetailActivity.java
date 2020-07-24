package com.example.youtube_clone.src.detail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.youtube_clone.R;
import com.example.youtube_clone.src.BaseActivity;
import com.example.youtube_clone.src.account.AccountActivity;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.DefaultTimeBar;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class DetailActivity extends BaseActivity implements View.OnClickListener {

    private PlayerView exoPlayerView;
    private SimpleExoPlayer player;

    private Boolean playWhenReady = true;
    LinearLayout commentLinear;
    private int currentWindow = 0;
    private Long playbackPosition = 0L;
    private static Handler timeHandler;
    TimeRunnable mTimeRunnable;
    Thread mThread = new Thread();
    boolean PLAY = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        commentLinear = findViewById(R.id.ll_detail_comment);
        exoPlayerView = findViewById(R.id.exo_detail_exoplayer);
        TextView currentTime = findViewById(R.id.exo_position);
        TextView durationTime = findViewById(R.id.exo_duration);
        ProgressBar progressBar = findViewById(R.id.pb_detail_progress);
        DefaultTimeBar tb = findViewById(R.id.exo_progress);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        ImageView iv = findViewById(R.id.iv_detail_subscriber_profile);
        ImageButton ib = findViewById(R.id.ib_detail_thumbUp);
        tb.hideScrubber();
        String total = "01:58"; // 서버에서 받아온 값 넣기
        String[] tokens = total.split(":");

        int secondsToMs = Integer.parseInt(tokens[1]) * 1000;
        int minutesToMs = Integer.parseInt(tokens[0]) * 60000;
        long total2 = secondsToMs + minutesToMs;
        commentLinear.setOnClickListener(this);

        timeHandler = new Handler() {

            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(Message msg) {
                if(player!=null){
                    if(currentTime.isShown()){
                       progressBar.setVisibility(View.GONE);
                    }else{
                        progressBar.setVisibility(View.VISIBLE);
                    }
                    String current = currentTime.getText().toString();

                    int prop = (int) ((player.getCurrentPosition()) / (float) total2 * 100);

                    progressBar.setProgress(prop);

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

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true; //will prevent child touch events
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Util.SDK_INT >= 24) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
        if ((Util.SDK_INT < 24 || player == null)) {
            initializePlayer();
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

    private void initializePlayer() {
        player = new SimpleExoPlayer.Builder(this).build();
        exoPlayerView.setPlayer(player);
        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/clone-e7f75.appspot.com/o/videos%2F'%E1%84%8C%E1%85%A9%E1%84%8A%E1%85%B5%E1%84%80%E1%85%A9%E1%84%8B%E1%85%A1'%C2%B7'%E1%84%8B%E1%85%B5%E1%86%B6%E1%84%8B%E1%85%A5%E1%84%87%E1%85%A5%E1%84%85%E1%85%B5%E1%86%AB%20%E1%84%8B%E1%85%A5%E1%86%AF%E1%84%80%E1%85%AE%E1%86%AF'%20%E1%84%80%E1%85%AA%E1%86%AB%E1%84%80%E1%85%A2%E1%86%A8%20%E1%84%86%E1%85%A1%E1%86%AB%E1%84%82%E1%85%A1%E1%86%AB%E1%84%83%E1%85%A1%20-%20YTN.mp4?alt=media&token=6e0c0892-bc84-46f6-a014-86419f2cfb06");
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




    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ll_detail_comment :

                Toast.makeText(DetailActivity.this, "야야야야야", Toast.LENGTH_SHORT).show();
                BottomSheetDialogFragment bottomSheet = new BottomSheetDialogFragment();
                bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");

               break;


        }
    }




}
