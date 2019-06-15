package com.movie.mling.movieapp.utils.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.fragment.IndexFragment;
import com.movie.mling.movieapp.fragment.MapFragment;
import com.movie.mling.movieapp.fragment.UserFragment;
import com.movie.mling.movieapp.fragment.YanyuanFragment;
import com.movie.mling.movieapp.mould.IndexActivity;
import com.movie.mling.movieapp.mould.LoginActivity;
import com.movie.mling.movieapp.utils.common.FirstLocation;
import com.movie.mling.movieapp.utils.common.MainPermissionsUtils;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;


/**
 * 作者：MLing on 2016/12/16 14:28
 * 邮箱：mamenglingkl1314@163.com
 */
public class CustomSingoButtonFeedbackDialog {
    private Dialog mDialog;
    private ViewGroup mContent;
    private Context context;
    private RadioGroup radio_group;
    private RadioButton rb_lxrcw;
    private RadioButton rb_hgsjy;
    private RadioButton rb_stdjjrgs;
    private RadioButton rb_wygxxx;
    private String checkStr = "";
    private TextView leftButton;
    private OnButtonListener buttonListener;
    private boolean checked=false;

    public CustomSingoButtonFeedbackDialog(Context context) {
        this.mDialog = new Dialog(context, R.style.dialog);
        this.context = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_sing_custom_feedback_botton, null);
        this.mContent = (ViewGroup) view.findViewById(R.id.content);
        this.leftButton = (TextView) view.findViewById(R.id.left);
        this.radio_group = (RadioGroup) view.findViewById(R.id.radio_group);
        this.rb_lxrcw = (RadioButton) view.findViewById(R.id.rb_lxrcw);
        this.rb_hgsjy = (RadioButton) view.findViewById(R.id.rb_hgsjy);
        this.rb_stdjjrgs = (RadioButton) view.findViewById(R.id.rb_stdjjrgs);
        this.rb_wygxxx = (RadioButton) view.findViewById(R.id.rb_wygxxx);
        this.radio_group.setOnCheckedChangeListener(new OnTabListener());
        this.leftButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (buttonListener != null) {
                    if (rb_lxrcw.isChecked()) {
                        checkStr = rb_lxrcw.getText().toString();
                    } else if (rb_hgsjy.isChecked()) {
                        checkStr = rb_hgsjy.getText().toString();
                    } else if (rb_stdjjrgs.isChecked()) {
                        checkStr = rb_stdjjrgs.getText().toString();
                    } else if (rb_wygxxx.isChecked()) {
                        checkStr = rb_wygxxx.getText().toString();
                    }
                    buttonListener.onLeftButtonClick(CustomSingoButtonFeedbackDialog.this, checkStr);
                }

            }
        });

        this.mDialog.setCancelable(false);
        this.mDialog.setContentView(view);
        this.mDialog.show();
    }

    public void setView(View v) {
        this.mContent.removeAllViews();
        this.mContent.addView(v);
    }

    public void setLeftButtonText(String text) {
        this.leftButton.setText(text);
    }

    public void setLeftButtonText(int textID) {
        this.leftButton.setText(textID);
    }

    public void setLeftButtonTextColor(int color) {
        this.leftButton.setTextColor(ContextCompat.getColor(context, color));
    }

    public void show() {
        this.mDialog.show();
    }

    public void cancel() {
        this.mDialog.cancel();
    }

    public OnButtonListener getButtonListener() {
        return this.buttonListener;
    }

    public void setButtonListener(OnButtonListener buttonListener) {
        this.buttonListener = buttonListener;
    }

    public interface OnButtonListener {
        void onLeftButtonClick(CustomSingoButtonFeedbackDialog var1, String str);
    }


    protected class OnTabListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_lxrcw://
                    leftButton.setEnabled(true);
                    break;
                case R.id.rb_hgsjy://
                    leftButton.setEnabled(true);
                    break;
                case R.id.rb_stdjjrgs://
                    leftButton.setEnabled(true);
                    break;
                case R.id.rb_wygxxx://
                    leftButton.setEnabled(true);
                    break;
            }
        }

    }
}
