package com.example.eiyoukun;

import android.content.Context;

import com.example.eiyoukun.data.DateData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateManager {
    Calendar mCalendar;
    private Context mContext;
            /*-----
         修正
         public DateManager();
         ↓↓↓↓↓↓↓↓↓
         public  DateManager(Context context);
        -----*/
    public DateManager(Context context){
        mCalendar = Calendar.getInstance();
        mContext = context;
    }

    //当月の要素を取得
    public List<DateData> getDays(){
        //現在の状態を保持
        Date startDate = mCalendar.getTime();

        //GridViewに表示するマスの合計を計算
        int count = getWeeks() * 7 ;

        //当月のカレンダーに表示される前月分の日数を計算
        mCalendar.set(Calendar.DATE, 1);
        int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        mCalendar.add(Calendar.DATE, -dayOfWeek);

        List<DateData> days = new ArrayList<>();

        /*-----
        修正
        ------*/
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        String fromSDate = sdf.format(mCalendar.getTime());
        for (int i = 0; i < count; i ++){
            DateData dateData = new DateData();
            dateData.setDate(mCalendar.getTime());
            days.add(dateData);
            mCalendar.add(Calendar.DATE, 1);
        }
        String toSDate = sdf.format(mCalendar.getTime());
        List<EntityUser> users = RoomDB.getInstance(mContext).daoUser().getAll();
        for(DateData dateData:days){
            for (EntityUser user:users) {
                String day= sdf.format(dateData.getDate().getTime());
                if (day.equals(user.getDATE())) {
                    dateData.setUser(user);
                }
            }
        }


        //状態を復元
        mCalendar.setTime(startDate);

        return days;
    }

    //当月かどうか確認
    public boolean isCurrentMonth(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM", Locale.JAPAN);
        String currentMonth = format.format(mCalendar.getTime());
        if (currentMonth.equals(format.format(date))){
            return true;
        }else {
            return false;
        }
    }

    //週数を取得
    public int getWeeks(){
        return mCalendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
    }

    //曜日を取得
    public int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    //翌月へ
    public void nextMonth(){
        mCalendar.add(Calendar.MONTH, 1);
    }

    //前月へ
    public void prevMonth(){
        mCalendar.add(Calendar.MONTH, -1);
    }
}