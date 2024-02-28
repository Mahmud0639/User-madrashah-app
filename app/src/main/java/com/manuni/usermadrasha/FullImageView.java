package com.manuni.usermadrasha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

public class FullImageView extends AppCompatActivity {
    private PhotoView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        imageView = findViewById(R.id.imageView);

        String image = getIntent().getStringExtra("image");
        try {
            Glide.with(this).load(image).placeholder(R.drawable.impl5).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}