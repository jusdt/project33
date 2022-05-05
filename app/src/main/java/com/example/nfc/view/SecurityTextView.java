package com.example.nfc.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.example.nfc.R;

@SuppressLint("AppCompatCustomView")
public class SecurityTextView extends TextView {

    public SecurityTextView(Context context) {
        super(context);
        init(null);
    }

    public SecurityTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SecurityTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SecurityTextView);
            int fontName = typedArray.getInt(R.styleable.SecurityTextView_fontName, 0);
            if (fontName != 0) {
                Typeface font = ResourcesCompat.getFont(getContext(), fontName);
                setTypeface(font);
            } else {
                Typeface font = ResourcesCompat.getFont(getContext(), R.font.ginno_fonts_regular);
                setTypeface(font);
            }
            typedArray.recycle();
        }
    }

}
