package com.example.youtube_clone.src.detail.Comment1Fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.youtube_clone.R;
import com.example.youtube_clone.src.detail.Comment;
import com.example.youtube_clone.src.detail.Comment1Fragment.interfaces.Comment1ActivityView;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.DeleteCommentResponse;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.GetCommentResponse;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.PatchCommentResponse;
import com.example.youtube_clone.src.detail.Comment1Fragment.models.PostCommentResponse;
import com.example.youtube_clone.src.detail.CommentAdapter;
import com.example.youtube_clone.src.detail.DetailActivity;
import com.example.youtube_clone.src.detail.Comment2Fragment.DetailComment2Fragment;

import java.util.ArrayList;

import static com.example.youtube_clone.src.detail.DefaultFragment.DetailDefaultFragment.commentNum;

public class DetailComment1Fragment extends Fragment implements View.OnClickListener, Comment1ActivityView {

    CommentAdapter mCommentAdapter;
    public static ArrayList<Comment> mCommentList;
    public static EditText mComment;
    static Boolean check = false;
    static int position;
    String addCommentContent;
    TextView commentCount;
    static Context mContext;
    public static int replyCnt;
    public static int commentIdx,likesCount;
    public static String channelName,commentContent,date,profileImage;

    static ListView listView;
    CommentAdapter mAdapter = null ;

    final DetailComment1Service comment1Service = new DetailComment1Service(this);

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_comment1, null);
        ImageButton closeButton = view.findViewById(R.id.ib_comment1_close);
        closeButton.setOnClickListener(this);
        commentCount = view.findViewById(R.id.tv_comment1_commentCount);
        mCommentList = new ArrayList<>();
        listView= view.findViewById(R.id.lv_comment1_listView) ;
        mAdapter = new CommentAdapter(mCommentList,getContext()) ;
        mContext = getActivity();
        commentCount.setText(commentNum+"");

        final View header = getLayoutInflater().inflate(R.layout.comment1_listview_header, null, false) ;
        listView.addHeaderView(header) ;
        listView.setAdapter(mAdapter) ;
        mComment = header.findViewById(R.id.et_header_comment);
        ImageButton sendButton = header.findViewById(R.id.bt_comment1_send);

        //댓글 받아오는 api 호출
        int callNum;
        if(commentNum%10 == 0) callNum = commentNum/10;
        else callNum = commentNum/10+1;
        for(int i=1;i<=callNum;i++)
         getComment(i);

        mComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length() == 0) sendButton.setVisibility(View.INVISIBLE);
                    else sendButton.setVisibility(View.VISIBLE);

            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check){ //수정
                    String commentContent = mComment.getText().toString();
                      mCommentList.get(position).setmCommentContent(commentContent);
                      mCommentList.get(position).setmUploadDate( mCommentList.get(position).getmUploadDate());
                      mAdapter.notifyDataSetChanged();
                      mComment.setText("");
                    //키보드 내리기
                    DetailActivity.imm.hideSoftInputFromWindow(mComment.getWindowToken(), 0);
                    DetailComment1Service.patchComment(DetailActivity.videoIdx,mCommentList.get(position).getmCommentIdx(),commentContent);
                      //서버에 수정사항 전송하기
                }else{
                    addCommentContent = mComment.getText().toString();

                    mComment.setText("");
                    //키보드 내리기
                    DetailActivity.imm.hideSoftInputFromWindow(mComment.getWindowToken(), 0);
                    DetailComment1Service.postComment(DetailActivity.videoIdx,addCommentContent);
                    //서버에 댓글 전송하기 add !

                }

                check = false;

            }
        });

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                position = position -1;
                replyCnt = mCommentList.get(position).getmCommentCount();
                commentIdx = mCommentList.get(position).getmCommentIdx();
                channelName = mCommentList.get(position).getmChannelName();
                date = mCommentList.get(position).getmUploadDate();
                likesCount = mCommentList.get(position).getmThumbUpCount();
                commentContent = mCommentList.get(position).getmCommentContent();
                profileImage = mCommentList.get(position).getmProfileImage();



                Toast.makeText(mContext, commentIdx+"", Toast.LENGTH_SHORT).show();

                ((DetailActivity)getActivity()).replaceFragment(DetailComment2Fragment.newInstance());


            }
        };

        listView.setOnItemClickListener(listener);
        return view;


    }

    private void getComment(int i) {
        comment1Service.getComment(DetailActivity.videoIdx,i);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }

    public static void onModify(int pos){
     listView.smoothScrollToPosition(0);
     check = true;
     position = pos;
    }

    public static DetailComment1Fragment newInstance() {
        return new DetailComment1Fragment();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ib_comment1_close:
                ((DetailActivity)getActivity()).removeFragment(DetailComment1Fragment.this);
                break;


        }
    }

    public void addItem(String profileImage,String channelName,String uploadDate,String content,int thumbUpCount, int commentCount, int thumb, int commentIdx){
        Comment item = new Comment();

        item.setmProfileImage(profileImage);
        item.setmChannelName(channelName);
        item.setmUploadDate(uploadDate);
        item.setmCommentContent(content);
        item.setmThumbUpCount(thumbUpCount);
        item.setmCommentCount(commentCount);
        item.setThumbUpOrNot(thumb);
        item.setmCommentIdx(commentIdx);
        mCommentList.add(0,item);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void validateSuccess(DeleteCommentResponse result) {   //DELETE
        Toast.makeText(mContext, result.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void validateSuccess(PatchCommentResponse result) {   //PATCH ->
        Toast.makeText(mContext, result.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void validateSuccess(PostCommentResponse result) {   //ADD ->
        //값들 세팅해줘서 add 하기

        addItem("https://firebasestorage.googleapis.com/v0/b/clone-e7f75.appspot.com/o/profile%2FRita%20Go.png?alt=media&token=9eb60db4-8d03-4af3-9a68-ecbc0b9a858f",
                "sun",
                "방금 전",
                result.getResult().getCommentsText(),
                0,
                0,
                0,
                result.getResult().getCommentsIdx());

        //Toast.makeText(mContext, result.getMessage(), Toast.LENGTH_SHORT).show();
       // System.out.println(result.getMessage());
    }

    @Override
    public void validateSuccess(GetCommentResponse.Result[] result) {  //GET
        for(GetCommentResponse.Result r : result){
           Comment item = new Comment();
           item.setmCommentCount(r.getCmtReplyCount());
           item.setmCommentContent(r.getCommentText());
           item.setmThumbUpCount(r.getLikesCount());
           item.setThumbUpOrNot(r.getLikesStatus());
           item.setmCommentIdx(r.getCommentsIdx());
           item.setmVideoIdx(r.getVideoIdx());
           item.setmProfileImage(r.getProfileUrl());
           item.setmUploadDate(r.getCreatedAt());
           item.setmChannelName(r.getUserId());
           mCommentList.add(item);
       }
       mAdapter.notifyDataSetChanged();


    }

    @Override
    public void validateFailure(String message) {
        //System.out.println(message+"이다");
        //Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void validateFailure2(GetCommentResponse message) {
       // Toast.makeText(mContext, message.getCode()+"", Toast.LENGTH_SHORT).show();
    }
}