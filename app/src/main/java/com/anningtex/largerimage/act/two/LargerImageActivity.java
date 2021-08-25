package com.anningtex.largerimage.act.two;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.anningtex.largerimage.R;
import com.anningtex.largerimage.bean.PictureEntity;

import java.util.List;

/**
 * @author Song
 * desc:放大缩小，且左右滑动
 */
public class LargerImageActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private int index;
    private List<PictureEntity> pictureEntities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_larger_image);
        initView();
    }

    private void initView() {
        ViewPager mViewPager = findViewById(R.id.view_pager);
        Bundle bundle = getIntent().getExtras();
        pictureEntities = (List<PictureEntity>) bundle.getSerializable("imgList");
        index = bundle.getInt("index");
        // 判断图片是否需要拼接
        boolean isHasTitle = bundle.getBoolean("isHasTitle", true);
        mViewPager.addOnPageChangeListener(this);
        ViewPagerAdapter mAdapter = new ViewPagerAdapter(this, pictureEntities);
        mViewPager.setAdapter(mAdapter);
        mAdapter.setHasTitle(isHasTitle);
        setTitle(index + 1 + "/" + pictureEntities.size());
        mViewPager.setCurrentItem(index);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        index = position;
        setTitle(position + 1 + "/" + pictureEntities.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}