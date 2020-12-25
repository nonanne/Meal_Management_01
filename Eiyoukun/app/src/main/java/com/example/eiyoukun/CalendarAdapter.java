package com.example.eiyoukun;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.AbsListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarAdapter extends BaseAdapter {
    private List<Date> dateArray = new ArrayList();
    private Context mContext;
    private DateManager mDateManager;
    private LayoutInflater mLayoutInflater;
    private List<EntityUser> userList;

    //カスタムセルを拡張したらここでWigetを定義
    private static class ViewHolder {
        public TextView dateText;
        public TextView weightText;
        public TextView calorieText;
        public TextView proteinText;
        public TextView carbonText;
        public TextView fatText;

    }

    public CalendarAdapter(Context context){
        this(context, null);
    }

    public CalendarAdapter(Context context, List<EntityUser> userList){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mDateManager = new DateManager();
        dateArray = mDateManager.getDays();
        this.userList = userList;


    }

    @Override
    public int getCount() {
        return dateArray.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.calendar_cell, null);
            holder = new ViewHolder();
            holder.dateText = convertView.findViewById(R.id.dateText);
            holder.weightText = convertView.findViewById(R.id.weightText);
            holder.calorieText = convertView.findViewById(R.id.calorieText);
            holder.proteinText = convertView.findViewById(R.id.proteinText);
            holder.carbonText = convertView.findViewById(R.id.carbonText);
            holder.fatText = convertView.findViewById(R.id.fatText);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
            holder.dateText = convertView.findViewById(R.id.dateText);
            holder.weightText.setText("");
            holder.calorieText.setText("");
            holder.proteinText.setText("");
            holder.carbonText.setText("");
            holder.fatText.setText("");
        }

        //セルのサイズを指定
        float dp = mContext.getResources().getDisplayMetrics().density;
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(parent.getWidth()/7 - (int)dp, (parent.getHeight() - (int)dp * mDateManager.getWeeks() ) / mDateManager.getWeeks());
        convertView.setLayoutParams(params);

        //日付のみ表示させる
        SimpleDateFormat dateFormat = new SimpleDateFormat("d", Locale.US);
        holder.dateText.setText(dateFormat.format(dateArray.get(position)));
        SimpleDateFormat dateFormatCompare = new SimpleDateFormat("MM月dd日", Locale.US);
        if (userList != null && userList.size() != 0) {
            for (int i = 0; i < userList.size(); i++) {
                if (dateFormatCompare.format(dateArray.get(position)).endsWith(userList.get(i).getDATE())) {
                    holder.weightText.setText(userList.get(i).getWEIGHT() == null ? "" : userList.get(i).getWEIGHT() + "kg");
                    holder.calorieText.setText(userList.get(i).getCALORIE_COMPARE() == null ? "" : "Cal:" + userList.get(i).getCALORIE_COMPARE());
                    holder.proteinText.setText(userList.get(i).getPROTEIN_COMPARE() == null ? "" : "pro:" + userList.get(i).getPROTEIN_COMPARE());
                    holder.carbonText.setText(userList.get(i).getCARBON_COMPARE() == null ? "" : "car:" + userList.get(i).getCARBON_COMPARE());
                    holder.fatText.setText(userList.get(i).getFAT_COMPARE() == null ? "" : "fat:" + userList.get(i).getFAT_COMPARE());
                }
            }
        }

        //当月以外のセルをグレーアウト
        if (mDateManager.isCurrentMonth(dateArray.get(position))){
            convertView.setBackgroundColor(Color.WHITE);
        }else {
            convertView.setBackgroundColor(Color.LTGRAY);
        }

        //日曜日を赤、土曜日を青に
        int colorId;
        switch (mDateManager.getDayOfWeek(dateArray.get(position))){
            case 1:
                colorId = Color.RED;
                break;
            case 7:
                colorId = Color.BLUE;
                break;

            default:
                colorId = Color.BLACK;
                break;
        }
        holder.dateText.setTextColor(colorId);

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    //表示月を取得
    public String getTitle(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy年M月", Locale.US);
        return format.format(mDateManager.mCalendar.getTime());
    }

    //翌月表示
    public void nextMonth(){
        mDateManager.nextMonth();
        dateArray = mDateManager.getDays();
        this.notifyDataSetChanged();
    }

    //前月表示
    public void prevMonth(){
        mDateManager.prevMonth();
        dateArray = mDateManager.getDays();
        this.notifyDataSetChanged();
    }
}
