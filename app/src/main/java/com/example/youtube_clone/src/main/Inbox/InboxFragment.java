package com.example.youtube_clone.src.main.Inbox;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.youtube_clone.R;
import com.squareup.picasso.Picasso;

public class InboxFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_inbox, container, false);



    }

    @Override
    public void onResume() {
        super.onResume();
        ImageView iv = getView().findViewById(R.id.Iv);
        Picasso.with(getContext())
                .load("https://firebasestorage.googleapis.com/v0/b/clone-e7f75.appspot.com/o/%E1%84%8B%E1%85%AE%E1%84%86%E1%85%A1%20%E1%84%89%E1%85%A1%E1%84%90%E1%85%A1%E1%86%AB%E1%84%8B%E1%85%B4%20%E1%84%87%E1%85%A1%E1%86%AF%E1%84%80%E1%85%A1%E1%84%85%E1%85%A1%E1%86%A8.png?alt=media&token=f5e9d7eb-f5df-4d97-b100-ab4d958d46ef")
                .into(iv);


    }

}
