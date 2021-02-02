package com.alexio.plm;
import java.io.Serializable;

public class DateTime implements Comparable<DateTime>, Serializable {
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minutes;

    public DateTime(int hour,int minutes,int day,int month,int year) {
        this.minutes = minutes;
        this.hour = hour;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
    public void setHour(int hour) {
        this.hour = hour;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public int getMinutes() {
        return minutes;
    }
    public int getHour() {
        return hour;
    }
    public int getDay() {
        return day;
    }
    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Date & Time: "+this.hour+":"+this.minutes+" "+this.day+"-"+this.month+"-"+this.year;
    }

    public int dateTimeToMinutes() {
        return this.year * 365 * 24 * 60 + this.month * 30 * 24 * 60 + this.day * 24 * 60 + this.hour * 60;
    }

    @Override
    public int compareTo(DateTime dateTime) {
        return this.dateTimeToMinutes() - dateTime.dateTimeToMinutes();
    }
}

