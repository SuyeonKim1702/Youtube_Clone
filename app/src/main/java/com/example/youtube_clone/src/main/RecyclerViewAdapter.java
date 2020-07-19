package com.example.youtube_clone.src.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtube_clone.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<ListViewItem> mData = null ;
    private static Context mContext;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,channelName,uploadDate,viewCount;
        ImageView thumbNail;

        ViewHolder(View v) {
            super(v) ;
            // 뷰 객체에 대한 참조. (hold strong reference)
            tvTitle = v.findViewById(R.id.tv_listviewitem_title);
            channelName = v.findViewById(R.id.tv_listviewitem_channelName);
            uploadDate = v.findViewById(R.id.tv_listviewitem_uploadDate);
            viewCount = v.findViewById(R.id.tv_listviewitem_viewCount);
            thumbNail = v.findViewById(R.id.iv_listviewitem_video);
        }
    }

    // 생성자
    public RecyclerViewAdapter(ArrayList<ListViewItem> list) {
        mData = list ;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        mContext = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.listviewitem, parent, false) ;
        RecyclerViewAdapter.ViewHolder vh = new RecyclerViewAdapter.ViewHolder(view) ;

        return vh ;
    }
    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ListViewItem item = mData.get(position) ;
        holder.tvTitle.setText(item.getTitle());
        holder.channelName.setText(item.getChannelName());
        holder.uploadDate.setText(item.getUploadDate());
        holder.viewCount.setText(item.getUploadDate());

        Picasso.with(mContext)
                .load(item.getThumUrl())
                .into(holder.thumbNail);
        System.out.println(item.getChannelName());

    }


    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }

}