package com.wsg.xsybbs.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wsg.xsybbs.R;

/**
 * Created by wsg
 * on         2018/6/28.
 * function:  贴吧Fragment
 */
public class NoteFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
    }
}
