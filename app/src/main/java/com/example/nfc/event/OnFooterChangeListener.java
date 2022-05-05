package com.example.nfc.event;

public interface OnFooterChangeListener {

    void onChangeLeftListener(String textLeft);

    void onChangeRightListener(String right);

    void onChangeCenterListener(String center);

    void onShowFooterListener(boolean isShowFooter);

}
