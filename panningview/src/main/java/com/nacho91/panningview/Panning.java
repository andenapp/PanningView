package com.nacho91.panningview;

import android.graphics.RectF;

/**
 * Created by ignacio on 20/12/16.
 */

public abstract class Panning {

    protected RectF displayRect;
    protected RectF viewRect;

    protected float offsetX;
    protected float offsetY;

    public void setSize(RectF drawableRect, RectF viewRect){
        this.displayRect = drawableRect;
        this.viewRect = viewRect;
    }

    public abstract float getX(float dt);

    public abstract float getY(float dt);

    public void setXOffset(float offset){
        this.offsetX = offset;
    }

    public void setYOffset(float offset){
        this.offsetY = offset;
    }
}
