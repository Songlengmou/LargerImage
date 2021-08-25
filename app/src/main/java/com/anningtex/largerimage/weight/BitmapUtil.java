package com.anningtex.largerimage.weight;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaMetadataRetriever;

import com.anningtex.largerimage.R;

import java.util.HashMap;

/**
 * @Author: Song
 */
public class BitmapUtil {
    /**
     * 获取网络视频第一帧
     *
     * @param videoUrl
     * @return
     */
    public static Bitmap getNetVideoBitmap(String videoUrl) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据url获取缩略图
            retriever.setDataSource(videoUrl, new HashMap<>());
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }

    /**
     * 把两个位图覆盖合成为一个位图，上下拼接
     *
     * @param topBitmap
     * @param bottomBitmap
     * @param isBaseMax    是否以高度大的位图为准，true则小图等比拉伸，false则大图等比压缩
     * @return
     */
    public static Bitmap mergeBitmap_TB(Bitmap topBitmap, Bitmap bottomBitmap, boolean isBaseMax) {
        if (topBitmap == null || topBitmap.isRecycled()
                || bottomBitmap == null || bottomBitmap.isRecycled()) {
//            JDLog.logError(TAG, "topBitmap=" + topBitmap + ";bottomBitmap=" + bottomBitmap);
            return null;
        }
        int width;
        if (isBaseMax) {
            width = Math.max(topBitmap.getWidth(), bottomBitmap.getWidth());
        } else {
            width = Math.min(topBitmap.getWidth(), bottomBitmap.getWidth());
        }
        Bitmap tempBitmapT = topBitmap;
        Bitmap tempBitmapB = bottomBitmap;
        if (topBitmap.getWidth() != width) {
            tempBitmapT = Bitmap.createScaledBitmap(topBitmap, width, (int) (topBitmap.getHeight() * 1f / topBitmap.getWidth() * width), false);
        } else if (bottomBitmap.getWidth() != width) {
            tempBitmapB = Bitmap.createScaledBitmap(bottomBitmap, width, (int) (bottomBitmap.getHeight() * 1f / bottomBitmap.getWidth() * width), false);
        }
        int height = tempBitmapT.getHeight() + tempBitmapB.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Rect topRect = new Rect(0, 0, tempBitmapT.getWidth(), tempBitmapT.getHeight());
        Rect bottomRect = new Rect(0, 0, tempBitmapB.getWidth(), tempBitmapB.getHeight());
        Rect bottomRectT = new Rect(0, tempBitmapT.getHeight(), width, height);
        canvas.drawBitmap(tempBitmapT, topRect, topRect, null);
        canvas.drawBitmap(tempBitmapB, bottomRect, bottomRectT, null);
        return bitmap;
    }

    public static Bitmap drawText2Bitmap(String text, int imgResourceId, Context context) {
        try {
            Resources resources = context.getResources();
            Bitmap bitmap = BitmapFactory.decodeResource(resources, imgResourceId);
            Bitmap.Config bitmapConfig = bitmap.getConfig();
            if (bitmapConfig == null) {
                bitmapConfig = Bitmap.Config.ARGB_8888;
            }
            bitmap = bitmap.copy(bitmapConfig, true);
            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(context.getResources().getColor(R.color.colorPrimary));
            paint.setShadowLayer(1f, 0f, 1f, Color.DKGRAY);
            paint.setTextSize(DisplayUtil.dp2px(context, 20));
            Rect bounds = new Rect();
            paint.getTextBounds(text, 0, text.length(), bounds);
            canvas.drawText(text, bitmap.getWidth() - bounds.width() - DisplayUtil.dp2px(context, 20), (bounds.height()) + DisplayUtil.dp2px(context, 7), paint);
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }
}