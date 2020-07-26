package com.example.youtube_clone.src.detail;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtube_clone.R;
import com.example.youtube_clone.src.main.RecyclerViewItem;

import java.util.ArrayList;

public class DetailComment1Fragment extends Fragment implements View.OnClickListener {

    CommentAdapter mCommentAdapter;
    static ArrayList<Comment> mCommentList;
    static EditText mComment;
    static Boolean check = false;
    static int position;

    static ListView listView;
    CommentAdapter mAdapter = null ;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_comment1, null);
        ImageButton closeButton = view.findViewById(R.id.ib_comment1_close);
        closeButton.setOnClickListener(this);

        mCommentList = new ArrayList<>();
        listView= view.findViewById(R.id.lv_comment1_listView) ;
        mAdapter = new CommentAdapter(mCommentList,getContext()) ;


        final View header = getLayoutInflater().inflate(R.layout.comment1_listview_header, null, false) ;
        listView.addHeaderView(header) ;
        listView.setAdapter(mAdapter) ;
        mComment = header.findViewById(R.id.et_header_comment);
        ImageButton sendButton = header.findViewById(R.id.bt_comment1_send);

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

                if(check){
                      mCommentList.get(position).setmCommentContent(mComment.getText().toString());
                      mCommentList.get(position).setmUploadDate( mCommentList.get(position).getmUploadDate()+"(수정됨)");
                      mAdapter.notifyDataSetChanged();
                      mComment.setText("");
                    //키보드 내리기
                    DetailActivity.imm.hideSoftInputFromWindow(mComment.getWindowToken(), 0);
                      //서버에 수정사항 전송하기
                }else{
                    addItem("ddg","여락이네","3일전",mComment.getText().toString(),4,5,0);
                    mAdapter.notifyDataSetChanged();
                    mComment.setText("");
                    //키보드 내리기
                    DetailActivity.imm.hideSoftInputFromWindow(mComment.getWindowToken(), 0);
                    //서버에 댓글 전송하기 add !
                }

                check = false;

            }
        });

        AdapterView.OnItemClickListener listener =new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((DetailActivity)getActivity()).replaceFragment(DetailComment2Fragment.newInstance());

            }
        };

        listView.setOnItemClickListener(listener);
        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        for(int i=0;i<10;i++)
            addItem("ddg","여락이네","3일전","아 너무 웃겨요 ㅋㅋㅋㅋㅋ",4,5,0);
           mAdapter.notifyDataSetChanged();





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

    public void addItem(String profileImage,String channelName,String uploadDate,String content,int thumbUpCount, int commentCount, int thumb){
        Comment item = new Comment();

        item.setmProfileImage(profileImage);
        item.setmChannelName(channelName);
        item.setmUploadDate(uploadDate);
        item.setmCommentContent(content);
        item.setmThumbUpCount(thumbUpCount);
        item.setmCommentCount(commentCount);
        item.setThumbUpOrNot(thumb);

        mCommentList.add(0,item);
    }

}