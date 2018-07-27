package com.lynn.wristband.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lynn.wristband.R;

public class RecyclerStatusAdapter extends RecyclerView.Adapter<RecyclerStatusAdapter.StatusViewHolder> {


    @NonNull
    @Override
    public StatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.status_adapter, parent, false);
        StatusViewHolder holder = new StatusViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StatusViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 3;
    }

    class StatusViewHolder extends RecyclerView.ViewHolder {


        ImageView imageView;
        TextView textName;
        TextView textData;
        TextView textNote;

        public StatusViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.status_icon);
            textName = itemView.findViewById(R.id.status_name);
            textData = itemView.findViewById(R.id.status_data);
            textNote = itemView.findViewById(R.id.status_note);
        }
    }
}
