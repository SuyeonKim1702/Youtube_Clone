package com.example.youtube_clone.src.detail;

import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.youtube_clone.R;

import java.util.ArrayList;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class DetailComment2Fragment extends Fragment implements View.OnClickListener {

    SubCommentAdapter mSubCommentAdapter;
    static ArrayList<SubComment> mSubCommentList;
    static EditText mComment;
    static int position;
    static boolean check = false;

    static ListView listView;
    SubCommentAdapter mAdapter = null ;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_comment2, null);
        ImageButton closeButton = view.findViewById(R.id.ib_comment2_close);
        ImageButton leftButton = view.findViewById(R.id.ib_comment2_leftArrow);
        closeButton.setOnClickListener(this);
        leftButton.setOnClickListener(this);

        mSubCommentList = new ArrayList<>();
        listView= view.findViewById(R.id.lv_comment2_listView) ;
        mAdapter = new SubCommentAdapter(mSubCommentList,getContext()) ;

        final View header = getLayoutInflater().inflate(R.layout.comment2_listview_header, null, false) ;
        listView.addHeaderView(header) ;
        listView.setAdapter(mAdapter) ;
        mComment = header.findViewById(R.id.et_header2_comment);
        ImageButton sendButton = header.findViewById(R.id.bt_header2_send);


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
               if(check){ // 수정
                   mSubCommentList.get(position).setmCommentContent(mComment.getText().toString());
                   mSubCommentList.get(position).setmUploadDate( mSubCommentList.get(position).getmUploadDate()+"(수정됨)");
                   mAdapter.notifyDataSetChanged();
                   mComment.setText("");
                   //키보드 내리기
                   DetailActivity.imm.hideSoftInputFromWindow(mComment.getWindowToken(), 0);
                   //서버에 수정사항 전송하기
               }else{ //새 댓글 작성
                   addItem("ddg","td1702","방금",mComment.getText().toString(),0,0);
                   mAdapter.notifyDataSetChanged();
                   mComment.setText("");
                   DetailActivity.imm.hideSoftInputFromWindow(mComment.getWindowToken(), 0);
                   //서버에 답글 전송하기 add !
               }

           }
       });

        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        for(int i=0;i<10;i++)
            addItem("ddg","td1702","3일전","진짜 웃겨",10,0);
           mAdapter.notifyDataSetChanged();


    }



    public static DetailComment2Fragment newInstance() {
        return new DetailComment2Fragment();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ib_comment2_leftArrow:
                ((DetailActivity)getActivity()).removeFragment(DetailComment2Fragment.this);
                break;
            case R.id.ib_comment2_close:
                ((DetailActivity)getActivity()).removeFragment();
                break;



        }
    }

    public static void onModify(int pos){
        listView.smoothScrollToPosition(0);
        check = true;
        position = pos;
    }

    public void addItem(String profileImage,String channelName,String uploadDate,String content,int thumbUpCount, int thumb){
        Comment item = new Comment();

        item.setmProfileImage(profileImage);
        item.setmChannelName(channelName);
        item.setmUploadDate(uploadDate);
        item.setmCommentContent(content);
        item.setmThumbUpCount(thumbUpCount);
        item.setThumbUpOrNot(thumb);

        mSubCommentList.add(0,item);
    }

}