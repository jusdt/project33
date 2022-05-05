package com.example.nfc.utils;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogUtils {
    public static String TAG = "XMMI";

    public static void logi(Object s) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String fileName = stackTrace[3].getFileName();
        String methodName = stackTrace[3].getMethodName();
        s = "[" +fileName + "] " + "[" + methodName + "] " + s;
        Log.i(TAG, s + "");
//        appendLog(Utils.getCurrentTime() + " Info: " + s + "\n");
    }

    public static void loge(Object s) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String fileName = stackTrace[3].getFileName();
        String methodName = stackTrace[3].getMethodName();
        s = "[" +fileName + "] " + "[" + methodName + "] " + s;
        Log.e(TAG, s + "");
//        appendLog(Utils.getCurrentTime() + " Error: " + s + "\n");
    }

    public static void logd(Object s) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String fileName = stackTrace[3].getFileName();
        String methodName = stackTrace[3].getMethodName();
        s = "[" +fileName + "] " + "[" + methodName + "] " + s;
        Log.d(TAG, s + "");
//        appendLog(Utils.getCurrentTime() + " Debug: " + s + "\n");
    }

    public static void appendLog(String text)
    {
        File logFile = new File("sdcard/xmmi.file");
        if (!logFile.exists())
        {
            try
            {
                logFile.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        try
        {
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(text);
            buf.newLine();
            buf.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
