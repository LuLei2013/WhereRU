package com.whereru.greengrass.goforit.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.whereru.greengrass.goforit.R;
import com.whereru.greengrass.goforit.adapter.MainFragmentAdapter;
import com.whereru.greengrass.goforit.ui.BaseActivity;
import com.whereru.greengrass.goforit.ui.BaseFragment;


public class MainActivity extends BaseActivity {
    private View mMainControlMessage;
    private View mMainControlMap;
    private View mMainControlRelationShip;
    private ViewPager mFragmentViewPager;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.layout_main);
        intView();
        addListeners();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void addListeners() {
        mMainControlMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentViewPager.setCurrentItem(MainFragmentAdapter.FRAGMENT_POSITION_MESSAGE);
            }
        });
        mMainControlMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentViewPager.setCurrentItem(MainFragmentAdapter.FRAGMENT_POSITION_MAP);
            }
        });
        mMainControlRelationShip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentViewPager.setCurrentItem(MainFragmentAdapter.FRAGMENT_POSITION_RELATIONSHIP);
            }
        });
    }

    private void intView() {
        mFragmentViewPager = (ViewPager) findViewById(R.id.main_content_content_viewpager);
        mMainControlMessage = findViewById(R.id.main_content_control_message);
        mMainControlMap = findViewById(R.id.main_content_control_map);
        mMainControlRelationShip = findViewById(R.id.main_content_control_relationship);
    }
}


