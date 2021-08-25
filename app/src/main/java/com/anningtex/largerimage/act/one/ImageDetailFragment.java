package com.anningtex.largerimage.act.one;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import com.anningtex.largerimage.R;
import com.anningtex.largerimage.weight.ToastUtils;
import com.anningtex.photoview.PhotoViewAttache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.Objects;

/**
 * @Author Song
 * @Desc:图片放大/左右滑动
 * @Date：2021-08-24
 */
public class ImageDetailFragment extends Fragment {
    private String mImageUrl;
    private ImageView mImageView;
    private ProgressBar progressBar;
    private PhotoViewAttache mAttache;

    public static ImageDetailFragment newInstance(String imageUrl) {
        ImageDetailFragment fragment = new ImageDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", imageUrl);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageUrl = getArguments() != null ? getArguments().getString("url") : null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.image_detail_fragment, container, false);
        mImageView = v.findViewById(R.id.image);
        progressBar = v.findViewById(R.id.loading);
        mAttache = new PhotoViewAttache(mImageView);
        mAttache.setOnPhotoTapListener((arg0, arg1, arg2) -> Objects.requireNonNull(getActivity()).finish());
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageLoader.getInstance().displayImage(mImageUrl, mImageView, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                String message = null;
                switch (failReason.getType()) {
                    case IO_ERROR:
                        message = getResources().getString(R.string.error);
                        break;
                    case DECODING_ERROR:
                        message = getResources().getString(R.string.noShow);
                        break;
                    case NETWORK_DENIED:
                        message = getResources().getString(R.string.noXia);
                        break;
                    case OUT_OF_MEMORY:
                        message = getResources().getString(R.string.noBig);
                        break;
                    case UNKNOWN:
                        message = getResources().getString(R.string.noCuo);
                        break;
                    default:
                        break;
                }
                ToastUtils.showShortToast(Objects.requireNonNull(getActivity()), message);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                progressBar.setVisibility(View.GONE);
                mAttache.update();
            }
        });
    }
}