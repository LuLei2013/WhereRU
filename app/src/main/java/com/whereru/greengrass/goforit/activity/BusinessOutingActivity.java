package com.whereru.greengrass.goforit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;


import com.whereru.greengrass.goforit.R;
import com.whereru.greengrass.goforit.ui.BaseActivity;

/**
 * Created by didi on 16/5/24.
 */
public class BusinessOutingActivity extends BaseActivity {


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

}
