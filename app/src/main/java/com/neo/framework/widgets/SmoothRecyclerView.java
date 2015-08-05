package com.neo.framework.widgets;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class SmoothRecyclerView extends RecyclerView {
    private SmoothScrollLayoutManager mLayoutManager;
    private float mFlingSpeedRatio;

    public void setOverScrollListener(SmoothScrollLayoutManager.OverScrollListener listener) {
        overScrollListener = listener;
        if (mLayoutManager != null) mLayoutManager.setOverScrollListener(overScrollListener);
    }

    SmoothScrollLayoutManager.OverScrollListener overScrollListener;

    public SmoothRecyclerView(Context context, float autoScrollMillisPerDP, float manualScrollSlowRation, boolean isVerticalScrolling) {
        super(context);
        mLayoutManager = new SmoothScrollLayoutManager(context, this, autoScrollMillisPerDP, manualScrollSlowRation, isVerticalScrolling);
        this.setLayoutManager(mLayoutManager);
    }

    public SmoothRecyclerView(Context context) {
        super(context);
        setupDefault(context);
    }

    public SmoothRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDefault(context);
    }

    public SmoothRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setupDefault(context);
    }

    public void setAutoScrollMillisPerDP(float value) {
        mLayoutManager.setAutoScrollMillisPerDP(value);
    }

    public void setManualScrollSpeedRation(float value) {
        mLayoutManager.setManualScrollSpeedRation(value);
    }

    public void setOrientation(boolean isVertical) {
        mLayoutManager.setOrientation(isVertical ? VERTICAL : HORIZONTAL);
    }

    /**
     * between 0 for no fling, and 1 for normal fling, or more for faster fling.
     */
    public void setFlingSpeedRatio(float value) {
        mFlingSpeedRatio = value < 0 ? 1 : value;
    }

    private void setupDefault(Context context) {
        mLayoutManager = new SmoothScrollLayoutManager(context, this);
        this.setLayoutManager(mLayoutManager);
        setManualScrollSpeedRation(1.6f);
        setFlingSpeedRatio(0.8f);
        getItemAnimator().setSupportsChangeAnimations(false);
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        if (mLayoutManager.getOrientation() == VERTICAL)
            velocityY *= mFlingSpeedRatio;
        else
            velocityX *= mFlingSpeedRatio;
        return super.fling(velocityX, velocityY);
    }
}
