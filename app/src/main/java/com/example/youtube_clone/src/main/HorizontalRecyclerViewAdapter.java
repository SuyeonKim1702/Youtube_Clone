package com.example.youtube_clone.src.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtube_clone.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<ListViewItem> mDataList;
    private int mRowIndex = -1;
    Context context;

    public HorizontalRecyclerViewAdapter(ArrayList<ListViewItem> list) {
        mDataList = list;
    }

    public void setRowIndex(int index) {
        mRowIndex = index;
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView storyImage;

        public ItemViewHolder(View itemView) {
            super(itemView);
           storyImage = itemView.findViewById(R.id.iv_inner_storyImage);
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
        ListViewItem item = mDataList.get(position);
        Picasso.with(context)
                .load(item.getImage())
                .into(holder.storyImage);
        System.out.println(item.getImage()+"흑흑");

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

}