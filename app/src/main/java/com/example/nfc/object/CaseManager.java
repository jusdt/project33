package com.example.nfc.object;

import android.content.Context;

import com.example.nfc.utils.PrefUtils;

import java.util.ArrayList;

public class CaseManager {
    public static final String TAG = "CaseManager";
    public static final String READERTAG = "READER TAG";
    public static final String EMULATIONTAG = "EMULATION TAG";



    public static final int PASS = 1;
    public static final int FAIL = 2;

    private Context mContext;

    public CaseManager(Context context) {
             this.mContext = context;
    }

    public ArrayList<com.example.nfc.object.CaseObject> initArrayCaseObject() {
        ArrayList<com.example.nfc.object.CaseObject> arrCase = new ArrayList<>(18);

        arrCase.add(new CaseObject(READERTAG, PrefUtils.getInt(mContext, READERTAG)));
        arrCase.add(new CaseObject(EMULATIONTAG, PrefUtils.getInt(mContext, EMULATIONTAG)));

        return arrCase;
    }
}
