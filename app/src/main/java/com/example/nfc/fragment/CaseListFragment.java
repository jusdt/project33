package com.example.nfc.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nfc.R;
import com.example.nfc.activity.MainActivity;
import com.example.nfc.adapter.CaseAdapter;
import com.example.nfc.event.OnCaseListener;
import com.example.nfc.event.OnFooterChangeListener;
import com.example.nfc.object.CaseManager;
import com.example.nfc.object.CaseObject;
import com.example.nfc.utils.LogUtils;
import com.example.nfc.view.AlertPopup;

import java.util.ArrayList;

public class CaseListFragment extends Fragment implements CaseAdapter.OnItemClickCaseListener,
        View.OnClickListener, View.OnKeyListener, OnCaseListener {

    public static final String TAG = CaseListFragment.class.getSimpleName();
    private RecyclerView rlCase;
    private ArrayList<CaseObject> arrCaseList;
    private CaseAdapter testCaseAdapter;
    private CaseManager mCaseManager;

    private OnFooterChangeListener footerChangeListener;
    private OnSelectItemListener onSelectItemListener;
    private AlertPopup alertPopup;
    private Handler handler = new Handler();

    private int curPositionClick;

    public CaseListFragment() {
        // Required empty public constructor
    }

    public static CaseListFragment newInstance(String param1, String param2) {
        CaseListFragment fragment = new CaseListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_case_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFragment(view);
    }

    private void initFragment(View view) {
        rlCase = view.findViewById(R.id.rl_case_list);

        mCaseManager = new CaseManager(getActivity());

        if (arrCaseList == null) {
            arrCaseList = mCaseManager.initArrayCaseObject();
        }

        LogUtils.logd(arrCaseList.toString());

        testCaseAdapter = new CaseAdapter(getContext(), arrCaseList, this);
        GridLayoutManager gridLayoutMng = new GridLayoutManager(getContext(), 1);
        gridLayoutMng.setOrientation(RecyclerView.VERTICAL);
        rlCase.setLayoutManager(gridLayoutMng);
        rlCase.setAdapter(testCaseAdapter);
        alertPopup = AlertPopup.getInstance();
        rlCase.requestFocus();
        rlCase.setFocusable(true);
    }

    public void updateCaseResultList(String caseTitle, int result) {
        for(int i = 0; i < arrCaseList.size(); i++) {
            if(arrCaseList.get(i).getTitle().equals(caseTitle)) {
                arrCaseList.get(i).setResult(result);
            }
        }
    }

    public void updateHistoryList(boolean updateList, int action) {
        LogUtils.logd("thanhlt check updateList: " + updateList + " action: " + action);
//        if (updateList && action == CaseAdapter.NO_ACTION) {
//            testCaseAdapter = new CaseAdapter(getContext(), arrCaseList, this);
//            rlCase.setAdapter(testCaseAdapter);
//            testCaseAdapter.notifyDataSetChanged();
//            rlCase.requestFocus();
//        }
        testCaseAdapter.swap(CaseAdapter.ENTER_ITEM);
    }

    @Override
    public void onStart() {
        super.onStart();
        rlCase.requestFocus();
    }

    @Override
    public void onItemClickCase(int postion) {
        curPositionClick = postion;
//        testCaseAdapter.swap(CaseAdapter.ENTER_ITEM);
    }

    @Override
    public void onItemClickOptionListener() {
        Log.i(TAG, "onItemClickOptionListener");
        rlCase.setVisibility(View.VISIBLE);
        if (footerChangeListener != null) {
            Log.i(TAG, "footerChangeListener");
            footerChangeListener.onChangeLeftListener(null);
            footerChangeListener.onChangeCenterListener(getString(R.string.run));
            footerChangeListener.onChangeRightListener(getString(R.string.back));
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (alertPopup != null && alertPopup.isConfirmPopup()) {
                alertPopup.setOnKeyAlert(keyCode, event);
                return true;
            }
        }
        return false;
    }


    @Override
    public void onClick(View v) {

    }

    public void setFooterChangeListener(OnFooterChangeListener footerChangeListener) {
        this.footerChangeListener = footerChangeListener;
    }

    public void setSelectItemListener(OnSelectItemListener onSelectItemListener) {
        this.onSelectItemListener = onSelectItemListener;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (alertPopup != null) {
            alertPopup.dismissPopup();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDataChangeListener(ArrayList<CaseObject> arrSimFirewall) {
            if (arrSimFirewall != null && arrSimFirewall.size() > 0) {
                if (this.arrCaseList == null) {
                    this.arrCaseList = new ArrayList<>();
                }
                this.arrCaseList.addAll(arrSimFirewall);
                if (testCaseAdapter != null) {
                    testCaseAdapter.notifyDataSetChanged();
                }
            }
    }

    @Override
    public void onChangeDataSimListener(CaseObject simFirewallObject) {

    }


    public interface OnSelectItemListener {

        void onSelectItemListener();

    }

    public void onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ((MainActivity) getActivity()).finish();
        }
    }

}