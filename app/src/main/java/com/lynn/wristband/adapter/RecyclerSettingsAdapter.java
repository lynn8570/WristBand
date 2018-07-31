package com.lynn.wristband.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lynn.wristband.R;

public class RecyclerSettingsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int ITEM_TYPE_SWITCH = 10;
    private static final int ITEM_TYPE_GROUP = 11;
    private static final int ITEM_TYPE_PREF = 12;


    private String[] mStringArray;


    private Context context;

    public RecyclerSettingsAdapter(Context context) {
        this.context = context;
        initdata();

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == ITEM_TYPE_SWITCH) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_settings, parent, false);
            SettingsViewHolder holder = new SettingsViewHolder(inflate);
            return holder;
        } else if(viewType == ITEM_TYPE_GROUP){
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_settings_group, parent, false);
            SettingsGroupHolder holder = new SettingsGroupHolder(inflate);
            return holder;
        }else {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_settings_pref, parent, false);
            SettingsPrefHolder holder = new SettingsPrefHolder(inflate);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SettingsViewHolder) {
            ((SettingsViewHolder) holder).textView.setText(mStringArray[position]);
        } else if (holder instanceof SettingsGroupHolder) {
            ((SettingsGroupHolder) holder).textView.setText(mStringArray[position]);
        }else if(holder instanceof SettingsPrefHolder){
            ((SettingsPrefHolder) holder).textView.setText(mStringArray[position]);
        }
    }


    private void initdata() {
        mStringArray = context.getResources().getStringArray(R.array.settings_items);

    }


    @Override
    public int getItemViewType(int position) {

        if (position > 14) return ITEM_TYPE_PREF;

        return position % 5 == 0 ? ITEM_TYPE_GROUP : ITEM_TYPE_SWITCH;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int itemViewType = getItemViewType(position);
                    switch (itemViewType) {
                        case ITEM_TYPE_GROUP:
                            return 4;
                        case ITEM_TYPE_SWITCH:
                            return 1;
                        case ITEM_TYPE_PREF:
                            return 4;
                    }
                    return 4;
                }
            });
        }

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

    class SettingsPrefHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        TextView textView;

        public SettingsPrefHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.text_pref);

        }


        @Override
        public void onClick(View v) {

        }
    }

    class SettingsGroupHolder extends RecyclerView.ViewHolder {


        TextView textView;

        public SettingsGroupHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.text_group);

        }


    }
}
