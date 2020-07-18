package com.example.youtube_clone.src.main.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.example.youtube_clone.R;
import com.example.youtube_clone.src.main.ListViewAdapter;
import com.example.youtube_clone.src.splash.models.DefaultResponse;


public class HomeFragment extends ListFragment {

    ListViewAdapter adapter = new ListViewAdapter();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Bundle bundle = this.getArguments();
        if (bundle != null && bundle.getInt("check") == 1) { //맨처음에만 실행되도록 함
            DefaultResponse.Result[] result = (DefaultResponse.Result[]) bundle.getSerializable("result");
            setListAdapter(adapter) ;
            for(DefaultResponse.Result r:result)
            adapter.addItem(r.getTitle(),r.getChannelName(),r.getViewCount(),r.getUploadDate(),r.getThumUrl(),0) ;

        }
    }


    @Override
    public void onResume() {  //Fragment를 다 그렸을 때 호출된다, ui 변경 작업 가능
        super.onResume();

    }


}