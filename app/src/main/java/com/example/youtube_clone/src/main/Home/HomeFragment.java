package com.example.youtube_clone.src.main.Home;

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
import com.example.youtube_clone.src.main.Home.interfaces.MainActivityView;
import com.example.youtube_clone.src.main.ListViewItem;
import com.example.youtube_clone.src.main.RecyclerViewAdapter;
import com.example.youtube_clone.src.splash.models.DefaultResponse;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements MainActivityView {

    ArrayList<ListViewItem> mList = new ArrayList<>();
    ArrayList<ListViewItem> mHorizontalList = new ArrayList<>();
    RecyclerViewAdapter mAdapter;
    RecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    View mView;

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
                addItem(r.getTitle(), r.getChannelName(), r.getViewCount(), r.getCreateAt(), r.getThumUrl(), r.getProfileUrl(),0);
           // mAdapter.notifyDataSetChanged();
            for (DefaultResponse.Community c : result.getCommunity())
                addItem(c.getChannelName(), c.getCommentCount(), c.getCreateAt(), c.getImgUrl(),c.getLikesCount(),c.getMainText(),c.getProfileUrl(), 1);
            mAdapter.notifyDataSetChanged();
            addItem("https://firebasestorage.googleapis.com/v0/b/clone-e7f75.appspot.com/o/storyThumnail%2FMaroon5%20-%20Just%20a%20Feeling.png?alt=media&token=67aca61b-4a71-404f-9a9c-d9e4c557e426");
            mAdapter.notifyDataSetChanged();
        }else{ //테스트 용 실제로 할 땐 지울 것
/*
            mView = inflater.inflate(R.layout.fragment_home, container, false);
            mSwipeRefreshLayout = mView.findViewById(R.id.swipe_layout);
            mRecyclerView = (RecyclerView)mView.findViewById(R.id.recyclerView);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRecyclerView.scrollToPosition(0);

            RecyclerViewAdapter adapter2 = new RecyclerViewAdapter(mList);
            mRecyclerView.setAdapter(adapter2);
            adapter2.notifyDataSetChanged(); */
        }


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
                   System.out.println(lastPosition+"임");
                   int diff = totalCount - lastPosition;
                   if(diff == 4)
                       tryGetTest(totalCount/10+1);
                 System.out.println(diff+"이다");

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

    public void addItem(String title, String channelName, String viewCount, String uploadDate, String imageUrl, String profileImage, int type) {
        ListViewItem item = new ListViewItem();

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
        ListViewItem item = new ListViewItem();

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

    public void addItem(String i){
        ListViewItem item = new ListViewItem();
        item.setType(3);
        item.setImage(i);
        mList.add(item);
        mHorizontalList.add(item);
        mHorizontalList.add(item);
        mHorizontalList.add(item);
        mHorizontalList.add(item);
        mHorizontalList.add(item);
    }

    public void removeAll(){
        mList.removeAll(mList);
    }
    private void tryGetTest(int pageNum) {
        mainService.getVideoPathAndQuery(pageNum);
    }

    @Override
    public void validateSuccess(com.example.youtube_clone.src.main.Home.models.DefaultResponse.Result result, int pageNum) {
        if(pageNum == 1){ //새로고침
            removeAll(); //기존 리스트를 다 지우고
            for (com.example.youtube_clone.src.main.Home.models.DefaultResponse.Video r : result.getVideo())
                addItem(r.getTitle(), r.getChannelName(), r.getViewCount(), r.getCreateAt(), r.getThumUrl(), r.getProfileUrl(),0);
            for (com.example.youtube_clone.src.main.Home.models.DefaultResponse.Community c : result.getCommunity())
                addItem(c.getChannelName(), c.getCommentCount(), c.getCreateAt(), c.getImgUrl(),c.getLikesCount(),c.getMainText(),c.getProfileUrl(), 1);
            mAdapter.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false); // 새로고침 중지

        }else{
            for (com.example.youtube_clone.src.main.Home.models.DefaultResponse.Video r : result.getVideo())
                addItem(r.getTitle(), r.getChannelName(), r.getViewCount(), r.getCreateAt(), r.getThumUrl(), r.getProfileUrl(),0);
            for (com.example.youtube_clone.src.main.Home.models.DefaultResponse.Community c : result.getCommunity())
                addItem(c.getChannelName(), c.getCommentCount(), c.getCreateAt(), c.getImgUrl(),c.getLikesCount(),c.getMainText(),c.getProfileUrl(), 1);
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void validateFailure(String message) {

    }


}
