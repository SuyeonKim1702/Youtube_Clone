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
import com.example.youtube_clone.src.main.MainActivity;
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

    final MainService mainService = new MainService(this);

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
                addItem(r.getPlayTime(),r.getTitle(), r.getChannelName(), r.getViewCount(), r.getCreateAt(), r.getThumUrl(), r.getProfileUrl(),0);
            // mAdapter.notifyDataSetChanged();
            for (DefaultResponse.Community c : result.getCommunity())
                addItem(c.getChannelName(), c.getCommentCount(), c.getCreateAt(), c.getImgUrl(),c.getLikesCount(),c.getMainText(),c.getProfileUrl(), 1);
            if(result.getstory() != null)
                addItem(result.getstory());
            mAdapter.notifyDataSetChanged();
            //mRecyclerView.scrollToPosition(0);
        }

        mAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                // TODO : 아이템 클릭 이벤트를 MainActivity에서 처리.
                Intent intent = new Intent(getContext(), DetailActivity.class);
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
                //mSwipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    public void addItem(String timeLine, String title, String channelName, String viewCount, String uploadDate, String imageUrl, String profileImage, int type) {
        RecyclerViewItem item = new RecyclerViewItem();
        item.setTimeline(timeLine);
        item.setTitle(title);
        item.setChannelName(channelName);
        item.setUploadDate(uploadDate);
        item.setViewCount(viewCount);
        item.setThumUrl(imageUrl);
        item.setType(type);
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
        int iValue = (int)(Math.random() * 8 + 1);
        mList.add(mList.size()-iValue,item);


    }

    public void addItem(DefaultResponse.Story[] story){
        removeAll(mHorizontalList);
        for(DefaultResponse.Story s :story){
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

    public void addItem(com.example.youtube_clone.src.main.Home.models.DefaultResponse.Story[] story){
        removeAll(mHorizontalList);
        for(com.example.youtube_clone.src.main.Home.models.DefaultResponse.Story s :story){
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
        mainService.getVideoPathAndQuery(pageNum);
    }

    @Override
    public void validateSuccess(com.example.youtube_clone.src.main.Home.models.DefaultResponse.Result result, int pageNum) {
        if(pageNum == 1){ //새로고침
            removeAll(mList); //기존 리스트를 다 지우고
            for (com.example.youtube_clone.src.main.Home.models.DefaultResponse.Video r : result.getVideo())
                addItem(r.getPlayTime(),r.getTitle(), r.getChannelName(), r.getViewCount(), r.getCreateAt(), r.getThumUrl(), r.getProfileUrl(),0);
            for (com.example.youtube_clone.src.main.Home.models.DefaultResponse.Community c : result.getCommunity())
                addItem(c.getChannelName(), c.getCommentCount(), c.getCreateAt(), c.getImgUrl(),c.getLikesCount(),c.getMainText(),c.getProfileUrl(), 1);
            if(result.getstory() != null) addItem(result.getstory());

            mAdapter.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false); // 새로고침 중지

        }else{
            for (com.example.youtube_clone.src.main.Home.models.DefaultResponse.Video r : result.getVideo())
                addItem(r.getPlayTime(),r.getTitle(), r.getChannelName(), r.getViewCount(), r.getCreateAt(), r.getThumUrl(), r.getProfileUrl(),0);
            for (com.example.youtube_clone.src.main.Home.models.DefaultResponse.Community c : result.getCommunity())
                addItem(c.getChannelName(), c.getCommentCount(), c.getCreateAt(), c.getImgUrl(),c.getLikesCount(),c.getMainText(),c.getProfileUrl(), 1);
            if(result.getstory() != null) addItem(result.getstory());
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void validateFailure(String message) {

    }


}