package com.whereru.greengrass.goforit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.whereru.greengrass.goforit.R;
import com.whereru.greengrass.goforit.baidupush.entity.PushMessage;
import com.whereru.greengrass.goforit.fragment.MessageFragment;
import com.whereru.greengrass.goforit.swipelistview.SwipeListView;

import java.util.List;

public class RelationShipFragmentAdapter extends BaseAdapter {

    private List<PushMessage> mRelationShipItemList;
    private Context mContext;
    private LayoutInflater mInInflater;
    private SwipeListView mListView;

    public RelationShipFragmentAdapter(Context context, SwipeListView swipeListView, List<PushMessage> relationShipItemList) {
        this.mContext = context;
        this.mListView = swipeListView;
        this.mRelationShipItemList = relationShipItemList;
        this.mInInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        if (mRelationShipItemList == null) {
            return 0;
        }
        return mRelationShipItemList.size();
    }

    @Override
    public PushMessage getItem(int position) {
        if (mRelationShipItemList == null || position > mRelationShipItemList.size()) {
            return null;
        }
        return mRelationShipItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final PushMessage item = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInInflater.inflate(R.layout.layout_relationship_fragment_content_item, null);
            holder = new ViewHolder();
            holder.mGotoTop = convertView.findViewById(R.id.go_to_top);
            holder.mDeleleThisOne = convertView.findViewById(R.id.delete_this_one);
            holder.mBusinessName = (TextView) convertView.findViewById(R.id.business_name);
            holder.mBusinesssAbstract = (TextView) convertView.findViewById(R.id.business_abstract);
            holder.mAvatar = (ImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
//头像这里没有设置
        holder.mBusinessName.setText(mRelationShipItemList.get(position).getData().getName());
        holder.mBusinesssAbstract.setText(mRelationShipItemList.get(position).getData().getAbstract());
        holder.mDeleleThisOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRelationShipItemList.remove(position);
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
                mRelationShipItemList.add(0, mRelationShipItemList.remove(position));
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
        TextView mBusinesssAbstract;
    }

}