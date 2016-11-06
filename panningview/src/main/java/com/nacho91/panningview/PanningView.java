package com.nacho91.panningview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by ignacio on 06/10/16.
 */

public class PanningView extends ImageView {

    private static final long FRAME_DELAY = 1000 / 60;

    private static final long TRANSITION_DURATION = 5000;

    private Matrix matrix = new Matrix();

    private RectF mDisplayRect = new RectF();

    private long duration = TRANSITION_DURATION;

    private long elapsedTime;

    private long lastFrameTime;

    private float scaleFactor;

    private long endTime;

    public PanningView(Context context) {
        this(context, null);
    }

    public PanningView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PanningView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init(){

        super.setScaleType(ScaleType.MATRIX);

    }

    @Override
    public void setScaleType(ScaleType scaleType) {
        throw new UnsupportedOperationException("only MATRIX scaleType is supported");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        scaleFactor = (float)getHeight() / (float) getDrawable().getIntrinsicHeight();
        matrix.postScale(scaleFactor, scaleFactor);

        refreshDisplayRect();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        start();
    }

    private void refreshDisplayRect() {
        mDisplayRect.set(0, 0, getDrawable().getIntrinsicWidth(), getDrawable().getIntrinsicHeight());
        matrix.mapRect(mDisplayRect);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if(lastFrameTime < endTime) {
            elapsedTime += System.currentTimeMillis() - lastFrameTime;
            float f = Math.min(elapsedTime / (float) duration, 1);
            float value = mDisplayRect.left + f * (mDisplayRect.left - (mDisplayRect.right - getWidth()) - mDisplayRect.left);


            Log.v("PanningView", mDisplayRect.toString());
            Log.v("PanningView", String.valueOf(value));

            matrix.reset();
            matrix.postScale(scaleFactor, scaleFactor);
            matrix.postTranslate(-value, 0);

            setImageMatrix(matrix);

            refreshDisplayRect();

            lastFrameTime = System.currentTimeMillis();
            postInvalidateDelayed(FRAME_DELAY);
        }else{
            start();
        }

        super.onDraw(canvas);
    }

    public void start(){
        lastFrameTime = System.currentTimeMillis();
        elapsedTime = 0;
        endTime = lastFrameTime + duration;
        invalidate();
    }
}
