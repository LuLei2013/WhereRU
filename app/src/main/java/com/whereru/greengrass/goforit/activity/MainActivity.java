package com.whereru.greengrass.goforit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.whereru.greengrass.goforit.Constants;
import com.whereru.greengrass.goforit.R;
import com.whereru.greengrass.goforit.adapter.MainFragmentAdapter;
import com.whereru.greengrass.goforit.commonmodule.utils.Log;
import com.whereru.greengrass.goforit.ui.BaseActivity;
import com.whereru.greengrass.goforit.ui.BaseViewPager;


public class MainActivity extends BaseActivity {
    private View mMainControlMessage;
    private View mMainControlMap;
    private View mMainControlRelationShip;
    private BaseViewPager mFragmentViewPager;
    private MainFragmentAdapter mMainFragmentAdapter;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        intView();
        addListeners();
        handleInent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.i("@MainActivity#onNewIntent, intent :" + intent.toString());
        handleInent(intent);
    }

    private void addListeners() {
        mMainControlMessage.setOnClickListener(new View.OnClickListener() {
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
        mFragmentViewPager = (BaseViewPager) findViewById(R.id.main_content_content_viewpager);
        mMainControlMessage = findViewById(R.id.main_content_control_message);
        mMainControlMap = findViewById(R.id.main_content_control_map);
        mMainControlRelationShip = findViewById(R.id.main_content_control_relationship);
        mMainFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager());
        mFragmentViewPager.setAdapter(mMainFragmentAdapter);
        mFragmentViewPager.setCurrentItem(MainFragmentAdapter.FRAGMENT_POSITION_MAP);
        mFragmentViewPager.setHorizonalSrocll(false);
    }


    /**
     * 处理通过MainActivity,中转打开目标Activity的行为
     */
    private void handleInent(Intent intent) {
        if (intent == null) {
            Log.i("@MainActivity#handleInent, intent ==null");
            return;
        }
        Intent targetIntent = intent.getParcelableExtra(Constants.TARGET_ACTIVITY);
        if (targetIntent == null) {
            Log.i("@MainActivity#handleInent, targetIntent ==null");
            return;
        }
        Log.i("@MainActivity#handleInent, targetIntent:" + targetIntent.toString());
        startActivity(targetIntent);
    }
}


