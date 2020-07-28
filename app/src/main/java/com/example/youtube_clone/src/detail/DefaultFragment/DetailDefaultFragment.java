package com.example.youtube_clone.src.detail.DefaultFragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtube_clone.R;
import com.example.youtube_clone.src.detail.Comment;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.GetCommentResponse;
import com.example.youtube_clone.src.detail.DefaultFragment.interfaces.DefaultActivityView;
import com.example.youtube_clone.src.detail.DefaultFragment.models.LikesResponse;
import com.example.youtube_clone.src.detail.DefaultFragment.models.SavedResponse;
import com.example.youtube_clone.src.detail.DefaultFragment.models.SubscribeResponse;
import com.example.youtube_clone.src.detail.DetailActivity;
import com.example.youtube_clone.src.detail.Comment1Fragment.DetailComment1Fragment;
import com.example.youtube_clone.src.detail.DefaultFragment.models.DetailResponse;
import com.example.youtube_clone.src.detail.DefaultFragment.RecyclerViewAdapter;
import com.example.youtube_clone.src.main.Home.models.DefaultResponse;
import com.example.youtube_clone.src.main.Home.models.StoryResponse;
import com.example.youtube_clone.src.main.RecyclerViewItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;

public class DetailDefaultFragment extends Fragment implements DefaultActivityView {

    static TextView thumbUpCount;
    static TextView thumbDownCount;

    static Context mContext;
    static ImageButton thumbUpButton,thumbDownButton, saveButton;
    static public int thumbUpCountHeader,thumbDownCountHeader,commentCountHeader, subscribeCountHeader;
    static public String channelNameHeader,commentContent,commentImage;
    static int status;
    static int thumbUpNum;
    static int thumbDownNum;
    static int videoIndex;
    public static int commentNum;
    public static String videoTitleHeader;
    public static String viewCountHeader;
    static ArrayList<RecyclerViewItem> mList = new ArrayList<>();
    public static String createdAtHeader;
    ArrayList<RecyclerViewItem> mHorizontalList = new ArrayList<>();
    static RecyclerViewAdapter mAdapter;
    static RecyclerView mRecyclerView;
    public static int likesStatusHeader, channelId;
    public static String profileImageHeader;
    public static boolean savesState,subscribeStatus;
    static int videoIdx;

    static DetailDefaultService mainService = null;

    {
        mainService = new DetailDefaultService(this);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_default, null);
        mContext = getActivity();
        mRecyclerView = view.findViewById(R.id.recyclerView2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정
        mAdapter = new RecyclerViewAdapter(mList,mHorizontalList);
        mRecyclerView.setAdapter(mAdapter);
        mList.removeAll(mList);


       /* LinearLayout commentLinear = view.findViewById(R.id.ll_detail_comment);
        commentLinear.setOnClickListener(this);
        thumbUpButton.setOnClickListener(this);
        thumbDownButton.setOnClickListener(this);   */

        Bundle bundle = this.getArguments();

        videoIdx =  bundle.getInt("videoIndex");

        //api 호출
        DetailDefaultService.getDetailVideoPath(videoIdx);


        mAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                // TODO : 아이템 클릭 이벤트를 MainActivity에서 처리.
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("videoIndex",mList.get(position).getVideoIndex());
                intent.putExtra("runTime",mList.get(position).getmTimeline());
                startActivity(intent);


            }
        }) ;




        return view;


    }

    @Override
    public void onStart() {
        super.onStart();


    }


    public static void replaceFragment(){
        ((DetailActivity)mContext).replaceFragment(DetailComment1Fragment.newInstance());
    }


    public static void updateLikes(int likesStatusHeader){
        DetailDefaultService.updateLikesStatus(videoIndex,likesStatusHeader);
    }


    public static DetailDefaultFragment newInstance() {
        return new DetailDefaultFragment();
    }

    public static void getDetailInfo(DetailResponse.VideoInfo videoInfo){
     channelId = videoInfo.getUserIdx();
     savesState = videoInfo.getSaveStatus();
     subscribeStatus = videoInfo.getSubscribeStatus();
     videoTitleHeader =videoInfo.getTitleText();
     viewCountHeader = videoInfo.getViews()+"회";
     createdAtHeader = videoInfo.getCreatedAt();
     thumbUpCountHeader = videoInfo.getLikesCount();
     thumbDownCountHeader = videoInfo.getDislikesCount();
     commentCountHeader = videoInfo.getCommentsCount();
     channelNameHeader = videoInfo.getUserId();
     subscribeCountHeader = videoInfo.getSubscribeCount();
     likesStatusHeader = videoInfo.getLikesStatus();
     profileImageHeader = videoInfo.getProfileUrl();
     status = videoInfo.getLikesStatus();
        thumbUpNum = videoInfo.getLikesCount();
        thumbDownNum = videoInfo.getDislikesCount();
        videoIndex = videoInfo.getVideoIdx();
        commentNum = videoInfo.getCommentsCount();
      RecyclerViewItem item = new RecyclerViewItem();
      item.setType(5);
      mList.add(0,item);
       mAdapter.notifyDataSetChanged();

        //최신 댓글 호출
        DetailDefaultService.getOneComment(videoIdx);
        //추천 동영상 호출
        mainService.getVideoPathAndQuery(1);
        mainService.getStoryPathAndQuery(1);


    }

    @Override
    public void validateSuccess(GetCommentResponse.Result[] result) {
           if(result.length>0){
               commentContent = result[0].getCommentText();
               commentImage = result[0].getProfileUrl();
               mAdapter.notifyDataSetChanged();
           }else{

           }

    }

    @Override
    public void validateSuccess(SubscribeResponse subscribeResponse) {
        Toast.makeText(mContext, subscribeResponse.getCode()+"임", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void validateSuccess(SavedResponse savedResponse) {
        Toast.makeText(mContext, savedResponse.isSuccess()+"임", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void validateSuccess(DefaultResponse.Result result) {
        for (com.example.youtube_clone.src.main.Home.models.DefaultResponse.Video r : result.getVideo())
            addItem(r.getPlayTime(),r.getTitle(), r.getChannelName(), r.getViewCount(), r.getCreateAt(), r.getThumUrl(), r.getProfileUrl(),r.getVideoIndex(),0);
             mAdapter.notifyDataSetChanged();
     }

    @Override
    public void validateSuccess(StoryResponse.Result result) {
        addItem(result.getStory());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void validateSuccess(com.example.youtube_clone.src.detail.DefaultFragment.models.DetailResponse.Result result) {
        getDetailInfo(result.getVideoInfo());
    }

    @Override
    public void validateSuccess(LikesResponse response) {
        System.out.println("응답"+response.IsSuccess());
        Toast.makeText(mContext, "응답"+response.IsSuccess(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void validateFailure(String message) {
        Toast.makeText(mContext, "실패"+message, Toast.LENGTH_SHORT).show();

    }

    public void addItem(String timeLine, String title, String channelName, String viewCount, String uploadDate, String imageUrl, String profileImage, int index, int type) {
        RecyclerViewItem item = new RecyclerViewItem();
        item.setTimeline(timeLine);
        item.setTitle(title);
        item.setChannelName(channelName);
        item.setUploadDate(uploadDate);
        item.setViewCount(viewCount);
        item.setThumUrl(imageUrl);
        item.setType(type);
        item.setVideoIndex(index);
        item.setProfileImage(profileImage);

        mList.add(item);

    }

    public void addItem(StoryResponse.Story[] story){
        mHorizontalList.remove(mHorizontalList);
        for(StoryResponse.Story s :story){
            RecyclerViewItem item = new RecyclerViewItem();
            item.setChannelName(s.getChannelName());
            item.setProfileImage(s.getProfileUrl());
            item.setThumUrl(s.getThumbUrl());
            mHorizontalList.add(item);
        }
        RecyclerViewItem item2 = new RecyclerViewItem();
        item2.setType(2);
        mList.add(item2); //한번만 add -> 스토리 영상 여러개를 하나로 인식

    }


}




