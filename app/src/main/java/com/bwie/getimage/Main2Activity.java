package com.bwie.getimage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

public class Main2Activity extends AppCompatActivity {

    private PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        photoView = (PhotoView) findViewById(R.id.photo);
        Intent intent = getIntent();
        String string = intent.getStringExtra("image");
        Glide.with(this).load(string).into(photoView);

    }
}
