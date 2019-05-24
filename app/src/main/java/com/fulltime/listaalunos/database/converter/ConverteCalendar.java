package com.fulltime.listaalunos.database.converter;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class ConverteCalendar {

    @TypeConverter
    public Long toLong(Calendar calendar) {
        if (calendar != null) {
            return calendar.getTimeInMillis();
        }
        return Calendar.getInstance().getTimeInMillis();
    }

    @TypeConverter
    public Calendar toCalendar(Long value) {
        Calendar calendar = Calendar.getInstance();
        if (value != null) {
            calendar.setTimeInMillis(value);
        }
        return calendar;
    }

}
