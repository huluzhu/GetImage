package com.bwie.getimage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;


public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Bean.DataBean.ComicsBean> list;

    public MyAdapter(Context context, List<Bean.DataBean.ComicsBean> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageview);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Bean.DataBean.ComicsBean comicsBean = list.get(position);
        Glide.with(context).load(comicsBean.getCover_image_url()).into(holder.imageView);
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
    }
}
