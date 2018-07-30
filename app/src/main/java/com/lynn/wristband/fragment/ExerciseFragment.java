package com.lynn.wristband.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lynn.wristband.R;
import com.lynn.wristband.adapter.RecyclerExerciseAdapter;

/**
 * Created by zowee-laisc on 2018/7/11.
 */

public class ExerciseFragment extends Fragment {

    RecyclerView mRecyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.i("linlian","ExerciseFragment onCreateView=");
        View root = inflater.inflate(R.layout.fragment_exercise, container, false);

        mRecyclerView = root.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mRecyclerView.setAdapter(new RecyclerExerciseAdapter(getContext()));
        return root;
    }


}
