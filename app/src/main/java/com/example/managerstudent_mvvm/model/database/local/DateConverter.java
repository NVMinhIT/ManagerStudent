package com.example.managerstudent_mvvm.model.database.local;

import androidx.room.TypeConverter;

import java.util.Calendar;
import java.util.Date;

public class DateConverter {
    @TypeConverter
    public static Calendar toCalendar(Long l) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(l);
        return c;
    }
    @TypeConverter
    public static Long fromCalendar(Calendar c) {
        return c == null ? null : c.getTime().getTime();
    }
    @TypeConverter
    public static Date toDate(Long dateLong){
        return dateLong == null ? null: new Date(dateLong);
    }
}