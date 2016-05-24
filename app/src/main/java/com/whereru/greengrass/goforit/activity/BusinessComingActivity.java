package com.whereru.greengrass.goforit.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.whereru.greengrass.goforit.Constants;
import com.whereru.greengrass.goforit.R;
import com.whereru.greengrass.goforit.commonmodule.eventmessage.PushMessage;
import com.whereru.greengrass.goforit.commonmodule.utils.Log;
import com.whereru.greengrass.goforit.ui.BaseActivity;

/**
 * 当进入商铺的范围后,收到push消息时,展示商铺消息的
 * Created by lulei on 16/5/22.
 */
public class BusinessComingActivity extends BaseActivity {

    private ImageView mBusinessAvatat;
    private TextView mBusinessName;
    private TextView mBusinessCreateUser;
    private TextView mBusinessAbstract;
    private View mDismiss;
    private View mGoforit;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.layout_business_coiming_content);
        intViews();
        addListeners();
        handleIntent(getIntent());
    }


    //屏蔽掉用户按backspace 导航键
    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }


    private void intViews() {
        mBusinessAvatat = (ImageView) findViewById(R.id.business_coming_content_title_business_avatar);
        mBusinessName = (TextView) findViewById(R.id.business_coming_content_title_business_name);
        mBusinessCreateUser = (TextView) findViewById(R.id.business_coming_content_title_create_user);
        mBusinessAbstract = (TextView) findViewById(R.id.business_coming_content_detail_abstract);
        mDismiss = findViewById(R.id.business_coming_content_control_dismiss);
        mGoforit = findViewById(R.id.business_coming_content_control_goforit);
    }

    private void addListeners() {
        mDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mGoforit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BusinessComingActivity.this, "什么也么做呢!!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void handleIntent(Intent intent) {
        if (intent == null) {
            Log.i("@BusinessComingActivity#handleInent, intent ==null");
            return;
        }
        PushMessage pushMsg = null;
        Uri uri = null;
        try {
            uri = intent.getData();
            pushMsg = (PushMessage) intent.getSerializableExtra(Constants.TARGET_ACTIVITY_DATA_KEY);
        } catch (Exception e) {
        }
        if (pushMsg == null || uri == null) {
            Log.i("@BusinessComingActivity#handleInent, pushMsg == null || uri == null");
            return;
        }
        if (!(Constants.TARGET_ACTIVITY_SCHEM + Constants.SCHEMA_AND_HOST_SEPORATER + getClass().getName()).equals(uri.toString())) {
            Log.i("@BusinessComingActivity#handleInent, target activity not match");
            return;
        }
        mBusinessName.setText(pushMsg.getData().getName());
        mBusinessCreateUser.setText(pushMsg.getData().getUserName());
        mBusinessAbstract.setText(pushMsg.getData().getAbstract());

    }
}
