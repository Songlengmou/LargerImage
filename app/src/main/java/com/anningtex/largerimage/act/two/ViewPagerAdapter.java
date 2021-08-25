package com.anningtex.largerimage.act.two;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.anningtex.largerimage.R;
import com.anningtex.largerimage.bean.PictureEntity;
import com.anningtex.largerimage.weight.BitmapUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.luck.picture.lib.photoview.PhotoView;

import java.util.List;

/**
 * @Author: Song
 */
public class ViewPagerAdapter extends BaseViewPagerAdapter<PictureEntity> {
    private boolean isHasTitle;

    public ViewPagerAdapter(Context context, List<PictureEntity> imgList) {
        super(context, imgList);
    }

    public boolean isHasTitle() {
        return isHasTitle;
    }

    public void setHasTitle(boolean hasTitle) {
        isHasTitle = hasTitle;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        PictureEntity pictureEntity = imgList.get(position);
        View itemView = getItemView(R.layout.view_pager_img);
        PhotoView imageView = itemView.findViewById(R.id.item_img_view_pager);
        if (isHasTitle()) {
            if (imgList.get(position) != null) {
                Glide.with(container.getContext())
                        .asBitmap()
                        .load(pictureEntity.getImgOriginUrl())
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                String name = pictureEntity.getName();
                                if (name.equals("挂钩样")) {
                                    imageView.setImageBitmap(resource);
                                } else {
                                    int index = name.length();
                                    if (name.contains(" ")) {
                                        index = name.indexOf(" ");
                                    }
                                    Bitmap topBitmap = BitmapUtil.drawText2Bitmap(name.substring(0, index), R.mipmap.pic_title, container.getContext());
                                    Bitmap bitmap = BitmapUtil.mergeBitmap_TB(topBitmap, resource, false);
                                    imageView.setImageBitmap(bitmap);
                                }
                            }
                        });
            }
        } else {
            Glide.with(container.getContext())
                    .load(pictureEntity.getImgOriginUrl())
                    .into(imageView);
        }
        container.addView(itemView);
        return itemView;
    }
}