package com.manveerbasra.ontime.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.util.Log;

import com.manveerbasra.ontime.db.converter.DateConverter;
import com.manveerbasra.ontime.db.converter.StringArrayConverter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Entity(tableName = "alarms")
@TypeConverters({DateConverter.class, StringArrayConverter.class})
public class Alarm {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="alarm_id")
    public int id;

    @ColumnInfo(name = "alarm_time")
    public Date time;

    @ColumnInfo(name = "alarm_active")
    public boolean active;

    @ColumnInfo(name = "alarm_active_days")
    public String[] activeDays;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String[] getActiveDays() {
        return activeDays;
    }

    public void setActiveDays(String[] activeDays) {
        this.activeDays = activeDays;
    }

    public Alarm() {
    }

    // Ignored Methods

    @Ignore
    public Alarm(Date time, boolean active, String[] activeDays) {
        this.time = time;
        this.active = active;
        this.activeDays = activeDays;
    }

    @Ignore
    public boolean isRepeat() {
        return (activeDays != null && activeDays.length > 0);
    }

    @Ignore
    public String getStringTime() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("hh:mm aa");
        return dateFormatter.format(this.time);
    }

    @Ignore
    public String getStringOfActiveDays() {
        if (activeDays.length == 7) {
            return "everyday";
        } else if (activeDays.length == 0) {
            return "never";
        }

        boolean satInArray = false; // "Saturday" in activeDays
        boolean sunInArray = false; // "Sunday" in activeDays

        StringBuilder builder = new StringBuilder();
        for (String day : activeDays) {
            if (day.equals("Saturday")) {
                satInArray = true;
            } else if (day.equals("Sunday")) {
                sunInArray = true;
            }
            String formattedDay = day.substring(0, 3) + ", ";
            builder.append(formattedDay);
        }

        if (satInArray && sunInArray && activeDays.length == 2) {
            return "weekends";
        } else if (!satInArray && !sunInArray && activeDays.length == 5) {
            return "weekdays";
        }

        if (builder.length() > 1) {
            builder.setLength(builder.length() - 2);
        }

        return builder.toString();
    }

    @Ignore
    public long getTimeToRing() {
        String[] timeString = new SimpleDateFormat("HH:mm").format(this.time).split(":");
        int hour = Integer.parseInt(timeString[0]);
        int minute = Integer.parseInt(timeString[1]);
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Log.i("Alarm.java", calendar.getTime().toString());
        return calendar.getTimeInMillis() - System.currentTimeMillis();
    }
}