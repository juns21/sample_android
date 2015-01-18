package com.example.c.listview01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by c on 2015-01-18.
 */
public class MyCustomAdapter extends BaseAdapter {
    Context mContext;
    int mLayout;
    ArrayList<MyData> mList;
    LayoutInflater inf;

    public MyCustomAdapter(Context context, int layout, ArrayList<MyData> mList) {
        mContext = context;
        mLayout = layout;
        this.mList = mList;

        inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        //return 0;
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        //return null;
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        //return 0;
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return null;
        if (convertView == null) {
            convertView = inf.inflate(mLayout, null);
        }
        ImageView imageView = (ImageView)convertView.findViewById(R.id.rowIcon);
        TextView textViewTitle = (TextView)convertView.findViewById(R.id.rowTitle);
        TextView textViewSubTitle = (TextView)convertView.findViewById(R.id.rowSubTitle);

        MyData data = mList.get(position);
        imageView.setImageResource(data.mImgIcon);
        textViewTitle.setText(data.mTitle);
        textViewSubTitle.setText(data.mSubTitle);

        return convertView;
    }
}
