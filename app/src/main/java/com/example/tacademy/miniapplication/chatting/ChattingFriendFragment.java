package com.example.tacademy.miniapplication.chatting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tacademy.miniapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChattingFriendFragment extends Fragment {


    public ChattingFriendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chatting_friend, container, false);
    }

}
