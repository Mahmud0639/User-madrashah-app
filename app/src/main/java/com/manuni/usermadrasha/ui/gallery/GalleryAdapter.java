package com.manuni.usermadrasha.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.manuni.usermadrasha.FullImageView;
import com.manuni.usermadrasha.R;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewAdapter> {
    private final Context context;
    private final List<ImageModel> list;

    public GalleryAdapter(Context context, List<ImageModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public GalleryViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gallery_image,parent,false);

        return new GalleryViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewAdapter holder, int position) {

        ImageModel data = list.get(position);
        try {
            Glide.with(context).load(data.getImage()).placeholder(R.drawable.impl5).into(holder.imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullImageView.class);
                intent.putExtra("image",data.getImage());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GalleryViewAdapter extends RecyclerView.ViewHolder {
        ImageView imageView;

        public GalleryViewAdapter(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
