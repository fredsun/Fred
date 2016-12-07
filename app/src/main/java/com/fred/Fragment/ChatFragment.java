package com.fred.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fred.R;

/**
 * Created by Administrator on 2016/12/7.
 */

public class ChatFragment extends Fragment {
    public static Fragment newInstance(String name){
        Fragment fragment = new ChatFragment();
        Bundle bundle = new Bundle();
        bundle.putString("fragmentName",name);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat,container,false);
    }
}
