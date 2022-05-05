package com.example.nfc.view;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.nfc.R;

public class AlertPopup {

    private static AlertPopup alertPopup;

    public static synchronized AlertPopup getInstance() {
        if (alertPopup == null) {
            alertPopup = new AlertPopup();
        }
        return alertPopup;
    }

    private ImageView ivIconAlert;
    private com.example.nfc.view.SecurityTextView tvAlert;
    private View vRimFooter;
    private com.example.nfc.view.SecurityTextView tvLeft;
    private com.example.nfc.view.SecurityTextView tvCenter;
    private com.example.nfc.view.SecurityTextView tvRight;
    private RelativeLayout rlFooter;
    private RelativeLayout rlPopup;

    private int drawable = 0;
    private String titleAlert = null;
    private boolean isFooter = false;
    private String textLeft;
    private String textRight;
    private String textCenter;

    private PopupWindow popupWindow;
    private OnKeyPopupListener onKeyPopupListener;

    public void showPopup(Activity activity) {
        if (!isConfirmPopup()) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (inflater != null) {
                View popupView = inflater.inflate(R.layout.view_popup_alert, (ViewGroup) activity.findViewById(R.id.rl_popup));
                ivIconAlert = popupView.findViewById(R.id.iv_icon_alert);
                tvAlert = popupView.findViewById(R.id.tv_alert);
                vRimFooter = popupView.findViewById(R.id.v_rim_footer);
                tvLeft = popupView.findViewById(R.id.tv_left);
                tvCenter = popupView.findViewById(R.id.tv_center);
                tvRight = popupView.findViewById(R.id.tv_right);
                rlFooter = popupView.findViewById(R.id.rl_footer);
                rlPopup = popupView.findViewById(R.id.rl_popup);
                if (!TextUtils.isEmpty(titleAlert)) {
                    tvAlert.setText(titleAlert);
                }
                if (drawable != 0) {
                    ivIconAlert.setImageResource(drawable);
                }
                if (isFooter) {
                    rlFooter.setVisibility(View.VISIBLE);
                    tvLeft.setText(textLeft);
                    tvRight.setText(textRight);
                    tvCenter.setText(textCenter);
                } else {
                    rlFooter.setVisibility(View.GONE);
                }
                popupWindow = new PopupWindow();
                popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
                popupWindow.setContentView(popupView);
                View viewRoot = activity.findViewById(android.R.id.content);
                if (viewRoot == null) {
                    viewRoot = activity.getWindow().getDecorView().findViewById(android.R.id.content);
                }
                popupWindow.showAtLocation(viewRoot, Gravity.CENTER, 0, 0);
            }
        }
    }

    public AlertPopup setIcon(int drawable) {
        this.drawable = drawable;
        if (ivIconAlert != null) {
            ivIconAlert.setImageResource(drawable);
        }
        return alertPopup;
    }

    public AlertPopup setText(String text) {
        this.titleAlert = text;
        if (tvAlert != null) {
            tvAlert.setText(text);
        }
        return alertPopup;
    }

    public AlertPopup showFooter(boolean isFooter, String textLeft, String textCenter, String textRight) {
        this.isFooter = isFooter;
        this.textLeft = textLeft;
        this.textRight = textRight;
        this.textCenter = textCenter;
        if (rlFooter != null) {
            rlFooter.setVisibility(isFooter ? View.VISIBLE : View.GONE);
        }
        if (isFooter) {
            if (tvLeft != null && tvRight != null && tvCenter != null) {
                tvLeft.setText(textLeft);
                tvRight.setText(textRight);
                tvCenter.setText(textCenter);
            }
        }
        return alertPopup;
    }

    public boolean isConfirmPopup() {
        return popupWindow != null && popupWindow.isShowing();
    }

    public void dismissPopup() {
        try {
            if (isConfirmPopup()) {
                popupWindow.dismiss();
                popupWindow = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOnKeyAlert(int keyCode, KeyEvent keyEvent) {
        if (onKeyPopupListener != null) {
            onKeyPopupListener.onKeyPopupListener(keyCode, keyEvent);
        }
    }

    public void setKeyPopupListener(OnKeyPopupListener onKeyPopupListener) {
        this.onKeyPopupListener = onKeyPopupListener;
    }

    public interface OnKeyPopupListener {
        void onKeyPopupListener(int keyCode, KeyEvent keyEvent);
    }

}
