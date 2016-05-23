package com.whereru.greengrass.goforit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.whereru.greengrass.goforit.R;
import com.whereru.greengrass.goforit.ui.BaseActivity;

/**
 * 当进入商铺的范围后,收到push消息时,展示商铺消息的
 * Created by lulei on 16/5/22.
 */
public class BusinessComingActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.layout_business_coiming_content);
        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {
    }

    //屏蔽掉用户按backspace 导航键
    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
}
