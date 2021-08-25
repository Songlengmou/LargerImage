package com.anningtex.largerimage;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * @Author Song
 * @Date：2021-08-24
 */
public class MyApp extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                //缓存图片最大的长和宽
                .memoryCacheExtraOptions(480, 800)
                //线程池的数量
                .threadPoolSize(2)
                .threadPriority(4)
                //设置内存缓存区大小
                .memoryCacheSize(5 * 1024 * 1024)
                //设置sd卡缓存区大小
                .diskCacheSize(20 * 1024 * 1024)
                // .diskCache(new UnlimitedDiscCache(cachefile))//自定义磁盘缓存目录
                //打印日志内容
                .writeDebugLogs()
                //给缓存的文件名进行md5加密处理
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .build();
        ImageLoader.getInstance().init(configuration);
    }

    public static Context getContext() {
        return context;
    }
}