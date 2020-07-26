package com.example.youtube_clone.src.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.youtube_clone.R;

public class DetailDefaultFragment extends Fragment implements View.OnClickListener {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_default, null);
        LinearLayout commentLinear = view.findViewById(R.id.ll_detail_comment);
        commentLinear.setOnClickListener(this);
        return view;




    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ll_detail_comment :
                ((DetailActivity)getActivity()).replaceFragment(DetailComment1Fragment.newInstance());
                break;


        }
    }



    public static DetailDefaultFragment newInstance() {
        return new DetailDefaultFragment();
    }

}




