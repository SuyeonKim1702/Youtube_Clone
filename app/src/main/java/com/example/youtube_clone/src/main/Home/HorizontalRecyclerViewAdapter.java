package com.example.youtube_clone.src.main.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.youtube_clone.R;
import com.example.youtube_clone.src.main.RecyclerViewItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<RecyclerViewItem> mDataList;
    private int mRowIndex = -1;
    Context context;

    public HorizontalRecyclerViewAdapter(ArrayList<RecyclerViewItem> list) {
        mDataList = list;
    }

    public void setRowIndex(int index) {
        mRowIndex = index;
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView storyImage,profileImage;
        private TextView channelName;

        public ItemViewHolder(View itemView) {
            super(itemView);
           storyImage = itemView.findViewById(R.id.iv_inner_storyImage);
           storyImage.setClipToOutline(true);
           profileImage = itemView.findViewById(R.id.iv_inner_profileImage);
           profileImage.setClipToOutline(true);
           channelName = itemView.findViewById(R.id.tv_inner_channelName);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.inner_recycler_view_item, parent, false);
        ItemViewHolder holder = new ItemViewHolder(itemView);
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder rawHolder, int position) {
        ItemViewHolder holder = (ItemViewHolder) rawHolder;
        RecyclerViewItem item = mDataList.get(position);
        System.out.println(position+"입니다");
        holder.channelName.setText(item.getChannelName());

        Picasso.with(context)
                .load(item.getThumUrl())
                .into(holder.storyImage);

        Picasso.with(context)
                .load(item.getProfileImage())
                .into(holder.profileImage);


    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

}