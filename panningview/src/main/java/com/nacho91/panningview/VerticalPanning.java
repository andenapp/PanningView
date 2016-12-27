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

    public VerticalPanning(@Way int way){
        this.way = way;
    }

    @Override
    public float getX(float dt) {
        return 0;
    }

    @Override
    public float getY(float dt) {
       return getStartY() + getInterpolator().getInterpolation(dt) * getEndY();
    }

    private float getStartY(){
        if(way == BOTTOM_TO_TOP)
            return viewRect.top + (viewRect.height() * getStartOffset());

        return displayRect.right - viewRect.height() - (viewRect.height() * getStartOffset());
    }

    private float getEndY(){
        if(way == BOTTOM_TO_TOP)
            return displayRect.bottom - viewRect.height() - (viewRect.height() * getEndOffset());

        return displayRect.bottom - viewRect.height() + (viewRect.height() * getEndOffset());
    }

    private float getStartOffset(){
        return yStartOffset;
    }

    private float getEndOffset(){
        return yStartOffset + yEndOffset;
    }
}
