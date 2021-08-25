package com.anningtex.largerimage.weight;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

/**
 * @author Song
 */
public class ToastUtils {
    private static Toast toast;
    @SuppressLint("StaticFieldLeak")
    private static View view;

    private ToastUtils() {
    }

    @SuppressLint("ShowToast")
    private static void getToast(Context context) {
        if (toast == null) {
            toast = new Toast(context);
        }
        if (view == null) {
            view = Toast.makeText(context, "", Toast.LENGTH_SHORT).getView();
        }
        toast.setView(view);
    }

    public static void showShortToast(Context context, CharSequence msg) {
        showToast(context.getApplicationContext(), msg);
    }

    public static void showShortToast(Context context, int resId) {
        showToast(context.getApplicationContext(), resId, Toast.LENGTH_SHORT);
    }

    public static void showLongToast(Context context, CharSequence msg) {
        showToast(context.getApplicationContext(), msg);
    }

    public static void showLongToast(Context context, int resId) {
        showToast(context.getApplicationContext(), resId, Toast.LENGTH_LONG);
    }

    private static void showToast(Context context, CharSequence msg) {
        try {
            getToast(context);
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 120);
            toast.show();
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
    }

    private static void showToast(Context context, int resId, int duration) {
        try {
            if (resId == 0) {
                return;
            }
            getToast(context);
            toast.setText(resId);
            toast.setDuration(duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
    }

    public static void cancelToast() {
        if (toast != null) {
            toast.cancel();
        }
    }
}