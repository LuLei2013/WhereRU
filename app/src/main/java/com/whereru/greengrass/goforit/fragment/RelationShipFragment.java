package com.whereru.greengrass.goforit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whereru.greengrass.goforit.R;
import com.whereru.greengrass.goforit.adapter.RelationShipFragmentAdapter;
import com.whereru.greengrass.goforit.commonmodule.eventmessage.PushMessage;
import com.whereru.greengrass.goforit.swipelistview.BaseSwipeListViewListener;
import com.whereru.greengrass.goforit.swipelistview.SwipeListView;
import com.whereru.greengrass.goforit.ui.BaseFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lulei on 16/5/18.
 */
public class RelationShipFragment extends BaseFragment {
    private SwipeListView mSwipeListView;
    private RelationShipFragmentAdapter mRelationShipFragmentAdapter;
    private List<PushMessage> mRelationShipItemList;

    public RelationShipFragment() {
        // 为Fragment 注册EventBus, 使得它可以接收到Push消息回调
//        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View messageFragmentView = inflater.inflate(R.layout.layout_relationship_fragment_content, container, false);
        mSwipeListView = (SwipeListView) messageFragmentView.findViewById(R.id.relationship_fragment_list);
        /**
         * 从本地数据库中预取,以后每次有推送时,都在这里更新mMessageItemList
         */
        //测试数据
        test();
        mRelationShipFragmentAdapter = new RelationShipFragmentAdapter(getActivity().getApplicationContext(), mSwipeListView, mRelationShipItemList);
        mSwipeListView.setAdapter(mRelationShipFragmentAdapter);
        mSwipeListView.setSwipeListViewListener(new BaseSwipeListViewListener() {
            // 这里可以重写很多方法
            @Override
            public void onListChanged() {
                super.onListChanged();
            }

            @Override
            public void onClickFrontView(int position) {

                super.onClickFrontView(position);
            }

        });

        return messageFragmentView;
    }

    private void test() {
        mRelationShipItemList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            PushMessage pushmessage = new PushMessage();
            PushMessage.Data data = new PushMessage.Data();
            data.setName("尚东数字山谷 " + i);
            data.setAbstract("卢磊的工作地方 " + i);
            pushmessage.setData(data);
            mRelationShipItemList.add(pushmessage);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        // 为Fragment 撤销EventBus
//        EventBus.getDefault().unregister(this);
    }
}

