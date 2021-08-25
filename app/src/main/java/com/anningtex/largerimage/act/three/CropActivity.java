package com.anningtex.largerimage.act.three;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.anningtex.largerimage.R;
import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;

public class CropActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);
        initView();
    }

    private void initView() {
        PhotoView mPhotoView = findViewById(R.id.photo_view);
        //设置是否允许缩放，默认是不允许的
        mPhotoView.enable();
        mPhotoView.animaFrom(mPhotoView.getInfo());
        // 获取/设置 最大缩放倍数
        mPhotoView.setMaxScale(10f);

        String picUrl = getIntent().getStringExtra("picUrl");
        Glide.with(this).load(picUrl).into(mPhotoView);
    }
}