package com.example.nfc.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nfc.event.OnFooterChangeListener;
import com.example.nfc.view.SecurityTextView;
import com.example.nfc.R;

public class FooterFragment extends Fragment implements OnFooterChangeListener {

    public static final String TAG = FooterFragment.class.getSimpleName();
    private View vRimFooter;
    private SecurityTextView tvLeft;
    private SecurityTextView tvCenter;
    private SecurityTextView tvRight;
    private RelativeLayout rlFooter;

    public FooterFragment() {
        // Required empty public constructor
    }

    public static FooterFragment newInstance() {
        FooterFragment fragment = new FooterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_foolter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        vRimFooter = view.findViewById(R.id.v_rim_footer);
        tvLeft = view.findViewById(R.id.tv_left);
        tvCenter = view.findViewById(R.id.tv_center);
        tvRight = view.findViewById(R.id.tv_right);
        rlFooter = view.findViewById(R.id.rl_footer);
    }

    @Override
    public void onChangeLeftListener(String textLeft) {
        if (tvLeft != null) {
            tvLeft.setText(textLeft);
        }
    }

    @Override
    public void onChangeRightListener(String right) {
        if (tvRight != null) {
            tvRight.setText(right);
        }
    }

    @Override
    public void onChangeCenterListener(String center) {
        if (tvCenter != null) {
            tvCenter.setText(center);
        }
    }

    @Override
    public void onShowFooterListener(boolean isShowFooter) {
        if (isShowFooter) {
            rlFooter.setVisibility(View.VISIBLE);
        } else {
            rlFooter.setVisibility(View.GONE);
        }
    }

}