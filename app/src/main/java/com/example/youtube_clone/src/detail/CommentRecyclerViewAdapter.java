package com.example.youtube_clone.src.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtube_clone.R;
import com.example.youtube_clone.src.main.Home.HorizontalRecyclerViewAdapter;
import com.example.youtube_clone.src.main.RecyclerViewItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CommentRecyclerViewAdapter extends RecyclerView.Adapter<CommentRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Comment> mData = null ;
    private static Context mContext;


    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView channelName,uploadDate,commentCount,content,thumbUpCount,hasComment;
        ImageView profile;
        ImageButton thumbUpButton,thumbDownButton;

        ViewHolder(View v) {
            super(v) ;
            // 뷰 객체에 대한 참조. (hold strong reference)

            channelName = v.findViewById(R.id.tv_listview1_content);

        }
    }


    // 생성자
    public CommentRecyclerViewAdapter(ArrayList<Comment> list) {
        mData = list ;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public CommentRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        mContext = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

            View view = inflater.inflate(R.layout.comment1_listview_item, parent, false) ;
            CommentRecyclerViewAdapter.ViewHolder vh = new CommentRecyclerViewAdapter.ViewHolder(view) ;

        return vh;
        }


    @Override
    public void onBindViewHolder(@NonNull CommentRecyclerViewAdapter.ViewHolder holder, int position) {



        Comment item = mData.get(position);
        holder.channelName.setText(item.getmChannelName());

        }




    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }


}