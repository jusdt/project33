package com.example.nfc.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtils {

    private static final String KEY_SHARE_PRE = "SP_XMMI";
    private SharedPreferences preferences;

    @SuppressLint("WrongConstant")
    public static SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences(KEY_SHARE_PRE, Context.MODE_PRIVATE);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sharedPreferences = getSharedPreference(context);
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public static Boolean getBoolean(Context context, String key) {
        SharedPreferences sharedPreferences = getSharedPreference(context);
        return sharedPreferences.getBoolean(key, false);
    }

    public static Boolean getBoolean(Context context, String key, boolean defvalue) {
        SharedPreferences sharedPreferences = getSharedPreference(context);
        return sharedPreferences.getBoolean(key, defvalue);
    }

    public static void putString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = getSharedPreference(context);
        sharedPreferences.edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key) {
        SharedPreferences sharedPreferences = getSharedPreference(context);
        return sharedPreferences.getString(key, null);
    }

    public static String getString(Context context, String key, String defValue) {
        SharedPreferences sharedPreferences = getSharedPreference(context);
        return sharedPreferences.getString(key, defValue);
    }

    public static void putInt(Context context, String key, Integer value) {
        SharedPreferences sharedPreferences = getSharedPreference(context);
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public static Integer getInt(Context context, String key) {
        SharedPreferences sharedPreferences = getSharedPreference(context);
        return sharedPreferences.getInt(key, 0);
    }

    public static Integer getInt(Context context, String key, int defValue) {
        SharedPreferences sharedPreferences = getSharedPreference(context);
        return sharedPreferences.getInt(key, defValue);
    }

    public static void putLong(Context context, String key, long value) {
        SharedPreferences sharedPreferences = getSharedPreference(context);
        sharedPreferences.edit().putLong(key, value).apply();
    }

    public static Long getLong(Context context, String key) {
        SharedPreferences sharedPreferences = getSharedPreference(context);
        return sharedPreferences.getLong(key, 0);
    }

}
