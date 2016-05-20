package com.whereru.greengrass.goforit.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;

/**
 * Created by lulei on 16/5/20.
 */
public class BaseViewPager extends ViewPager {
    /**
     * 是否拦截,左右滑动事件
     */
    private boolean mIsHorizonalSrocll;

    public BaseViewPager(Context context) {
        super(context);
    }

    public void setHorizonalSrocll(boolean isHorizonalSrocll) {
        mIsHorizonalSrocll = isHorizonalSrocll;
    }

    public boolean getHorizontalScroll() {
        return mIsHorizonalSrocll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (!mIsHorizonalSrocll) {
            return true;
        }
        return super.onInterceptTouchEvent(event);
    }
}
