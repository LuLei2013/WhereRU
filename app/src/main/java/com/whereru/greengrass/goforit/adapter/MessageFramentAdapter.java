package com.whereru.greengrass.goforit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.whereru.greengrass.goforit.fragment.MessageFragment;
import com.whereru.greengrass.goforit.swipelistview.SwipeListView;
import com.whereru.greengrass.goforit.R;

import java.util.List;

public class MessageFramentAdapter extends BaseAdapter {

    private List<MessageFragment.MessageItem> mMessageItemList;
    private Context mContext;
    private LayoutInflater mInInflater;
    private SwipeListView mListView;

    public MessageFramentAdapter(Context context, SwipeListView swipeListView, List<MessageFragment.MessageItem> messageItemList) {
        this.mContext = context;
        this.mListView = swipeListView;
        this.mMessageItemList = messageItemList;
        this.mInInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        if (mMessageItemList == null) {
            return 0;
        }
        return mMessageItemList.size();
    }

    @Override
    public MessageFragment.MessageItem getItem(int position) {
        if (mMessageItemList == null || position > mMessageItemList.size()) {
            return null;
        }
        return mMessageItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MessageFragment.MessageItem item = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInInflater.inflate(R.layout.layout_message_fragment_content_item, null);
            holder = new ViewHolder();
            holder.mGotoTop = convertView.findViewById(R.id.go_to_top);
            holder.mDeleleThisOne = convertView.findViewById(R.id.delete_this_one);
            holder.mBusinessName = (TextView) convertView.findViewById(R.id.business_name);
            holder.mLastMessage = (TextView) convertView.findViewById(R.id.the_last_message);
            holder.mAvatar = (ImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
//头像这里没有设置
        holder.mBusinessName.setText(mMessageItemList.get(position).getBusinessName());
        holder.mLastMessage.setText(mMessageItemList.get(position).getLastMessage());
        holder.mDeleleThisOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMessageItemList.remove(position);
                notifyDataSetChanged();
                /**
                 * 关闭SwipeListView
                 * 不关闭的话，刚删除位置的item存在问题 ，不能再次点击
                 */
                mListView.closeOpenedItems();
            }
        });
        holder.mGotoTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageFragment.MessageItem currrentItem = mMessageItemList.remove(position);
                mMessageItemList.add(0, currrentItem);
                notifyDataSetChanged();
                mListView.closeOpenedItems();
            }
        });

        return convertView;
    }

    static class ViewHolder {
        View mGotoTop;
        View mDeleleThisOne;
        ImageView mAvatar;
        TextView mBusinessName;
        TextView mLastMessage;

    }

}