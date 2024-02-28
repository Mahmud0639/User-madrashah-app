package com.manuni.usermadrasha.ui.autoimageslider.autoimageadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.manuni.usermadrasha.R;
import com.manuni.usermadrasha.ui.autoimageslider.autoimagemodel.AutoImage;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class AutoImageAdapter extends SliderViewAdapter<AutoImageAdapter.AutoImageViewHolder> {
    private Context context;
    private List<AutoImage> list;

    public AutoImageAdapter(Context context, List<AutoImage> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public AutoImageViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.auto_image_sample,parent,false);
        return new AutoImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AutoImageViewHolder viewHolder, int position) {
        AutoImage data = list.get(position);
        try {
            Glide.with(context).load(data.getImage()).into(viewHolder.myAutoImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }


    public class AutoImageViewHolder extends SliderViewAdapter.ViewHolder {
        View itemView;
        private ImageView myAutoImage;

        public AutoImageViewHolder(@NonNull View itemView) {
            super(itemView);
            myAutoImage = itemView.findViewById(R.id.myAutoImage);
            this.itemView = itemView;
        }
    }
}

