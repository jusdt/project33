package com.example.nfc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {
    public static String CONFIG_FILE_NAME = "config_file_name";
    public static final String BASE_DIR = "/sdcard/FTM_AP/";
    public static final String RES_RAW_SOUND_WAV = "qualsound.wav";
    public static final String RES_RAW_TONE_1KHZ_WAV = "tone_single_1khz.wav";
    public static final String SDCARD_DIR = "/sdcard/";

    public static final String SOUND_FILE_PATH = BASE_DIR + RES_RAW_SOUND_WAV;
    public static final String TONE_1KHZ_FILE_PATH = BASE_DIR + RES_RAW_TONE_1KHZ_WAV;

    public static String getCurrentTime()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a", Locale.getDefault());
        return sdf.format(new Date());
    }
}
