package com.whereru.greengrass.goforit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whereru.greengrass.goforit.R;
import com.whereru.greengrass.goforit.adapter.MessageFragmentAdapter;
import com.whereru.greengrass.goforit.swipelistview.BaseSwipeListViewListener;
import com.whereru.greengrass.goforit.swipelistview.SwipeListView;
import com.whereru.greengrass.goforit.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lulei on 16/5/18.
 */
public class MessageFragment extends BaseFragment {
    private SwipeListView mSwipeListView;
    private MessageFragmentAdapter mMessageFramentAdapter;
    private List<MessageItem> mMessageItemList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View messageFragmentView = inflater.inflate(R.layout.layout_message_fragment_content, container, false);
        mSwipeListView = (SwipeListView) messageFragmentView.findViewById(R.id.message_fragment_list);
        /**
         * 从本地数据库中预取,以后每次有推送时,都在这里更新mMessageItemList
         */
        //测试数据
        test();
        mMessageFramentAdapter = new MessageFragmentAdapter(getActivity().getApplicationContext(), mSwipeListView, mMessageItemList);
        mSwipeListView.setAdapter(mMessageFramentAdapter);
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

    /**
     * 消息列表对应的数据模型
     */
    public static class MessageItem {
        /**
         * 商铺头像Url
         */
        private String mBusinessAvatarUrl;
        /**
         * 商铺对应的Url
         */
        private String mBusinessUrl;
        /**
         * 商铺名称
         */
        private String mBusinessName;
        /**
         * 该商铺的最后一条留言
         */
        private String mLastMessage;

        MessageItem(String businessAvatarUrl, String businessUrl, String businessName, String lastMessage) {
            this.mBusinessAvatarUrl = businessAvatarUrl;
            this.mBusinessUrl = businessUrl;
            this.mBusinessName = businessName;
            this.mLastMessage = lastMessage;
        }

        public String getBusinessAvatarUrl() {
            return mBusinessAvatarUrl;
        }

        public String getBusinessUrl() {
            return mBusinessUrl;
        }

        public String getLastMessage() {
            return mLastMessage;
        }

        public String getBusinessName() {
            return mBusinessName;
        }

        public void setBusinessAvatarUrl(String businessAvatarUrl) {
            this.mBusinessAvatarUrl = businessAvatarUrl;
        }

        public void setBusinessUrl(String businessUrl) {
            this.mBusinessUrl = businessUrl;
        }

        public void setBusinessName(String businessName) {
            this.mBusinessName = businessName;
        }

        public void setLastMessage(String lastMessage) {
            this.mLastMessage = lastMessage;
        }
    }

    private void test() {
        mMessageItemList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            mMessageItemList.add(new MessageItem("businessUrl", "businessAvatarUrl", "数字山谷" + i, "hell,times is :" + i));
        }
    }

}
