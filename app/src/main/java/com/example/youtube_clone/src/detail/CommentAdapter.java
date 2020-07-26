package com.example.youtube_clone.src.detail;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.youtube_clone.R;
import com.example.youtube_clone.src.main.Home.RecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CommentAdapter extends BaseAdapter {

    Comment comment;
    private ArrayList<Comment> mCommentList;
    private LayoutInflater mInflate;
    static Context mContext;
    int newstate;

    CommentAdapter(ArrayList<Comment> data, Context context){
        mCommentList = data; // 글 목록 리스트를 불러옴
        mInflate= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mCommentList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCommentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }


    public static class UserViewHolder {
        TextView channelName,uploadDate,commentCount,content,thumbUpCount,hasComment;
        ImageView profile;
        ImageButton thumbUpButton,thumbDownButton,moreButton;

        public void bind(String channelName, String uploadDate, int commentCount, String commentContent, int thumbUpCount, int thumbUp, String profileImage) {
            this.channelName.setText(channelName);
            this.uploadDate.setText(uploadDate);
            this.commentCount.setText(commentCount+"");
            this.content.setText(commentContent);
            this.thumbUpCount.setText(thumbUpCount+"");

            Picasso.with(mContext)
                    .load(profileImage)
                    .into(this.profile);

            if(commentCount == 0){
                this.hasComment.setVisibility(View.GONE);
            }else{
                this.hasComment.setText("답글 "+commentCount+"개");
            }
            //이미 좋아요를 누른 상태이면
            switch (thumbUp){
                case 1:
                    this.thumbUpButton.setColorFilter(Color.argb(255,1,78,185));
                    this.thumbDownButton.setColorFilter(Color.argb(255,97,97,97));
                    break;
                case 2:
                    this.thumbDownButton.setColorFilter(Color.argb(255,1,78,185));
                    this.thumbUpButton.setColorFilter(Color.argb(255,97,97,97));
                    break;
                case 0:
                    this.thumbUpButton.setColorFilter(Color.argb(255,97,97,97));
                    this.thumbDownButton.setColorFilter(Color.argb(255,97,97,97));
                    break;
            }

        }
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

                UserViewHolder userViewHolder;
                View v = convertView;
                mContext= parent.getContext();

                if (v == null) {
                    v = mInflate.inflate(R.layout.comment1_listview_item, parent, false);
                    userViewHolder = new UserViewHolder();
                    userViewHolder.channelName = v.findViewById(R.id.tv_listView1_channelName);
                    userViewHolder.uploadDate = v.findViewById(R.id.tv_listView1_upload_date);
                    userViewHolder.commentCount = v.findViewById(R.id.tv_listView1_commentCount);
                    userViewHolder.content = v.findViewById(R.id.tv_listview1_content);
                    userViewHolder.thumbUpCount = v.findViewById(R.id.tv_listView1_thumbUpCount);
                    userViewHolder.thumbUpButton = v.findViewById(R.id.ib_listview1_thumbUp);
                    userViewHolder.thumbDownButton = v.findViewById(R.id.ib_listview1_thumbDown);
                    userViewHolder.profile = v.findViewById(R.id.iv_listview1_profile);
                    userViewHolder.hasComment = v.findViewById(R.id.tv_listVIew1_hasComment);
                    userViewHolder.moreButton = v.findViewById(R.id.ib_listview1_more);


                    v.setTag(userViewHolder); //태그를 이용해 저장
                } else {
                    userViewHolder = (UserViewHolder) v.getTag(); //태그를 이용해 받아오기
                }
                System.out.println(position);

                userViewHolder.bind(
                        mCommentList.get(position).getmChannelName(),
                        mCommentList.get(position).getmUploadDate(),
                        mCommentList.get(position).getmCommentCount(),
                        mCommentList.get(position).getmCommentContent(),
                        mCommentList.get(position).getmThumbUpCount(),
                        mCommentList.get(position).isThumbUpOrNot(),
                        mCommentList.get(position).getmProfileImage()

                        ); //아이템과 어댑터 바인딩


                userViewHolder.thumbUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCommentList.get(position).isThumbUpOrNot() == 1){
                    newstate = 0;
                    mCommentList.get(position).setmThumbUpCount(mCommentList.get(position).getmThumbUpCount()-1);
                }
                else if(mCommentList.get(position).isThumbUpOrNot() == 2){
                    newstate = 1;
                    mCommentList.get(position).setmThumbUpCount(mCommentList.get(position).getmThumbUpCount()+1);
                }
                else if(mCommentList.get(position).isThumbUpOrNot() == 0){
                    newstate = 1;
                    mCommentList.get(position).setmThumbUpCount(mCommentList.get(position).getmThumbUpCount()+1);
                }
                mCommentList.get(position).setThumbUpOrNot(newstate);
                notifyDataSetChanged();

            }
        });
        userViewHolder.thumbDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCommentList.get(position).isThumbUpOrNot() == 1){
                    newstate = 2;
                    mCommentList.get(position).setmThumbUpCount(mCommentList.get(position).getmThumbUpCount()-1);
                }
                else if(mCommentList.get(position).isThumbUpOrNot() == 2){
                    newstate = 0;
                    
                }
                else if(mCommentList.get(position).isThumbUpOrNot() == 0){
                    newstate = 2;
                }
                mCommentList.get(position).setThumbUpOrNot(newstate);
                notifyDataSetChanged();

            }
        });

        userViewHolder.moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 내가 쓴 글일때랑 아닐 때랑 나눠서 코드 짜야 함 나중에
                PopupMenu popupMenu = new PopupMenu(mContext,userViewHolder.moreButton,R.style.myPopupMenuTextAppearanceSmall);
                popupMenu.getMenuInflater().inflate(R.menu.pop_up,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch(item.getItemId()){
                            case R.id.popup_item_modify:
                                DetailComment1Fragment.mComment.setText(mCommentList.get(position).getmCommentContent());
                                DetailComment1Fragment.onModify(position);
                                DetailActivity.imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);


                                break;
                            case R.id.popup_item_delete:

                                DetailComment1Fragment.mCommentList.remove(position);
                                //서버에서 삭제 처리
                                notifyDataSetChanged();
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });



                return v;


    }


}
