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

import com.example.youtube_clone.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SubCommentAdapter extends BaseAdapter {

    SubComment subComment;
    private ArrayList<SubComment> mSubCommentList;
    private LayoutInflater mInflate;
    static Context mContext;
    int newstate;

    SubCommentAdapter(ArrayList<SubComment> data, Context context){
        mSubCommentList = data; // 글 목록 리스트를 불러옴
        mInflate= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mSubCommentList.size();
    }

    @Override
    public Object getItem(int position) {
        return mSubCommentList.get(position);
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
        TextView channelName,uploadDate,content,thumbUpCount;
        ImageView profile;
        ImageButton thumbUpButton,thumbDownButton,moreButton;

        public void bind(String channelName, String uploadDate, String commentContent, int thumbUpCount, int thumbUp, String profileImage) {
            this.channelName.setText(channelName);
            this.uploadDate.setText(uploadDate);
            this.content.setText(commentContent);
            this.thumbUpCount.setText(thumbUpCount+"");

            Picasso.with(mContext)
                    .load(profileImage)
                    .into(this.profile);


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
                    v = mInflate.inflate(R.layout.comment2_listview_item, parent, false);
                    userViewHolder = new UserViewHolder();
                    userViewHolder.channelName = v.findViewById(R.id.tv_listView2_channelName);
                    userViewHolder.uploadDate = v.findViewById(R.id.tv_listView2_upload_date);
                    userViewHolder.content = v.findViewById(R.id.tv_listview2_content);
                    userViewHolder.thumbUpCount = v.findViewById(R.id.tv_listView2_thumbUpCount);
                    userViewHolder.thumbUpButton = v.findViewById(R.id.ib_listview2_thumbUp);
                    userViewHolder.thumbDownButton = v.findViewById(R.id.ib_listview2_thumbDown);
                    userViewHolder.profile = v.findViewById(R.id.iv_listview2_profile);
                    userViewHolder.moreButton = v.findViewById(R.id.ib_listview2_more);


                    v.setTag(userViewHolder); //태그를 이용해 저장
                } else {
                    userViewHolder = (UserViewHolder) v.getTag(); //태그를 이용해 받아오기
                }
                System.out.println(position);

                userViewHolder.bind(
                        mSubCommentList.get(position).getmChannelName(),
                        mSubCommentList.get(position).getmUploadDate(),
                        mSubCommentList.get(position).getmCommentContent(),
                        mSubCommentList.get(position).getmThumbUpCount(),
                        mSubCommentList.get(position).isThumbUpOrNot(),
                        mSubCommentList.get(position).getmProfileImage()

                        ); //아이템과 어댑터 바인딩


                userViewHolder.thumbUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSubCommentList.get(position).isThumbUpOrNot() == 1){
                    newstate = 0;
                    mSubCommentList.get(position).setmThumbUpCount(mSubCommentList.get(position).getmThumbUpCount()-1);
                }
                else if(mSubCommentList.get(position).isThumbUpOrNot() == 2){
                    newstate = 1;
                    mSubCommentList.get(position).setmThumbUpCount(mSubCommentList.get(position).getmThumbUpCount()+1);
                }
                else if(mSubCommentList.get(position).isThumbUpOrNot() == 0){
                    newstate = 1;
                    mSubCommentList.get(position).setmThumbUpCount(mSubCommentList.get(position).getmThumbUpCount()+1);
                }
                mSubCommentList.get(position).setThumbUpOrNot(newstate);
                notifyDataSetChanged();

            }
        });
        userViewHolder.thumbDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSubCommentList.get(position).isThumbUpOrNot() == 1){
                    newstate = 2;
                    mSubCommentList.get(position).setmThumbUpCount(mSubCommentList.get(position).getmThumbUpCount()-1);
                }
                else if(mSubCommentList.get(position).isThumbUpOrNot() == 2){
                    newstate = 0;
                    
                }
                else if(mSubCommentList.get(position).isThumbUpOrNot() == 0){
                    newstate = 2;
                }
                mSubCommentList.get(position).setThumbUpOrNot(newstate);
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
                                DetailComment2Fragment.mComment.setText(mSubCommentList.get(position).getmCommentContent());
                                DetailComment2Fragment.onModify(position);
                                DetailActivity.imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

                                break;
                            case R.id.popup_item_delete:
                                DetailComment2Fragment.mSubCommentList.remove(position);
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
