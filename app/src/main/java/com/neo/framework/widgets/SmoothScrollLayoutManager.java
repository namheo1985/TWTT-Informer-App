package com.neo.framework.widgets;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class SmoothScrollLayoutManager extends LinearLayoutManager {
    private float mAutoScrollMillisPerDP;
    private float mManualScrollSpeedRation;
    private RecyclerView mRecyclerView;
    private LinearSmoothScroller mScroller;
    private Context mContext;
    public SmoothScrollLayoutManager(Context context, RecyclerView recyclerView) {
        super(context, VERTICAL, false);
        mContext = context.getApplicationContext();
        mRecyclerView = recyclerView;
        setAutoScrollMillisPerDP(2f);
        setManualScrollSpeedRation(1f);
    }

    public SmoothScrollLayoutManager(Context context, RecyclerView recyclerView, float autoScrollMillisPerDP, float manualScrollSlowRation, boolean isVerticalScrolling) {
        this(context, recyclerView);
        this.setOrientation(isVerticalScrolling ? VERTICAL : HORIZONTAL);

        setAutoScrollMillisPerDP(autoScrollMillisPerDP);
        setManualScrollSpeedRation(manualScrollSlowRation);
    }

    public void setAutoScrollMillisPerDP(float value) {
        mAutoScrollMillisPerDP = value > 0 ? value : 2f;

        mScroller = new LinearSmoothScroller(mContext) {
            @Override
            public PointF computeScrollVectorForPosition(int targetPosition) {
                return SmoothScrollLayoutManager.this.computeScrollVectorForPosition(targetPosition);
            }
            // This code will request X milliseconds for every Y DP units.
            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return mAutoScrollMillisPerDP / TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, displayMetrics);
            }
        };
    }

    public void setManualScrollSpeedRation(float value) {
        mManualScrollSpeedRation = value < 0 ? 1 : value;
    }

    @Override
    public int scrollVerticallyBy(int delta, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (mRecyclerView.getScrollState() == RecyclerView.SCROLL_STATE_DRAGGING)
            delta = (int) (delta > 0 ? Math.max(delta * mManualScrollSpeedRation, 1) : Math.min(delta * mManualScrollSpeedRation, -1));

        delta = super.scrollVerticallyBy(delta, recycler, state);

        if (mRecyclerView.getScrollState() == RecyclerView.SCROLL_STATE_DRAGGING)
            delta = (int) (delta > 0 ? Math.max(delta / mManualScrollSpeedRation, 1) : Math.min(delta / mManualScrollSpeedRation, -1));
        return delta;
    }
    OverScrollListener overScrollListener;
    public interface OverScrollListener {
        void overScrollDown();
        void overScrollUp();
    }
    public void setOverScrollListener(OverScrollListener listener) {
        overScrollListener = listener;
    }
    @Override
    public int scrollHorizontallyBy(int delta, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (mRecyclerView.getScrollState() == RecyclerView.SCROLL_STATE_DRAGGING)
            delta = (int) (delta > 0 ? Math.max(delta * mManualScrollSpeedRation, 1) : Math.min(delta * mManualScrollSpeedRation, -1));

        delta = super.scrollHorizontallyBy(delta, recycler, state);

        if (mRecyclerView.getScrollState() == RecyclerView.SCROLL_STATE_DRAGGING)
            delta = (int) (delta > 0 ? Math.max(delta / mManualScrollSpeedRation, 1) : Math.min(delta / mManualScrollSpeedRation, -1));

        return delta;
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, final int position) {
        mScroller.setTargetPosition(position);
        startSmoothScroll(mScroller);
    }
}
