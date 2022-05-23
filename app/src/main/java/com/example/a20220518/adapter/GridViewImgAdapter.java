package com.example.a20220518.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a20220518.R;


public class GridViewImgAdapter extends BaseAdapter {
    private Context context;
    int []images;

    public GridViewImgAdapter(Context context,int []images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.grid_view_img_item, null);
        ImageView iv = view.findViewById(R.id.iv_grid_img);
        iv.setImageResource(images[position]);
        return view;
    }
}


