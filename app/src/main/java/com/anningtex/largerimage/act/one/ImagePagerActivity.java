package com.anningtex.largerimage.act.one;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

import com.anningtex.largerimage.R;
import com.anningtex.largerimage.weight.HackyViewPager;
import com.anningtex.largerimage.weight.SPUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Song
 * desc：图片放大/左右滑动
 */
public class ImagePagerActivity extends FragmentActivity {
    private static final String STATE_POSITION = "STATE_POSITION";
    public static final String EXTRA_IMAGE_INDEX = "image_index";
    public static final String EXTRA_IMAGE_URLS = "image_urls";
    private HackyViewPager mPager;
    private TextView indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_pager);
        mPager = findViewById(R.id.pager);
        indicator = findViewById(R.id.indicator);
        int pagerPosition = getIntent().getIntExtra(EXTRA_IMAGE_INDEX, 0);
        String type = getIntent().getStringExtra("type");

        ArrayList<String> urls;
        if ("1".equals(type)) {
            Gson gson = new Gson();
            String json = (String) SPUtils.get(this, "annexUrlList1", "");
            urls = gson.fromJson(json, new TypeToken<List<Object>>() {
            }.getType());
        } else {
            urls = getIntent().getStringArrayListExtra(EXTRA_IMAGE_URLS);
        }
        ImagePagerAdapter mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), urls);
        mPager.setAdapter(mAdapter);

        CharSequence text = getString(R.string.viewpager_indicator, 1, Objects.requireNonNull(mPager.getAdapter()).getCount());
        indicator.setText(text);
        // 更新下标
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int arg0) {
                CharSequence text = getString(R.string.viewpager_indicator, arg0 + 1, mPager.getAdapter().getCount());
                indicator.setText(text);
            }
        });
        if (savedInstanceState != null) {
            pagerPosition = savedInstanceState.getInt(STATE_POSITION);
        }
        mPager.setCurrentItem(pagerPosition);
    }

    @Override
    public void onSaveInstanceState(@NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_POSITION, mPager.getCurrentItem());
    }

    private static class ImagePagerAdapter extends FragmentStatePagerAdapter {
        public ArrayList<String> fileList;

        public ImagePagerAdapter(FragmentManager fm, ArrayList<String> fileList) {
            super(fm);
            this.fileList = fileList;
        }

        @Override
        public int getCount() {
            return fileList == null ? 0 : fileList.size();
        }

        @Override
        public @NotNull
        Fragment getItem(int position) {
            String url = fileList.get(position);
            return ImageDetailFragment.newInstance(url);
        }
    }
}