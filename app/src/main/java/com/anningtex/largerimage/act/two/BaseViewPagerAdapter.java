package com.anningtex.largerimage.act.two;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * @author Song
 */
public class BaseViewPagerAdapter<T> extends PagerAdapter {
    private Context context;
    /**
     * 图片的数据源
     */
    public List<T> imgList;

    public BaseViewPagerAdapter(Context context, List<T> imgList) {
        this.context = context;
        this.imgList = imgList;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return imgList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == object);
    }

    /**
     * destroyItem()：删除当前的View;
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public View getItemView(int layoutId) {
        View itemView = LayoutInflater.from(this.context).inflate(layoutId, null, false);
        return itemView;
    }
}