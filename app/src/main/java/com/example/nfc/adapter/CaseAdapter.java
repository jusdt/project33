package com.example.nfc.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nfc.object.CaseObject;
import com.example.nfc.R;
import com.example.nfc.activity.MainActivity;
import com.example.nfc.object.CaseManager;
import com.example.nfc.object.CaseObject;
import com.example.nfc.utils.LogUtils;

import java.util.ArrayList;

public class CaseAdapter extends RecyclerView.Adapter<CaseAdapter.CaseView> {
    public static final String TAG = "CaseAdapter";
    private Context context;
    private ArrayList<CaseObject> arrCaseObject;
    private OnItemClickCaseListener caseListener;
    public static final int NO_ACTION = 0;
    public static final int ENTER_ITEM = 1;
    private int action;

    private int currentPosition = -1;
    private RecyclerView recyclerView;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    public CaseAdapter(Context context, ArrayList<CaseObject> arrCaseObject, OnItemClickCaseListener caseListener) {
        this.context = context;
        this.arrCaseObject = arrCaseObject;
        this.caseListener = caseListener;
        this.action = -1;
    }

    @NonNull
    @Override
    public CaseView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_case, viewGroup, false);
        return new CaseView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CaseView CaseView, @SuppressLint("RecyclerView") int i) {
        CaseObject caseObject = arrCaseObject.get(i);
        CaseView.tvViewTitle.setText(caseObject.getTitle());
        CaseView.tvViewTitle.setTypeface(ResourcesCompat.getFont(context, R.font.ginno_fonts_regular));
        CaseView.itemView.setOnClickListener(new View.OnClickListener() {

            ///this is the reason that causes problem
            @Override
            public void onClick(View v) {
                caseListener.onItemClickCase(i);
                currentPosition = i;
//                showFragmentCase(caseObject.getTitle());
            }
        });

        CaseView.itemView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_ESCAPE) {
                        LogUtils.logd("trongnhat test 1234145123");
//                        if (caseListener != null) {
//                            caseListener.onItemClickOptionListener();
//                        }
                        showFragmentCase(caseObject.getTitle());
                        return true;
                    }

                }
                return false;
            }
        });

        if (action == ENTER_ITEM) {
            if (currentPosition != -1) {
                if (currentPosition == i) {
                    CaseView.itemView.requestFocus();
                    recyclerView.scrollToPosition(i);
                    currentPosition = -1;
                }
            }
        }
    }

    public void swap(int action) {
        this.action = action;
        notifyDataSetChanged();
    }

    private void showFragmentCase(String caseTitle) {
        switch (caseTitle) {
            case CaseManager.READERTAG:
            case CaseManager.EMULATIONTAG:
                ((MainActivity) context).showSystemScreen(caseTitle);
                break;
            default:
                break;
        }
    }


    @Override
    public int getItemCount() {
        return arrCaseObject.size();
    }

    class CaseView extends RecyclerView.ViewHolder {
        private TextView tvViewTitle;
        public CaseView(@NonNull View itemView) {
            super(itemView);
            tvViewTitle = itemView.findViewById(R.id.title);
        }
    }

    public interface OnItemClickCaseListener {
        void onItemClickCase(int position);
        void onItemClickOptionListener();
    }

}
