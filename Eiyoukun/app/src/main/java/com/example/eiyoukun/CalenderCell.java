package com.example.eiyoukun;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CalenderCell extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private String[] mHueArray = {
            "FF4040"
    };

    private static class ViewHolder {

        public TextView  hueTextView;
    }



    public CalenderCell(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return mHueArray.length;
    }

    public Object getItem(int position) {
        return mHueArray[position];
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {

            holder = new ViewHolder();

            holder.hueTextView = (TextView)convertView.findViewById(R.id.dateText);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.hueTextView.setText(mHueArray[position]);

        return convertView;
    }

}
