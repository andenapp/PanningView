package com.nacho91.panningview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;

/**
 * Created by ignacio on 06/10/16.
 */

public class PanningView extends ImageView {

    private static final long FRAME_DELAY = 1000 / 60;

    private static final long TRANSITION_DURATION = 3000;

    private Matrix matrix = new Matrix();

    private RectF mDisplayRect = new RectF();

    private long duration = TRANSITION_DURATION;

    private long elapsedTime;

    private long lastFrameTime;

    private float scaleFactor;

    private long endTime;

    private Drawable drawable;

    private Interpolator INTERPOLATOR = new DecelerateInterpolator();

    private Panning panning = new HorizontalPanning(HorizontalPanning.LEFT_TO_RIGHT);

    public PanningView(Context context) {
        this(context, null);
    }

    public PanningView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PanningView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PanningView, 0, 0);

        try {
            duration = a.getInteger(R.styleable.PanningView_duration, (int) TRANSITION_DURATION);
            drawable = a.getDrawable(R.styleable.PanningView_drawable);
        }finally {
            a.recycle();
        }

        init();
    }

    private void init(){

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        scaleFactor = (float)getMeasuredHeight() / (float) getDrawable().getIntrinsicHeight();
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        matrix.reset();
        matrix.postScale(scaleFactor, scaleFactor);

        refreshDisplayRect();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        start();
    }

    private void refreshDisplayRect() {
        mDisplayRect.set(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        matrix.mapRect(mDisplayRect);

        panning.setXOffset(1f);
        panning.setSize(mDisplayRect, new RectF(0, 0, getWidth(), getHeight()));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        long t = System.currentTimeMillis() - lastFrameTime;

        Log.v("TAG","" + t);

        float f = INTERPOLATOR.getInterpolation(Math.max(0, Math.min(1, t / (float) duration)));

        matrix.reset();
        matrix.postScale(scaleFactor, scaleFactor);
        matrix.postTranslate(panning.getX(f), panning.getY(f));
//
        canvas.concat(matrix);
        drawable.draw(canvas);

        //lastFrameTime = AnimationUtils.currentAnimationTimeMillis();

        if(t < duration) {
            //setImageMatrix(matrix);
            Log.v("TAG","invalidate");
           ViewCompat.postInvalidateOnAnimation(this);
        }else{
//            start();
        }

    }

    private void setDrawable(int resourceId){
        setDrawable(ContextCompat.getDrawable(getContext(), resourceId));
    }

    private void setDrawable(Drawable drawable){
        this.drawable = drawable;
    }

    public void start(){
        lastFrameTime = System.currentTimeMillis();
        elapsedTime = 0;
        endTime = lastFrameTime + duration;
//        invalidate();
    }
}
