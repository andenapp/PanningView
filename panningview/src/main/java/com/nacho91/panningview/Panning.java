package com.nacho91.panningview;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

/**
 *  Base class to implement a scrolling animation
 *  that can be set in @{@link PanningView#setPanning(Panning)}
 */
public abstract class Panning {

    private RectF displayRect;
    private RectF viewRect;

    private float xStartOffset = 0f;
    private float xEndOffset = 0f;

    private float yStartOffset = 0f;
    private float yEndOffset = 0f;

    private Interpolator INTERPOLATOR = new LinearInterpolator();

    private Interpolator interpolator = INTERPOLATOR;

    /**
     * Sets the @{@link Drawable} and  @{@link PanningView} size,
     * this method calls automatically after @{@link PanningView#onMeasure(int, int)}}
     * @param drawableRect contains the drawable size info (0, 0, {@link Drawable#getIntrinsicWidth()}, {@link Drawable#getIntrinsicHeight()})
     * @param viewRect contains the view size info (0, 0, {@link View#getMeasuredWidth()}, {@link View#getMeasuredHeight()})
     */
    public void setSize(RectF drawableRect, RectF viewRect){
        this.displayRect = drawableRect;
        this.viewRect = viewRect;
    }

    /**
     * Returns a x value in elapsed fraction of an animation
     * @param dt elapsed fraction (elapsedTime / duration)
     */
    public abstract float getX(float dt);

    /**
     * Returns a y value in elapsed fraction of an animation
     * @param dt elapsed fraction (elapsedTime / duration)
     */
    public abstract float getY(float dt);

    public RectF getDisplaySize(){
        return displayRect;
    }

    public RectF getViewSize(){
        return viewRect;
    }

    public void setStartXOffset(float offset){
        this.xStartOffset = offset;
    }

    public float getStartXOffset(){
        return this.xStartOffset;
    }

    public void setEndXOffset(float offset){
        this.xEndOffset = offset;
    }

    public float getEndXOffset(){
        return this.xStartOffset + this.xEndOffset;
    }

    public void setStartYOffset(float offset){
        this.yStartOffset = offset;
    }

    public float getStartYOffset(){
        return this.yStartOffset;
    }

    public void setEndYOffset(float offset){
        this.yEndOffset = offset;
    }

    public float getEndYOffset(){
        return this.yStartOffset + this.yEndOffset;
    }

    public void setInterpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
    }

    public Interpolator getInterpolator() {
        return interpolator;
    }
}
