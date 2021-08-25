package com.anningtex.photoview;

import android.graphics.RectF;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * @author Song
 */
public class DefaultOnDoubleTapListener implements GestureDetector.OnDoubleTapListener {

    private PhotoViewAttache photoViewAttache;

    /**
     * Default constructor
     *
     * @param photoViewAttache PhotoViewAttacher to bind to
     */
    public DefaultOnDoubleTapListener(PhotoViewAttache photoViewAttache) {
        setPhotoViewAttache(photoViewAttache);
    }

    /**
     * Allows to change PhotoViewAttacher within range of single instance
     *
     * @param newPhotoViewAttache PhotoViewAttacher to bind to
     */
    public void setPhotoViewAttache(PhotoViewAttache newPhotoViewAttache) {
        this.photoViewAttache = newPhotoViewAttache;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        if (this.photoViewAttache == null) {
            return false;
        }

        ImageView imageView = photoViewAttache.getImageView();

        if (null != photoViewAttache.getOnPhotoTapListener()) {
            final RectF displayRect = photoViewAttache.getDisplayRect();

            if (null != displayRect) {
                final float x = e.getX(), y = e.getY();

                // Check to see if the user tapped on the photo
                if (displayRect.contains(x, y)) {

                    float xResult = (x - displayRect.left)
                            / displayRect.width();
                    float yResult = (y - displayRect.top)
                            / displayRect.height();

                    photoViewAttache.getOnPhotoTapListener().onPhotoTap(imageView, xResult, yResult);
                    return true;
                }
            }
        }
        if (null != photoViewAttache.getOnViewTapListener()) {
            photoViewAttache.getOnViewTapListener().onViewTap(imageView, e.getX(), e.getY());
        }

        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent ev) {
        if (photoViewAttache == null) {
            return false;
        }

        try {
            float scale = photoViewAttache.getScale();
            float x = ev.getX();
            float y = ev.getY();

            if (scale < photoViewAttache.getMediumScale()) {
                photoViewAttache.setScale(photoViewAttache.getMediumScale(), x, y, true);
            } else if (scale >= photoViewAttache.getMediumScale() && scale < photoViewAttache.getMaximumScale()) {
                photoViewAttache.setScale(photoViewAttache.getMaximumScale(), x, y, true);
            } else {
                photoViewAttache.setScale(photoViewAttache.getMinimumScale(), x, y, true);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // Can sometimes happen when getX() and getY() is called
        }

        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        // Wait for the confirmed onDoubleTap() instead
        return false;
    }
}
