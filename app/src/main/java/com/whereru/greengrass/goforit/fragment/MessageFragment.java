package com.whereru.greengrass.goforit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whereru.greengrass.goforit.R;
import com.whereru.greengrass.goforit.ui.BaseFragment;

/**
 * Created by lulei on 16/5/18.
 */
public class MessageFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View messageFragmentView = inflater.inflate(R.layout.layou_message_fragment, container, false);
        if (messageFragmentView == null) {
            return messageFragmentView;
        }
        return messageFragmentView;
    }
}
