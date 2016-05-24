package com.whereru.greengrass.goforit.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.whereru.greengrass.goforit.commonmodule.EventManager;


/**
 * Created by didi on 16/5/17.
 */
public class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    public void register() {
        EventManager.getInstance().register(this);
    }

    public void unregister() {
        EventManager.getInstance().unregister(this);
    }

}
