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

public class RecyclerSettingsAdapter extends RecyclerView.Adapter<RecyclerSettingsAdapter.SettingsViewHolder> {


    private String[] mStringArray;


    private Context context;

    public RecyclerSettingsAdapter(Context context) {
        this.context = context;
        initdata();

    }

    @NonNull
    @Override
    public SettingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_settings, parent, false);
        SettingsViewHolder holder = new SettingsViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsViewHolder holder, int position) {

        Log.i("linlian", "onBindViewHolder position=" + position);

        holder.textView.setText(mStringArray[position]);
    }


    private void initdata() {
        mStringArray = context.getResources().getStringArray(R.array.settings_items);

    }

    @Override
    public int getItemCount() {
        if (mStringArray != null) {
            return mStringArray.length;
        }
        return 0;
    }

    class SettingsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView textView;

        public SettingsViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.text_settings);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
