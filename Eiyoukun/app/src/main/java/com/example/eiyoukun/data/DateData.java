package com.example.eiyoukun.data;

import com.example.eiyoukun.EntityUser;

import java.util.Date;

//CalendarAdapterで使うデータ型

public class DateData{
    private Date date;
    private EntityUser user;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public EntityUser getUser() {
        return user;
    }

    public void setUser(EntityUser user) {
        this.user = user;
    }
}
