package com.example.nfc.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.nfc.R;
import com.example.nfc.event.OnFooterChangeListener;
import com.example.nfc.event.OnKeyDownActivityListener;
import com.example.nfc.fragment.FooterFragment;
import com.example.nfc.fragment.HeaderFragment;
import com.example.nfc.fragment.CaseListFragment;
import com.example.nfc.fragment.SystemFragment;
import com.example.nfc.utils.LogUtils;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private OnFooterChangeListener footerChangeListener;

    private FrameLayout flBody, flHeader, flFooter;

    private HeaderFragment headerFragment;

    private CaseListFragment caseListFragment;
    private SystemFragment systemFragment; // NFC reader, NFC emulation

    private OnKeyDownActivityListener activityListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtils.logd("thanhltd check onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case);

        FooterFragment footerFragment = new FooterFragment();
        footerChangeListener = footerFragment;
        getSupportFragmentManager().beginTransaction().add(R.id.fl_footer, footerFragment).commit();

//        android.os.Build.getSerial()
        headerFragment = HeaderFragment.newInstance(R.drawable.ic_baseline_nfc_24, "NFC");
        getSupportFragmentManager().beginTransaction().add(R.id.fl_header, headerFragment).commit();

        flBody = (FrameLayout) findViewById(R.id.fl_body);
        flHeader = (FrameLayout) findViewById(R.id.fl_header);
        flFooter = (FrameLayout) findViewById(R.id.fl_footer);

        caseListFragment = new CaseListFragment();
//        caseListFragment.setFooterChangeListener(footerChangeListener);

        systemFragment = new SystemFragment();
//        speakerEarpieceFragment = new SpeakerEarpieceFragment();
//        sensorFragment = new SensorFragment();
//        wifiBluetoothNfcFragment = new WifiBluetoothNfcFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_body, caseListFragment, CaseListFragment.TAG)
                .add(R.id.fl_body, systemFragment, SystemFragment.TAG)
                .hide(systemFragment)
                .show(caseListFragment)
                .commit();

//        prepare();
    }

    public void updateCaseResult(String caseTitle, int result) {
        caseListFragment.updateCaseResultList(caseTitle, result);
    }

    public void showSystemScreen(String caseType) {
        LogUtils.logd("thanhlt check showSystemScreen ");
        getSupportFragmentManager().beginTransaction()
                .hide(caseListFragment)
                .show(systemFragment)
                .commit();
        changeHeader(true, caseType, 0);
        setFooterText(getString(R.string.pass), getString(R.string.fail), getString(R.string.back));
        systemFragment.setCaseType(caseType);
    }

    public void showHeaderFooter() {
        flHeader.setVisibility(View.VISIBLE);
        flFooter.setVisibility(View.VISIBLE);
    }




    public void showCaseListScreen(boolean updateList, int action) {
        LogUtils.logd("thanhlt check showCaseListScreen ");
        getSupportFragmentManager().beginTransaction()
                .hide(systemFragment)
                .show(caseListFragment)
                .commit();
        changeHeader(true, "NFC", R.drawable.ic_baseline_nfc_24);
        setFooterText(getString(R.string.select), "", getString(R.string.back));
        caseListFragment.updateHistoryList(updateList, action);
    }

    private boolean isHideFragment(Fragment fragment) {
        return fragment != null && fragment.isHidden();
    }

    public void setFooterText(String left, String center, String right) {
        if (footerChangeListener != null) {
            footerChangeListener.onChangeLeftListener(left);
            footerChangeListener.onChangeCenterListener(center);
            footerChangeListener.onChangeRightListener(right);
        }
    }

    public void changeHeader(boolean isShow, String title, int drawable) {
        if(isShow) {
            flHeader.setVisibility(View.VISIBLE);
            if (headerFragment != null) {
                headerFragment.changeHeader(title, drawable);
            }
        }
        else {
            flHeader.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (footerChangeListener != null) {
            footerChangeListener.onChangeLeftListener(getString(R.string.select));
            footerChangeListener.onChangeCenterListener(" ");
            footerChangeListener.onChangeRightListener(getString(R.string.back));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
                return false;
            }
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                return false;
            }

            if (!isHideFragment(caseListFragment)) {
                caseListFragment.onKeyDown(keyCode, event);
                return true;
            } else if (!isHideFragment(systemFragment)) {
                systemFragment.onKeyDown(keyCode, event);
                return true;
            }
        }
        return false;
    }

    private void prepare() {
        //copy the raw data file into data/ .../files
        LogUtils.logi("prepare start");
        LogUtils.logi("end to prepare");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}