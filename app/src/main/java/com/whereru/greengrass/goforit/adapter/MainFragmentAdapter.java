package com.whereru.greengrass.goforit.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.whereru.greengrass.goforit.fragment.MapFragment;
import com.whereru.greengrass.goforit.fragment.MessageFragment;
import com.whereru.greengrass.goforit.fragment.RelationShipFragment;

/**
 * Created by lulei on 16/5/18.
 */
public class MainFragmentAdapter extends FragmentPagerAdapter {
    /**
     * 主界面 Fragment的数量
     */
    private static final int DEFAULT_FRAGMENT_COUNNTER = 3;
    public static final int FRAGMENT_POSITION_MESSAGE = 0;
    public static final int FRAGMENT_POSITION_MAP = 1;
    public static final int FRAGMENT_POSITION_RELATIONSHIP = 2;

    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position < 0) {
            position = 0;
        }
        position = position & DEFAULT_FRAGMENT_COUNNTER;
        switch (position) {
            case FRAGMENT_POSITION_MESSAGE:
                return new MessageFragment();
            case FRAGMENT_POSITION_MAP:
                return new MapFragment();
            case FRAGMENT_POSITION_RELATIONSHIP:
                return new RelationShipFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return DEFAULT_FRAGMENT_COUNNTER;
    }
}
