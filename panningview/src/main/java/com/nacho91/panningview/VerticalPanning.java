package com.nacho91.panningview;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by ignacio on 20/12/16.
 */

public class VerticalPanning extends Panning {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TOP_TO_BOTTOM, BOTTOM_TO_TOP})
    public @interface Way {}
    public static final int TOP_TO_BOTTOM = 0;
    public static final int BOTTOM_TO_TOP = 1;

    private int way;

    private float startY;
    private float endY;

    public VerticalPanning(@Way int way){
        this.way = way;
        this.startY = calculateStartY();
        this.endY = calculateEndY();
    }

    private float calculateStartY(){
        if(way == BOTTOM_TO_TOP)
            return viewRect.top + (viewRect.height() * getStartOffset());

        return displayRect.right - viewRect.height() - (viewRect.height() * getStartOffset());
    }

    private float calculateEndY(){
        if(way == BOTTOM_TO_TOP)
            return displayRect.bottom - viewRect.height() - (viewRect.height() * getEndOffset());

        return displayRect.bottom - viewRect.height() + (viewRect.height() * getEndOffset());
    }

    @Override
    public float getX(float dt) {
        return 0;
    }

    @Override
    public float getY(float dt) {
       return startY + getInterpolator().getInterpolation(dt) * endY;
    }

    private float getStartOffset(){
        return yStartOffset;
    }

    private float getEndOffset(){
        return yStartOffset + yEndOffset;
    }
}
