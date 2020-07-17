package com.example.youtube_clone.src.main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.youtube_clone.R;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>();
    private LayoutInflater mInflate;

    public ListViewAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    // adapter에 사용되는 아이템의 type 개수
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }


    public static class UserViewHolder {
        TextView tvTitle;
        TextView tvContent;


        public void bind(String tvTitle) {
            this.tvTitle.setText(tvTitle);


        }
    }

    public static class UserViewHolder2 {
        TextView tvTitle;
        TextView tvContent;
        ImageView photo;


        public void bind(String tvTitle, String tvContent, Drawable photo, boolean c) {
            this.tvTitle.setText(tvTitle);
            this.tvContent.setText(tvContent);
            this.photo.setImageDrawable(photo);

        }
    }


    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        int viewType = getItemViewType(position);
        final Context context = parent.getContext();
        mInflate= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        switch (viewType) {
            case 0:
                UserViewHolder userViewHolder;
                View v = convertView;
                if (v == null) {

                    v = mInflate.inflate(R.layout.listviewitem, parent, false);
                    userViewHolder = new UserViewHolder();
                    userViewHolder.tvTitle = v.findViewById(R.id.tv_listviewitem_title);
                    v.setTag(userViewHolder); //태그를 이용해 저장
                } else {
                    userViewHolder = (UserViewHolder) v.getTag(); //태그를 이용해 받아오기
                }
                userViewHolder.bind(listViewItemList.get(position).getTitle()); //아이템과 어댑터 바인딩
                return v;
            default: return convertView;
        }

    }

    // 아이템 데이터 추가를 위한 함수.
    public void addItem(String title) {
        ListViewItem item = new ListViewItem();

        item.setTitle(title);

        listViewItemList.add(item);
    }
}