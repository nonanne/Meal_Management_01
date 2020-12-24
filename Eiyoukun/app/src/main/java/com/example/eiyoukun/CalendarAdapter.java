package com.example.eiyoukun;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.AbsListView;


import com.example.eiyoukun.data.DateData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarAdapter extends BaseAdapter {
    private List<DateData> dateArray = new ArrayList();
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
        /*-----
         修正
         mDateManager = new DateManager();
         ↓↓↓↓↓↓↓↓↓
         mDateManager = new DateManager(context);
        -----*/
        mDateManager = new DateManager(context);

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

        }
        /*-------
        追加
         -------*/
        DateData currentDateData = dateArray.get(position);
        //セルのサイズを指定
        float dp = mContext.getResources().getDisplayMetrics().density;
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(parent.getWidth()/7 - (int)dp, (parent.getHeight() - (int)dp * mDateManager.getWeeks() ) / mDateManager.getWeeks());
        convertView.setLayoutParams(params);

        //日付のみ表示させる
        SimpleDateFormat dateFormat = new SimpleDateFormat("d", Locale.US);
        holder.dateText.setText(dateFormat.format(currentDateData.getDate()));
//        SimpleDateFormat dateFormatCompare = new SimpleDateFormat("MM月dd日", Locale.US);
        SimpleDateFormat dateFormatCompare = new SimpleDateFormat("MM月dd日", Locale.US);
        EntityUser user = currentDateData.getUser();
        /*------
        修正　落ちる原因データベースの指定のカレントがない場合落ちる
         ------*/
        if (user != null && dateFormatCompare.format(currentDateData.getDate()).endsWith(user.getDATE())) {
            holder.weightText.setText(user.getWEIGHT() + "kg");
            holder.calorieText.setText("Cal:" + user.getCALORIE_COMPARE());
            holder.proteinText.setText("pro:" + user.getPROTEIN_COMPARE());
            holder.carbonText.setText("car:" + user.getCARBON_COMPARE());
            holder.fatText.setText("fat:" + user.getFAT_COMPARE());
        }

    /*元のコード----------------------------
        if (dateFormatCompare.format(dateArray.get(position)).endsWith(userList.get(0).getDATE())) {
            holder.weightText.setText(userList.get(0).getWEIGHT() + "kg");
            holder.calorieText.setText("Cal:" + userList.get(0).getCALORIE_COMPARE());
            holder.proteinText.setText("pro:" + userList.get(0).getPROTEIN_COMPARE());
            holder.carbonText.setText("car:" + userList.get(0).getCARBON_COMPARE());
            holder.fatText.setText("fat:" + userList.get(0).getFAT_COMPARE());
        }

     */

        //当月以外のセルをグレーアウト
        if (mDateManager.isCurrentMonth(currentDateData.getDate())){
            convertView.setBackgroundColor(Color.WHITE);
        }else {
            convertView.setBackgroundColor(Color.LTGRAY);
        }

        //日曜日を赤、土曜日を青に
        int colorId;
        switch (mDateManager.getDayOfWeek(currentDateData.getDate())){
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
