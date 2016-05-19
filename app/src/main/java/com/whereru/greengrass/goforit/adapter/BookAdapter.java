package com.whereru.greengrass.goforit.adapter;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.exampleswipelistviewtest.R;
import com.exampleswipelistviewtest.entity.Book;
import com.fortysevendeg.swipelistview.SwipeListView;
import com.whereru.greengrass.goforit.activity.Book;

public class BookAdapter extends BaseAdapter {

    private List<Book> data;
    private Context context;
    private LayoutInflater minInflater;

    public BookAdapter(Context context, List<Book> data) {
        this.context = context;
        this.data = data;
        
        minInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Book getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Book item = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
        	
            convertView = minInflater.inflate(R.layout.package_row, parent, false);
            
            holder = new ViewHolder();
            holder.ivLogo = (ImageView) convertView.findViewById(R.id.iv_logo);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tvDescription = (TextView) convertView.findViewById(R.id.tv_description);
            holder.btDelete = (Button) convertView.findViewById(R.id.bt_delete);
            holder.btEdit = (Button) convertView.findViewById(R.id.bt_edit);
            
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ((SwipeListView)parent).recycle(convertView, position);

        holder.ivLogo.setImageResource(item.getLogo());
        holder.tvTitle.setText(item.getName());
        holder.tvDescription.setText(item.getDesc());

        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               
            	Toast.makeText(context, R.string.delete, Toast.LENGTH_SHORT).show();
            }
        });

        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            	Toast.makeText(context, R.string.edit, Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    static class ViewHolder {
        ImageView ivLogo;
        TextView tvTitle;
        TextView tvDescription;
        Button btEdit;
        Button btDelete;
    }

}