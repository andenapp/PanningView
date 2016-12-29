package com.nacho91.panningview;

import android.graphics.RectF;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

/**
 * Created by ignacio on 20/12/16.
 */
public abstract class Panning {

    protected RectF displayRect;
    protected RectF viewRect;

    protected float xStartOffset = 0f;
    protected float xEndOffset = 0f;

    protected float yStartOffset = 0f;
    protected float yEndOffset = 0f;

    private Interpolator INTERPOLATOR = new LinearInterpolator();

    private Interpolator interpolator = INTERPOLATOR;

    public void setSize(RectF drawableRect, RectF viewRect){
        this.displayRect = drawableRect;
        this.viewRect = viewRect;
    }

    public abstract float getX(float dt);

    public abstract float getY(float dt);

    public void setStartXOffset(float offset){
        this.xStartOffset = offset;
    }

    public void setEndXOffset(float offset){
        this.xEndOffset = offset;
    }

    public void setStartYOffset(float offset){
        this.yStartOffset = offset;
    }

    public void setEndYOffset(float offset){
        this.yEndOffset = offset;
    }

    public void setInterpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
    }

    public Interpolator getInterpolator() {
        return interpolator;
    }
}
