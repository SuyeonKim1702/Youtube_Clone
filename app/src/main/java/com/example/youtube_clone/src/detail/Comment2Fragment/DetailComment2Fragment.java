package com.example.youtube_clone.src.detail.Comment2Fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.youtube_clone.R;
import com.example.youtube_clone.src.detail.Comment;
import com.example.youtube_clone.src.detail.Comment1Fragment.DetailComment1Fragment;
import com.example.youtube_clone.src.detail.Comment2Fragment.interfaces.Comment2ActivityView;
import com.example.youtube_clone.src.detail.Comment2Fragment.models.DeleteReplyResponse;
import com.example.youtube_clone.src.detail.Comment2Fragment.models.GetReplyResponse;
import com.example.youtube_clone.src.detail.Comment2Fragment.models.PatchReplyResponse;
import com.example.youtube_clone.src.detail.Comment2Fragment.models.PostReplyResponse;
import com.example.youtube_clone.src.detail.DetailActivity;
import com.example.youtube_clone.src.detail.SubComment;
import com.example.youtube_clone.src.detail.SubCommentAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.youtube_clone.src.detail.Comment1Fragment.DetailComment1Fragment.commentIdx;
import static com.example.youtube_clone.src.detail.Comment1Fragment.DetailComment1Fragment.replyCnt;
import static com.example.youtube_clone.src.detail.DefaultFragment.DetailDefaultFragment.commentNum;

public class DetailComment2Fragment extends Fragment implements View.OnClickListener, Comment2ActivityView {


    final DetailComment2Service comment2Service = new DetailComment2Service(this);

    SubCommentAdapter mSubCommentAdapter;
    public static ArrayList<SubComment> mSubCommentList;
    public static EditText mComment;
    static int position;
    static boolean check = false;
    static Context mContext;
    static ListView listView;
    static SubCommentAdapter mAdapter = null ;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_comment2, null);
        ImageButton closeButton = view.findViewById(R.id.ib_comment2_close);
        ImageButton leftButton = view.findViewById(R.id.ib_comment2_leftArrow);
        closeButton.setOnClickListener(this);
        leftButton.setOnClickListener(this);
        mContext = getActivity();
        mSubCommentList = new ArrayList<>();
        listView= view.findViewById(R.id.lv_comment2_listView) ;
        mAdapter = new SubCommentAdapter(mSubCommentList,getContext()) ;

        final View header = getLayoutInflater().inflate(R.layout.comment2_listview_header, null, false) ;
        listView.addHeaderView(header) ;
        listView.setAdapter(mAdapter) ;
        mComment = header.findViewById(R.id.et_header2_comment);
        TextView channelName = header.findViewById(R.id.tv_header2_channelName);
        channelName.setText(DetailComment1Fragment.channelName);
        TextView content = header.findViewById(R.id.tv_header2_content);
        content.setText(DetailComment1Fragment.commentContent);
        TextView likesCount = header.findViewById(R.id.tv_header2_thumbUpCount);
        TextView replyCount = header.findViewById(R.id.tv_header2_commentCount);
        likesCount.setText(DetailComment1Fragment.likesCount+"");
        replyCount.setText(replyCnt+"");
        ImageView profile = header.findViewById(R.id.iv_header2_profile);
        Picasso.with(getActivity())
                .load(DetailComment1Fragment.profileImage)
                .into(profile);
        TextView data = header.findViewById(R.id.tv_header2_upload_date);
        data.setText(DetailComment1Fragment.date);


        ImageButton sendButton = header.findViewById(R.id.bt_header2_send);


        //댓글 받아오는 api 호출
        int callNum;
        if(replyCnt%10 == 0) callNum = replyCnt/10;
        else callNum = replyCnt/10+1;
        for(int i=1;i<=callNum;i++)
            getReply(i);


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
                   check = false;
                   String commentContent = mComment.getText().toString();
                   mSubCommentList.get(position).setmCommentContent(mComment.getText().toString());
                   mSubCommentList.get(position).setmUploadDate( mSubCommentList.get(position).getmUploadDate());
                   mAdapter.notifyDataSetChanged();
                   mComment.setText("");
                   //키보드 내리기
                   DetailActivity.imm.hideSoftInputFromWindow(mComment.getWindowToken(), 0);
                   Toast.makeText(mContext, commentIdx+"랑"+mSubCommentList.get(position).getmReplyIdx(), Toast.LENGTH_SHORT).show();
                   DetailComment2Service.patchReply(DetailActivity.videoIdx,commentIdx,commentContent,mSubCommentList.get(position).getmReplyIdx());
                   //서버에 수정사항 전송하기
               }else{ //새 댓글 작성
                   String commentContent = mComment.getText().toString();
                   mAdapter.notifyDataSetChanged();
                   mComment.setText("");
                   DetailActivity.imm.hideSoftInputFromWindow(mComment.getWindowToken(), 0);
                   DetailComment2Service.postReply(DetailActivity.videoIdx,commentIdx,commentContent);
                   //서버에 답글 전송하기 add !
               }

           }
       });

        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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

    private void getReply(int i) {
        comment2Service.getReply(DetailActivity.videoIdx,commentIdx,i);
    }

    public static void onModify(int pos){
        Toast.makeText(mContext, "포지션"+pos, Toast.LENGTH_SHORT).show();
        listView.smoothScrollToPosition(0);
        check = true;
        position = pos;
    }

    public static void onDelete(int pos){
        Toast.makeText(mContext, "포지션"+pos, Toast.LENGTH_SHORT).show();
        DetailComment2Service.deleteReply(mSubCommentList.get(position).getmVideoIdx(),mSubCommentList.get(position).getmCommentIdx(),mSubCommentList.get(position).getmReplyIdx());
        mSubCommentList.remove(pos);
        mAdapter.notifyDataSetChanged();
    }

    public void addItem(String profileImage,String channelName,String uploadDate,String content,int thumbUpCount,int replyIdx){
        SubComment item = new SubComment();

        item.setmProfileImage(profileImage);
        item.setmChannelName(channelName);
        item.setmUploadDate(uploadDate);
        item.setmCommentContent(content);
        item.setmThumbUpCount(thumbUpCount);
        item.setmReplyIdx(replyIdx);


        mSubCommentList.add(0,item);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void validateSuccess(DeleteReplyResponse result) {

    }

    @Override
    public void validateSuccess(PatchReplyResponse result) {
        Toast.makeText(getActivity(), result.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void validateSuccess(PostReplyResponse result) { //POST

       addItem("https://firebasestorage.googleapis.com/v0/b/clone-e7f75.appspot.com/o/profile%2FRita%20Go.png?alt=media&token=9eb60db4-8d03-4af3-9a68-ecbc0b9a858f",
                "sun",
                "방금 전",
                result.getResult().getReplyText(),
                0,
               result.getResult().getReplyIdx());

        //Toast.makeText(mContext, result.getMessage(), Toast.LENGTH_SHORT).show();
        // System.out.println(result.getMessage());
    }

    @Override
    public void validateSuccess(GetReplyResponse.Result[] result) { //GET
        for(GetReplyResponse.Result r : result){
            SubComment item = new SubComment();
            item.setmChannelName("sun");
            item.setmThumbUpCount(r.getLikesCount());
            item.setmReplyIdx(r.getReplyIdx());
            item.setmCommentIdx(r.getCommentsIdx());
            item.setmVideoIdx(r.getVideoIdx());
            item.setmCommentContent(r.getReplyText());
            //item.setmProfileImage();
            //item.setmChannelName();
            //item.setmUploadDate();

            mSubCommentList.add(item);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void validateFailure(String message) {

    }
}