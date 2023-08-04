package com.example.repairbrain20;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserData {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();
    int last_accuracy_percent = 0;
    String uid,lastly_noted_change,lastly_noted_side_effect,next_step;

    Activity act;

    public UserData() {

    }

    UserData(Activity act)
    {
        this.act = act;
        this.uid = User.uid;
        reference = reference.child(this.uid);

        lastly_noted_change = lastly_noted_side_effect  = next_step = "Not Found";
    }

    public int getLast_accuracy_percent() {
        return last_accuracy_percent;
    }

    public void setLast_accuracy_percent(int last_accuracy_percent) {
        this.last_accuracy_percent = last_accuracy_percent;
    }

    public String getLastly_noted_change() {
        return lastly_noted_change;
    }

    public void setLastly_noted_change(String lastly_noted_change) {
        this.lastly_noted_change = lastly_noted_change;
    }

    public String getLastly_noted_side_effect() {
        return lastly_noted_side_effect;
    }

    public void setLastly_noted_side_effect(String lastly_noted_side_effect) {
        this.lastly_noted_side_effect = lastly_noted_side_effect;
    }

    public String getNext_step() {
        return next_step;
    }

    public void setNext_step(String next_step) {
        this.next_step = next_step;
    }
}

class Time
{
    int day,hour,minute,second,month,year;

    public Time()
    {

    }

    public Time(int year, int month, int day, int hour, int minute, int second) {
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.month = month;
        this.year = year;
    }

    public Time(LocalDateTime date_time)
    {
        this.year = date_time.getYear();
        this.month = date_time.getMonthValue();
        this.day = date_time.getDayOfMonth();
        this.hour = date_time.getHour();
        this.minute = date_time.getMinute();
        this.second = date_time.getSecond();
    }

    public LocalDateTime localtime()
    {
        return LocalDateTime.of(year,month,day,hour,minute,second);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

class Plot
{
    List<Integer> date,value;

    Plot()
    {

    }

    public Plot(List<Integer> date, List<Integer> value) {
        this.date = date;
        this.value = value;
    }

    public List<Integer> getDate() {
        return date;
    }

    public void setDate(List<Integer> date) {
        this.date = date;
    }

    public List<Integer> getValue() {
        return value;
    }

    public void setValue(List<Integer> value) {
        this.value = value;
    }
}

class ReplaceHabits
{
    Map<String,Integer> days_data;
    List<String> show_on;

    public ReplaceHabits()
    {

    }

    public ReplaceHabits(Map<String, Integer> days_data, List<String> show_on) {
        this.days_data = days_data;
        this.show_on = show_on;
    }

    public Map<String, Integer> getDays_data() {
        return days_data;
    }

    public void setDays_data(Map<String, Integer> days_data) {
        this.days_data = days_data;
    }

    public List<String> getShow_on() {
        return show_on;
    }

    public void setShow_on(List<String> show_on) {
        this.show_on = show_on;
    }
}


