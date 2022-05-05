package com.example.nfc.event;

import com.example.nfc.object.CaseObject;

import java.util.ArrayList;

public interface OnCaseListener {

    void onDataChangeListener(ArrayList<CaseObject> arrSimFirewall);

    void onChangeDataSimListener(CaseObject simFirewallObject);

}