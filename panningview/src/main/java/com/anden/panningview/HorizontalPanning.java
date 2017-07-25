package com.anden.panningview;

import android.graphics.RectF;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by ignacio on 20/12/16.
 */

public class HorizontalPanning extends Panning {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({RIGHT_TO_LEFT, LEFT_TO_RIGHT})
    public @interface Way {}
    public static final int RIGHT_TO_LEFT = 0;
    public static final int LEFT_TO_RIGHT = 1;

    private int way;

    private float startX;
    private float endX;

    public HorizontalPanning(@Way int way){
        this.way = way;
    }

    @Override
    public void setSize(RectF drawableRect, RectF viewRect) {
        super.setSize(drawableRect, viewRect);
        this.startX = calculateStartX();
        this.endX = calculateEndX();
    }

    private float calculateStartX(){

        RectF displayRect = getDisplaySize();
        RectF viewRect = getViewSize();

        if(way == RIGHT_TO_LEFT)
            return displayRect.left + (viewRect.width() * getStartXOffset());

        return (viewRect.width() - displayRect.right)  - (viewRect.width() * getStartXOffset());
    }

    private float calculateEndX(){

        RectF displayRect = getDisplaySize();
        RectF viewRect = getViewSize();

        if(way == RIGHT_TO_LEFT)
            return viewRect.width() - (displayRect.right + (viewRect.width() * getEndXOffset()));

        return (viewRect.width() * getEndXOffset()) + (displayRect.right - viewRect.width());
    }

    @Override
    public float getX(float dt) {
        return startX + getInterpolator().getInterpolation(dt) * endX;
    }

    @Override
    public float getY(float dt) {
        return 0;
    }

}
