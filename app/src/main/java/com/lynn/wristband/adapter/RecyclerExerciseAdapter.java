package com.lynn.wristband.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lynn.wristband.R;

public class RecyclerExerciseAdapter extends RecyclerView.Adapter<RecyclerExerciseAdapter.ExerciseViewHolder> {


    private String[] mStringArray;
    private int[] mImageResid;


    private Context context;

    public RecyclerExerciseAdapter(Context context) {
        this.context = context;
        initdata();

    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_excercise, parent, false);
        ExerciseViewHolder holder = new ExerciseViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {

        Log.i("linlian","onBindViewHolder position="+position);

        holder.textView.setText(mStringArray[position]);
        setTopDrawables(holder.textView, mImageResid[position]);
    }

    private void setTopDrawables(TextView textView, int resid) {
        Drawable drawable = context.getResources().getDrawable(resid);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());

        textView.setCompoundDrawables(null, drawable, null, null);
    }

    private void initdata() {
        mStringArray = context.getResources().getStringArray(R.array.exercise_items);
        TypedArray ar = context.getResources().obtainTypedArray(R.array.exercise_images);
        int len = ar.length();
        mImageResid = new int[len];
        for (int i = 0; i < len; i++)
            mImageResid[i] = ar.getResourceId(i, 0);

        ar.recycle();
    }

    @Override
    public int getItemCount() {
        if (mStringArray != null) {
            return mStringArray.length;
        }
        return 0;
    }

    class ExerciseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView textView;

        public ExerciseViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.text_excercise);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
