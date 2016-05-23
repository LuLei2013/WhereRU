package com.whereru.greengrass.goforit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.whereru.greengrass.goforit.R;
import com.whereru.greengrass.goforit.adapter.MainFragmentAdapter;
import com.whereru.greengrass.goforit.commonmodule.EventManager;
import com.whereru.greengrass.goforit.commonmodule.eventmessage.LocateMessage;
import com.whereru.greengrass.goforit.commonmodule.eventmessage.PushMessage;
import com.whereru.greengrass.goforit.commonmodule.utils.Log;
import com.whereru.greengrass.goforit.ui.BaseActivity;
import com.whereru.greengrass.goforit.ui.BaseViewPager;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


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
        regitser();
    }


    @Override
    protected void onResume() {
        super.onResume();
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
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregister();
    }


    /**
     * 注册为处理EventBus入口
     */

    private void regitser() {
        EventManager.getInstance().register(this);
    }

    /**
     * 取消处理EventBus 事件
     */
    private void unregister() {
        EventManager.getInstance().unregister(this);
    }

    /**
     * EventBus 统一入口
     *
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handlePushMessage(PushMessage message) {
        Log.e("MainActivity 收到一个Push消息" + message.toString());
        if (message == null) {
            return;
        }

    }

    /**
     * EventBus 统一入口
     *
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleLocationMessage(LocateMessage message) {
        Log.i(" MainActivity 收到一个定位消息 :" + (message == null ? "null" : message.toString()));
        if (message == null) {
            return;
        }
    }

}


