package com.example.nfc.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nfc.R;
import com.example.nfc.activity.MainActivity;
import com.example.nfc.adapter.CaseAdapter;
import com.example.nfc.object.CaseManager;
import com.example.nfc.utils.LogUtils;
import com.example.nfc.utils.PrefUtils;
import com.example.nfc.view.SecurityTextView;

@SuppressLint("ValidFragment")
public class SystemFragment extends Fragment {

    public static final String TAG = "SystemFragment";
    private SecurityTextView tvInfo;
    private Activity activity;
    private String caseType = "";

    private static Vibrator mVibrator = null;
    private final static long VIBRATOR_ON_TIME = 1000;
    private final static long VIBRATOR_OFF_TIME = 500;
    long[] pattern = {VIBRATOR_OFF_TIME, VIBRATOR_ON_TIME};

    TelephonyManager mTelephonyManager = null;

    private Context mContext;


    public SystemFragment() {
        // Required empty public constructor
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
        if (caseType.equals(CaseManager.READERTAG)) {
            tvInfo.setText("READING...");
        } else if (caseType.equals(CaseManager.EMULATIONTAG)) {
            tvInfo.setText("EMULATION...");
        }
        tvInfo.setTextSize(13);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_system_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFragment(view);
    }

    private void initFragment(View view) {
        tvInfo = view.findViewById(R.id.tv_info);
        mVibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        if (mVibrator == null) {
            LogUtils.loge( "No mVibrator service here");
        }

        mTelephonyManager = (TelephonyManager) getActivity().getSystemService(
                Context.TELEPHONY_SERVICE);
        if (mTelephonyManager == null) {
            LogUtils.loge( "No mWifiManager service here");
        }
    }

    public void onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ((MainActivity) getActivity()).showCaseListScreen(false, CaseAdapter.NO_ACTION);
        } else if (keyCode == KeyEvent.KEYCODE_ESCAPE) {
            PrefUtils.putInt(getActivity(), caseType, CaseManager.PASS);
            ((MainActivity) getActivity()).updateCaseResult(caseType, CaseManager.PASS);
            ((MainActivity) getActivity()).showCaseListScreen(true, CaseAdapter.ENTER_ITEM);
            mVibrator.cancel();
        } else if (keyCode == KeyEvent.KEYCODE_ENTER) {
            PrefUtils.putInt(getActivity(), caseType, CaseManager.FAIL);
            ((MainActivity) getActivity()).updateCaseResult(caseType, CaseManager.FAIL);
            ((MainActivity) getActivity()).showCaseListScreen(true, CaseAdapter.ENTER_ITEM);
            mVibrator.cancel();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        if (context instanceof Activity) {
            activity = (Activity) context;
        }
    }


}