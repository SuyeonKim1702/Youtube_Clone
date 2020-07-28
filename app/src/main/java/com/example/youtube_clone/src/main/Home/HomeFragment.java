package com.example.youtube_clone.src.main.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.youtube_clone.R;
import com.example.youtube_clone.src.detail.DetailActivity;
import com.example.youtube_clone.src.main.Home.interfaces.MainActivityView;
import com.example.youtube_clone.src.main.Home.models.StoryResponse;
import com.example.youtube_clone.src.main.RecyclerViewItem;
import com.example.youtube_clone.src.splash.models.DefaultResponse;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements MainActivityView {

    ArrayList<RecyclerViewItem> mList = new ArrayList<>();
    ArrayList<RecyclerViewItem> mHorizontalList = new ArrayList<>();
    RecyclerViewAdapter mAdapter;
    static RecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    View mView;
    int pageNum = 1;

    final HomeService mainService = new HomeService(this);

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        if (bundle != null && bundle.getInt("check") == 1) { //맨처음에만 실행되도록 함
            DefaultResponse.Result result = (DefaultResponse.Result) bundle.getSerializable("result");
            mView = inflater.inflate(R.layout.fragment_home, container, false);
            mRecyclerView = mView.findViewById(R.id.recyclerView);
            mSwipeRefreshLayout = mView.findViewById(R.id.swipe_layout);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            // 리사이클러뷰에 SimpleTextAdapter 객체 지정
            mAdapter = new RecyclerViewAdapter(mList,mHorizontalList);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.scrollToPosition(0);
            for (DefaultResponse.Video r : result.getVideo())
                addItem(r.getPlayTime(),r.getTitle(), r.getChannelName(), r.getViewCount(), r.getCreateAt(), r.getThumUrl(), r.getProfileUrl(),r.getVideoIndex(),0);
            mAdapter.notifyDataSetChanged();

        }

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

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //페이징 처리
                if (dy > 0) {
                    // 아래로
                    int lastPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                    int totalCount = recyclerView.getAdapter().getItemCount();
                    int diff = totalCount - lastPosition;
                    if(diff <= 4){
                        pageNum++;
                        tryGetTest(pageNum);
                    }

                }
            }
        });

        return mView;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onResume() {  //Fragment를 다 그렸을 때 호출된다, ui 변경 작업 가능
        super.onResume();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tryGetTest(1); //새로고침

            }
        });

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

    public void addItem(String channelName, int commentCount, String uploadDate, String imageUrl, String thumbUpCount, String content, String profileImage, int type) {
        RecyclerViewItem item = new RecyclerViewItem();

        item.setChannelName(channelName);
        item.setUploadDate(uploadDate);
        item.setCommentCount(commentCount);
        item.setThumbUpCount(thumbUpCount);
        item.setImage(imageUrl);
        item.setContentText(content);
        item.setType(type);
        item.setProfileImage(profileImage);
        //int iValue = (int)(Math.random() * 8 + 1);
      //  mList.add(mList.size()-iValue,item);
        mList.add(item);


    }

    public void addItem(StoryResponse.Story[] story){
        removeAll(mHorizontalList);
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

    static public void moveToFirst(){
        mRecyclerView.smoothScrollToPosition(0);
    }
    public void removeAll(ArrayList List){
        List.removeAll(List);
    }
    private void tryGetTest(int pageNum) {
        if(pageNum%3 == 1) mainService.getVideoPathAndQuery(pageNum);
       else if(pageNum%3 == 0) mainService.getCommunityPathAndQuery(pageNum);
        else mainService.getStoryPathAndQuery(pageNum);
    }

    @Override
    public void validateSuccess(com.example.youtube_clone.src.main.Home.models.DefaultResponse.Result result, int pageNum) {
        if(pageNum == 1){ //새로고침
            removeAll(mList); //기존 리스트를 다 지우고
            for (com.example.youtube_clone.src.main.Home.models.DefaultResponse.Video r : result.getVideo())
                addItem(r.getPlayTime(),r.getTitle(), r.getChannelName(), r.getViewCount(), r.getCreateAt(), r.getThumUrl(), r.getProfileUrl(),r.getVideoIndex(),0);
            mAdapter.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false); // 새로고침 중지

        }else{
            for (com.example.youtube_clone.src.main.Home.models.DefaultResponse.Video r : result.getVideo())
                addItem(r.getPlayTime(),r.getTitle(), r.getChannelName(), r.getViewCount(), r.getCreateAt(), r.getThumUrl(), r.getProfileUrl(),r.getVideoIndex(),0);
                mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void validateSuccess(com.example.youtube_clone.src.main.Home.models.CommunityResponse.Result result, int pageNum) {

            for (com.example.youtube_clone.src.main.Home.models.CommunityResponse.Community c : result.getCommunity())
                addItem(c.getChannelName(), c.getCommentCount(), c.getCreateAt(), c.getImgUrl(),c.getLikesCount(),c.getMainText(),c.getProfileUrl(), 1);
            mAdapter.notifyDataSetChanged();
        }


    @Override
    public void validateSuccess(com.example.youtube_clone.src.main.Home.models.StoryResponse.Result result, int pageNum) {

            addItem(result.getStory());
            mAdapter.notifyDataSetChanged();
    }



    @Override
    public void validateFailure(String message) {

    }


}