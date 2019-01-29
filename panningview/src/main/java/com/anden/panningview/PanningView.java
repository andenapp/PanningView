package com.anden.panningview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * View can display a {@link Drawable} with a {@link Panning} animation
 */
public class PanningView extends View {

    static final String TAG = "PanningView";

    public interface PanningListener{
        void onPanningStart(PanningView panningView);
        void onPanningEnd(PanningView panningView);
    }

    private static final long TRANSITION_DURATION = 3000;

    private static final int PAUSE_STATE = 0;
    private static final int START_STATE = 1;

    private int state = PAUSE_STATE;

    private Matrix matrix = new Matrix();

    private RectF displayRect = new RectF();

    private long duration = TRANSITION_DURATION;

    private boolean autoStart;

    private long lastFrameTime;

    private long elapsedTime;

    private long pausedTime;

    private float scaleFactor;

    private Drawable drawable;

    private Panning PANNING = new HorizontalPanning(HorizontalPanning.LEFT_TO_RIGHT);

    private Panning panning = PANNING;

    private PanningListener panningListener;

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
            autoStart = a.getBoolean(R.styleable.PanningView_autoStart, false);
            drawable = a.getDrawable(R.styleable.PanningView_drawable);
        } catch (Resources.NotFoundException exception) {
            Log.e(TAG, "Drawable resource not found", exception);
        } finally {
            a.recycle();
        }

        init();
    }

    private void init(){
        setDrawable(drawable);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int desiredWidth = 0;
        int desiredHeight = 0;

        if(drawable != null){
            desiredWidth = drawable.getIntrinsicWidth();
            desiredHeight = drawable.getIntrinsicHeight();
        }

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(desiredWidth, widthSize);
        } else {
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(desiredHeight, heightSize);
        } else {
            height = desiredHeight;
        }

        setMeasuredDimension(width, height);

        calculateValues();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(autoStart)
            start();
    }

    private void calculateValues(){

        if (drawable == null)
            return;

        scaleFactor = (float)getMeasuredHeight() / (float) drawable.getIntrinsicHeight();
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        matrix.reset();
        matrix.postScale(scaleFactor, scaleFactor);

        refreshDisplayRect();
    }

    private void refreshDisplayRect() {

        if (drawable == null)
            return;

        displayRect.set(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        matrix.mapRect(displayRect);

        panning.setSize(displayRect, new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight()));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (drawable == null)
            return;

        if(state == START_STATE)
            elapsedTime += System.currentTimeMillis() - lastFrameTime;

        float f = Math.max(0, Math.min(1, elapsedTime / (float) duration));

        matrix.reset();
        matrix.postScale(scaleFactor, scaleFactor);
        matrix.postTranslate(panning.getX(f), panning.getY(f));

        canvas.concat(matrix);
        drawable.draw(canvas);

        if(state == PAUSE_STATE) {
            return;
        }

        if(elapsedTime < duration) {
            lastFrameTime = System.currentTimeMillis();
            ViewCompat.postInvalidateOnAnimation(this);
        }else{
            if(panningListener != null){
                panningListener.onPanningEnd(this);
            }
        }
    }

    /**
     * Sets the duration of the animation
     */
    public void setDuration(long duration){
        this.duration = duration;
    }

    /**
     * Sets the {@link Panning} implementation of the animation
     */
    public void setPanning(Panning panning){
        this.panning = panning;
        requestLayout();
    }

    public Panning getPanning(){
        return this.panning;
    }

    public void setDrawable(int resourceId){
        setDrawable(ContextCompat.getDrawable(getContext(), resourceId));
    }

    private void setDrawable(Drawable drawable){
        this.drawable = drawable;
    }

    /**
     * Start the animation
     */
    public void start(){
        state = START_STATE;
        lastFrameTime = System.currentTimeMillis();
        elapsedTime = 0;
        invalidate();
        if(panningListener != null)
            panningListener.onPanningStart(this);
    }

    /**
     * Resume the current animation previous call {@link PanningView#pause()}
     */
    public void resume(){
        state = START_STATE;
        lastFrameTime += System.currentTimeMillis() - pausedTime;
        invalidate();
    }

    /**
     * Pause the current animation
     */
    public void pause(){
        state = PAUSE_STATE;
        pausedTime = System.currentTimeMillis();
        invalidate();
    }

    /**
     * Returns if the animation isPaused, only returne true when {@link PanningView#pause()} was called
     **/
    public boolean isPaused(){
        return state == PAUSE_STATE;
    }

    public void setPanningListener(PanningListener panningListener) {
        this.panningListener = panningListener;
    }
}
