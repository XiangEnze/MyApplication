package com.example.user.myapplication;

import android.app.Application;
public class MyApp extends Application {
    private static String imel;
    public static String getImel() {
        return imel;
    }
    public void setImel(String s) {
        imel = s;
    }

    private static String textPhoneNo;
    public static String gettextPhoneNo() {
        return textPhoneNo;
    }
    public void settextPhoneNo(String s) {
        textPhoneNo = s;
    }
}