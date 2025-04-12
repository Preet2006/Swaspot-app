package com.example.swapspot;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * A custom drawable wrapper that enlarges an existing drawable
 */
public class LargeIconDrawable extends Drawable {
    private final Drawable mDrawable;
    private final float mScaleFactor;

    public LargeIconDrawable(Drawable drawable, float scaleFactor) {
        this.mDrawable = drawable;
        this.mScaleFactor = scaleFactor;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        if (mDrawable == null) return;
        
        int saveCount = canvas.save();
        
        // Scale from the center
        float centerX = getBounds().width() / 2f;
        float centerY = getBounds().height() / 2f;
        
        canvas.scale(mScaleFactor, mScaleFactor, centerX, centerY);
        
        // Draw the original drawable
        mDrawable.setBounds(getBounds());
        mDrawable.draw(canvas);
        
        canvas.restoreToCount(saveCount);
    }

    @Override
    public void setAlpha(int alpha) {
        if (mDrawable != null) {
            mDrawable.setAlpha(alpha);
        }
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        if (mDrawable != null) {
            mDrawable.setColorFilter(colorFilter);
        }
    }

    @Override
    public int getOpacity() {
        return mDrawable != null ? mDrawable.getOpacity() : PixelFormat.TRANSPARENT;
    }
} 