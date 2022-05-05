package com.example.nfc.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.example.nfc.R;

public class HeaderFragment extends Fragment {

    public static final String TAG = HeaderFragment.class.getSimpleName();
    private static final String KEY_ICON_HEADER = "key_icon_header";
    private static final String KEY_TITLE_HEADER = "key_title_header";
    private ImageView ivHeader;
    private TextView tvHeader;
    private LinearLayout llHeader;

    public HeaderFragment() {
        // Required empty public constructor
    }

    public static HeaderFragment newInstance(int drawable, String title) {
        HeaderFragment fragment = new HeaderFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_ICON_HEADER, drawable);
        args.putString(KEY_TITLE_HEADER, title);
        fragment.setArguments(args);
        return fragment;
    }

    private int iconHeader;
    private String titleHeader;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            iconHeader = getArguments().getInt(KEY_ICON_HEADER, 0);
            titleHeader = getArguments().getString(KEY_TITLE_HEADER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_header, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFragment(view);
    }

    private void initFragment(View view) {
        ivHeader = view.findViewById(R.id.iv_header);
        tvHeader = view.findViewById(R.id.tv_header);
        llHeader = view.findViewById(R.id.ll_header);

        tvHeader.setTypeface(ResourcesCompat.getFont(getActivity(), R.font.ginno_fonts_regular));
        if (iconHeader != 0) {
            ivHeader.setImageResource(iconHeader);
        }
        if (!TextUtils.isEmpty(titleHeader)) {
            tvHeader.setText(titleHeader);
        }
    }

    public void changeHeader(String title, int drawable) {
        if (drawable != 0) {
            ivHeader.setImageResource(drawable);
        }
        if (!TextUtils.isEmpty(title)) {
            tvHeader.setText(title);
        }
    }

}