package com.example.youtube_clone.src.detail.DefaultFragment;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtube_clone.R;
import com.example.youtube_clone.src.main.Home.HorizontalRecyclerViewAdapter;
import com.example.youtube_clone.src.main.RecyclerViewItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.youtube_clone.src.detail.DefaultFragment.DetailDefaultFragment.channelId;
import static com.example.youtube_clone.src.detail.DefaultFragment.DetailDefaultFragment.channelNameHeader;
import static com.example.youtube_clone.src.detail.DefaultFragment.DetailDefaultFragment.commentContent;
import static com.example.youtube_clone.src.detail.DefaultFragment.DetailDefaultFragment.commentCountHeader;
import static com.example.youtube_clone.src.detail.DefaultFragment.DetailDefaultFragment.commentImage;
import static com.example.youtube_clone.src.detail.DefaultFragment.DetailDefaultFragment.createdAtHeader;
import static com.example.youtube_clone.src.detail.DefaultFragment.DetailDefaultFragment.likesStatusHeader;
import static com.example.youtube_clone.src.detail.DefaultFragment.DetailDefaultFragment.mList;
import static com.example.youtube_clone.src.detail.DefaultFragment.DetailDefaultFragment.profileImageHeader;
import static com.example.youtube_clone.src.detail.DefaultFragment.DetailDefaultFragment.replaceFragment;
import static com.example.youtube_clone.src.detail.DefaultFragment.DetailDefaultFragment.saveButton;
import static com.example.youtube_clone.src.detail.DefaultFragment.DetailDefaultFragment.savesState;
import static com.example.youtube_clone.src.detail.DefaultFragment.DetailDefaultFragment.subscribeCountHeader;
import static com.example.youtube_clone.src.detail.DefaultFragment.DetailDefaultFragment.subscribeStatus;
import static com.example.youtube_clone.src.detail.DefaultFragment.DetailDefaultFragment.thumbDownButton;
import static com.example.youtube_clone.src.detail.DefaultFragment.DetailDefaultFragment.thumbDownCount;
import static com.example.youtube_clone.src.detail.DefaultFragment.DetailDefaultFragment.thumbDownCountHeader;
import static com.example.youtube_clone.src.detail.DefaultFragment.DetailDefaultFragment.thumbUpButton;
import static com.example.youtube_clone.src.detail.DefaultFragment.DetailDefaultFragment.thumbUpCount;
import static com.example.youtube_clone.src.detail.DefaultFragment.DetailDefaultFragment.thumbUpCountHeader;
import static com.example.youtube_clone.src.detail.DefaultFragment.DetailDefaultFragment.videoIndex;
import static com.example.youtube_clone.src.detail.DefaultFragment.DetailDefaultFragment.videoTitleHeader;
import static com.example.youtube_clone.src.detail.DefaultFragment.DetailDefaultFragment.viewCountHeader;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static RecyclerView horizontalList;
    private ArrayList<RecyclerViewItem> mData = null ;
    private ArrayList<RecyclerViewItem> mHorizontalData = null ;
    private static Context mContext;
    private final int TYPE_HEADER = 5;


    public interface OnItemClickListener {
        void onItemClick(View v, int position) ;
    }

    private OnItemClickListener mListener = null ;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder1 extends RecyclerView.ViewHolder {
        TextView tvTitle,channelName,uploadDate,viewCount,timeLine;
        ImageView thumbNail,profileImage;

        ViewHolder1(View v) {
            super(v) ;
            // 뷰 객체에 대한 참조. (hold strong reference)
            tvTitle = v.findViewById(R.id.tv_recyclerviewitem1_title_rec);
            channelName = v.findViewById(R.id.tv_recyclerviewitem1_channelName_rec);
            uploadDate = v.findViewById(R.id.tv_recyclerviewitem1_uploadDate_rec);
            viewCount = v.findViewById(R.id.tv_recyclerviewitem1_viewCount_rec);
            thumbNail = v.findViewById(R.id.iv_recyclerviewitem1_video_rec);
            profileImage = v.findViewById(R.id.iv_recyclerviewitem1_profile_rec);
            timeLine = v.findViewById(R.id.tv_recyclerviewitem1_timeline_rec);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        // 리스너 객체의 메서드 호출.
                        if (mListener != null) {
                            mListener.onItemClick(v, pos) ;
                        }
                    }
                }
            });
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView channelName,uploadDate,content,thumbUpCount,commentCount;
        ImageView image,profileImage,thumbIcon;

        ViewHolder2(View v) {
            super(v) ;
            // 뷰 객체에 대한 참조. (hold strong reference)
            channelName = v.findViewById(R.id.tv_recyclerviewitem2_channelName);
            uploadDate = v.findViewById(R.id.tv_recyclerviewitem2_uploadDate);
            commentCount = v.findViewById(R.id.tv_recyclerviewitem2_commentNum);
            thumbUpCount = v.findViewById(R.id.tv_recyclerviewitem2_thumbUpNum);
            content = v.findViewById(R.id.tv_recyclerviewitem2_content);
            image = v.findViewById(R.id.iv_recyclerviewitem2_photo);
            image.setClipToOutline(true);
            profileImage = v.findViewById(R.id.iv_recyclerviewitem2_profile);
            thumbIcon = v.findViewById(R.id.iv_recyclerviewitem2_thumbIcon);
            thumbIcon.setClipToOutline(true);
        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder {

        private HorizontalRecyclerViewAdapter horizontalAdapter;
        TextView title;

        ViewHolder3(View v) {
            super(v) ;
            Context context = itemView.getContext();
            title = v.findViewById(R.id.tv_recyclerviewitem3_story);
            horizontalList = itemView.findViewById(R.id.rv_recyclerviewitem3_horizontalRv);
            horizontalList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            horizontalAdapter = new HorizontalRecyclerViewAdapter(mHorizontalData);
            horizontalList.setAdapter(horizontalAdapter);
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        TextView saveText, videoTitle, viewCount, createdAt, thumbUpCount, thumbDownCount, subscribeCount, commentCount, commentContent,channelName, subscribe;
        ImageView profileImage, commentImage;
        ImageButton thumbUpButton,thumbDownButton, saveButton, notificationButton;
        LinearLayout commentLayout;

        HeaderViewHolder(View view) {
            super(view);
            saveText = view.findViewById(R.id.tv_detail_save);
            videoTitle = view.findViewById(R.id.tv_detail_title);
            viewCount = view.findViewById(R.id.tv_detail_viewCount);
            thumbUpCount = view.findViewById(R.id.tv_detail_likesCount);
            thumbDownCount = view.findViewById(R.id.tv_detail_dislikesCount);
            createdAt = view.findViewById(R.id.tv_detail_uploadDate);
            subscribeCount = view.findViewById(R.id.tv_detail_subscribeCount);
            commentContent = view.findViewById(R.id.tv_detail_commentPreview);
            commentCount = view.findViewById(R.id.tv_detail_commentCount);
            commentImage = view.findViewById(R.id.iv_detail_subscriber_profile);
            profileImage = view.findViewById(R.id.iv_detail_profileImage);
            channelName = view.findViewById(R.id.tv_detail_channelName);
            thumbUpButton = view.findViewById(R.id.ib_detail_thumbUp);
            thumbDownButton = view.findViewById(R.id.ib_detail_thumbDown);
            saveButton = view.findViewById(R.id.ib_detail_save);
            commentLayout = view.findViewById(R.id.ll_detail_comment);
            notificationButton = view.findViewById(R.id.ib_header_notification);
            subscribe = view.findViewById(R.id.tv_header_subscribe);
        }
    }

    // 생성자
    public RecyclerViewAdapter(ArrayList<RecyclerViewItem> list, ArrayList<RecyclerViewItem> horizontalList) {
        mData = list ;
        mHorizontalData = horizontalList;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        mContext = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (viewType == 0) {
            View view = inflater.inflate(R.layout.recycler_view_item_rec, parent, false);
            RecyclerViewAdapter.ViewHolder1 vh = new RecyclerViewAdapter.ViewHolder1(view);

            return vh;

        } else if (viewType == 1) {
            View view = inflater.inflate(R.layout.recycler_view_item2, parent, false);
            RecyclerViewAdapter.ViewHolder2 vh = new RecyclerViewAdapter.ViewHolder2(view);

            return vh;

        } else if (viewType == 2) {
            View view = inflater.inflate(R.layout.recycler_view_item3, parent, false);
            RecyclerViewAdapter.ViewHolder3 vh = new RecyclerViewAdapter.ViewHolder3(view);

            return vh;
        } else if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.default_header, parent, false);
            RecyclerViewAdapter.HeaderViewHolder vh = new HeaderViewHolder(view);

            return vh;
        }
            return null;

    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolder1) {
            RecyclerViewItem item = mData.get(position);
            ((ViewHolder1) holder).tvTitle.setText(item.getTitle());
            ((ViewHolder1) holder).channelName.setText(item.getChannelName());
            ((ViewHolder1) holder).uploadDate.setText(item.getUploadDate());
            ((ViewHolder1) holder).viewCount.setText(item.getUploadDate());
            ((ViewHolder1) holder).timeLine.setText(item.getmTimeline());

            Picasso.with(mContext)
                    .load(item.getThumUrl())
                    .into(((ViewHolder1) holder).thumbNail);

            Picasso.with(mContext)
                    .load(item.getProfileImage())
                    .into(((ViewHolder1) holder).profileImage);

        } else if (holder instanceof ViewHolder2) {
            RecyclerViewItem item = mData.get(position);
            ((ViewHolder2) holder).channelName.setText(item.getChannelName());
            ((ViewHolder2) holder).uploadDate.setText(item.getUploadDate());
            ((ViewHolder2) holder).commentCount.setText(item.getCommentCount() + "");
            ((ViewHolder2) holder).content.setText(item.getContentText());
            ((ViewHolder2) holder).thumbUpCount.setText(item.getCommentCount() + "");


            Picasso.with(mContext)
                    .load(item.getProfileImage())
                    .into(((ViewHolder2) holder).profileImage);
            if (item.getImage().equals(" ")) {
                ((ViewHolder2) holder).image.setVisibility(View.GONE);
                ((ViewHolder2) holder).thumbIcon.setVisibility(View.GONE);
            } else {
                ((ViewHolder2) holder).thumbIcon.setVisibility(View.VISIBLE);
                ((ViewHolder2) holder).image.setVisibility(View.VISIBLE);
                Picasso.with(mContext)
                        .load(item.getImage())
                        .into(((ViewHolder2) holder).image);
            }


        } else if (holder instanceof ViewHolder3) {
            ((ViewHolder3) holder).title.setText("스토리");
            ((ViewHolder3) holder).horizontalAdapter.setRowIndex(position);

        } else if (holder instanceof HeaderViewHolder) {

            ((HeaderViewHolder) holder).videoTitle.setText(videoTitleHeader);
            ((HeaderViewHolder) holder).viewCount.setText(viewCountHeader);
            ((HeaderViewHolder) holder).createdAt.setText(createdAtHeader);
            ((HeaderViewHolder) holder).thumbUpCount.setText(thumbUpCountHeader+"");
            ((HeaderViewHolder) holder).thumbDownCount.setText(thumbDownCountHeader+"");
            ((HeaderViewHolder) holder).commentCount.setText(commentCountHeader+"");
            ((HeaderViewHolder) holder).channelName.setText(channelNameHeader);
            ((HeaderViewHolder) holder).subscribeCount.setText(subscribeCountHeader+"");
            ((HeaderViewHolder) holder).commentContent.setText(commentContent);


            Picasso.with(mContext)
                    .load(commentImage)
                    .into(((HeaderViewHolder) holder).commentImage);

            Picasso.with(mContext)
                    .load(profileImageHeader)
                    .into(((HeaderViewHolder) holder).profileImage);

            if(likesStatusHeader == 1) ((HeaderViewHolder) holder).thumbUpButton.setColorFilter(Color.argb(255,1,78,185));
            else if(likesStatusHeader == 2) ((HeaderViewHolder) holder).thumbDownButton.setColorFilter(Color.argb(255,1,78,185));
            else{
                ((HeaderViewHolder) holder).thumbUpButton.setColorFilter(Color.argb(255,97,97,97));
                ((HeaderViewHolder) holder).thumbDownButton.setColorFilter(Color.argb(255,97,97,97));
            }
            // 저장된 영상인지 여부 확인
            if(savesState){
                ((HeaderViewHolder) holder).saveText.setText("저장됨");
                ((HeaderViewHolder) holder).saveText.setTextColor(Color.argb(255,1,78,185));
                ((HeaderViewHolder) holder).saveButton.setColorFilter(Color.argb(255,1,78,185));
            }
            else{
                ((HeaderViewHolder) holder).saveText.setText("저장");
                ((HeaderViewHolder) holder).saveText.setTextColor(Color.argb(255,97,97,97));
                ((HeaderViewHolder) holder).saveButton.setColorFilter(Color.argb(255,97,97,97));
            }

            if(subscribeStatus){
                ((HeaderViewHolder) holder).notificationButton.setVisibility(View.VISIBLE);
                ((HeaderViewHolder) holder).subscribe.setText("구독중");
                ((HeaderViewHolder) holder).subscribe.setTextColor(Color.argb(255,97,97,97));
            }else{
                ((HeaderViewHolder) holder).notificationButton.setVisibility(View.GONE);
                ((HeaderViewHolder) holder).subscribe.setText("구독");
            }


            ((HeaderViewHolder) holder).thumbUpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(likesStatusHeader == 1){
                        likesStatusHeader = 0;
                        ((HeaderViewHolder) holder).thumbUpCount.setText(--thumbUpCountHeader+"");
                        ((HeaderViewHolder) holder).thumbUpButton.setColorFilter(Color.argb(255,97,97,97));

                    } else if(likesStatusHeader == 2){
                        likesStatusHeader = 1;
                        ((HeaderViewHolder) holder).thumbUpCount.setText(++thumbUpCountHeader+"");
                        ((HeaderViewHolder) holder).thumbDownCount.setText(--thumbDownCountHeader+"");
                        ((HeaderViewHolder) holder).thumbDownButton.setColorFilter(Color.argb(255,97,97,97));
                        ((HeaderViewHolder) holder).thumbUpButton.setColorFilter(Color.argb(255,1,78,185));

                    } else if(likesStatusHeader == 0){
                        likesStatusHeader = 1;
                        ((HeaderViewHolder) holder).thumbUpCount.setText(++thumbUpCountHeader+"");
                        ((HeaderViewHolder) holder).thumbUpButton.setColorFilter(Color.argb(255,1,78,185));

                    }

                    DetailDefaultFragment.updateLikes(likesStatusHeader);
                }
            });
            ((HeaderViewHolder) holder).thumbDownButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(likesStatusHeader == 1){
                        likesStatusHeader = 2;
                        ((HeaderViewHolder) holder).thumbUpCount.setText(--thumbUpCountHeader+"");
                        ((HeaderViewHolder) holder).thumbDownCount.setText(++thumbDownCountHeader+"");
                        ((HeaderViewHolder) holder).thumbUpButton.setColorFilter(Color.argb(255,97,97,97));
                        ((HeaderViewHolder) holder).thumbDownButton.setColorFilter(Color.argb(255,1,78,185));

                    } else if(likesStatusHeader == 2){
                        likesStatusHeader = 0;
                        ((HeaderViewHolder) holder).thumbDownCount.setText(--thumbDownCountHeader+"");
                        ((HeaderViewHolder) holder).thumbDownButton.setColorFilter(Color.argb(255,97,97,97));

                    } else if(likesStatusHeader == 0){
                        likesStatusHeader = 2;
                        ((HeaderViewHolder) holder).thumbDownCount.setText(++thumbDownCountHeader+"");
                        ((HeaderViewHolder) holder).thumbDownButton.setColorFilter(Color.argb(255,1,78,185));

                    }
                    DetailDefaultFragment.updateLikes(likesStatusHeader);
                }
            });


            ((HeaderViewHolder) holder).commentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    replaceFragment();
                }
            });

            ((HeaderViewHolder) holder).subscribe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    if(subscribeStatus){
                        subscribeStatus = false;
                        ((HeaderViewHolder) holder).notificationButton.setVisibility(View.GONE);
                        ((HeaderViewHolder) holder).subscribe.setText("구독");
                        ((HeaderViewHolder) holder).subscribe.setTextColor(Color.argb(255,189,45,39));
                    }else{
                        subscribeStatus = true;
                        ((HeaderViewHolder) holder).notificationButton.setVisibility(View.VISIBLE);
                        ((HeaderViewHolder) holder).subscribe.setTextColor(Color.argb(255,97,97,97));
                        ((HeaderViewHolder) holder).subscribe.setText("구독중");
                    }
                    DetailDefaultService.updateSubscribe(1,channelId);
                }

            });

            ((HeaderViewHolder) holder).saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(savesState){
                        savesState = false;
                        ((HeaderViewHolder) holder).saveText.setText("저장");
                        ((HeaderViewHolder) holder).saveText.setTextColor(Color.argb(255,97,97,97));
                        ((HeaderViewHolder) holder).saveButton.setColorFilter(Color.argb(255,97,97,97));
                    }
                    else {
                        savesState = true;
                        ((HeaderViewHolder) holder).saveText.setText("저장됨");
                        ((HeaderViewHolder) holder).saveText.setTextColor(Color.argb(255,1,78,185));
                        ((HeaderViewHolder) holder).saveButton.setColorFilter(Color.argb(255,1,78,185));
                    }

                    DetailDefaultService.updateSaved(videoIndex);
                }
            });

        }
    }


    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size();
    }

    //item 뷰 타입 리턴.
    @Override
    public int getItemViewType(int position) {
/*
        if(position ==0) {
            RecyclerViewItem rI = new RecyclerViewItem();
            mList.add(rI);
            notifyDataSetChanged();
            return TYPE_HEADER;
        }
*/
        return mData.get(position).getType();
    }

}