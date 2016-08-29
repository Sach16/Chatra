package com.pissay.chatra.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.VideoView;

/**
 * Created by S.K. Pissay on 19/7/16.
 */

public class FullVideoView extends VideoView {
    private int mForceHeight = 0;
    private int mForceWidth = 0;

    public FullVideoView(Context context) {
        super(context);
    }

    public FullVideoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FullVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setDimensions(int w, int h) {
        this.mForceHeight = h;
        this.mForceWidth = w;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i("ON_MEASURE_TAG", "onMeasure");
        setMeasuredDimension(mForceWidth, mForceHeight);
    }
}