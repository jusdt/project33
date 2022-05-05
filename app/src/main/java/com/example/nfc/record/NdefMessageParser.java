/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.example.nfc.record;

import android.app.Activity;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nfc.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for creating {@linkParsedNdefMessage}s.
 */
public class NdefMessageParser {

    // Utility class
    private NdefMessageParser() {

    }

    /** Parse an NdefMessage */
    public static List<ParsedNdefRecord> parse(NdefMessage message) {
        return getRecords(message.getRecords());
    }

    public static List<ParsedNdefRecord> getRecords(NdefRecord[] records) {
        List<ParsedNdefRecord> elements = new ArrayList<ParsedNdefRecord>();
        for (final NdefRecord record : records) {
            if (com.example.nfc.record.UriRecord.isUri(record)) {
                elements.add(com.example.nfc.record.UriRecord.parse(record));
            } else if (com.example.nfc.record.TextRecord.isText(record)) {
                elements.add(com.example.nfc.record.TextRecord.parse(record));
            } else if (com.example.nfc.record.SmartPoster.isPoster(record)) {
                elements.add(com.example.nfc.record.SmartPoster.parse(record));
            } else {
            	elements.add(new ParsedNdefRecord() {
					@Override
					public View getView(Activity activity, LayoutInflater inflater, ViewGroup parent, int offset) {
				        TextView text = (TextView) inflater.inflate(R.layout.activity_case, parent, false);
				        text.setText(new String(record.getPayload()));
				        return text;
					}
            		
            	});
            }
        }
        return elements;
    }
}
