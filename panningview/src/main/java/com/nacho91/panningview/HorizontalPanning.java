package com.nacho91.panningview;

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

    public HorizontalPanning(@Way int way){
        this.way = way;
    }

    @Override
    public float getX(float dt) {

        if(way == LEFT_TO_RIGHT)
            return (displayRect.left + (viewRect.width() * offsetX)) + dt * (viewRect.width() - (displayRect.right + (viewRect.width() * 1.1f)));

        return viewRect.width() - displayRect.right + dt * (displayRect.right - viewRect.width());
    }

    @Override
    public float getY(float dt) {
        return 0;
    }
}
