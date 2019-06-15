package com.movie.mling.movieapp.utils.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.movie.mling.movieapp.R;

/**
 * 作者：MLing on 2016/12/16 14:28
 * 邮箱：mamenglingkl1314@163.com
 */
public class CustomButtonPhoneDialog {
    private Dialog mDialog;
    private ViewGroup mContent;
    private Context context;
    private TextView tv_clace, tv_copy_phone, tv_send_msg, tv_call_phone;
    private OnButtonListener buttonListener;

    public CustomButtonPhoneDialog(Context context) {
        this.mDialog = new Dialog(context, R.style.dialog);
        this.context = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_custom_botton, null);
        this.mContent = (ViewGroup) view.findViewById(R.id.content);
        this.tv_clace = (TextView) view.findViewById(R.id.tv_clace);
        this.tv_copy_phone = (TextView) view.findViewById(R.id.tv_copy_phone);
        this.tv_send_msg = (TextView) view.findViewById(R.id.tv_send_msg);
        this.tv_call_phone = (TextView) view.findViewById(R.id.tv_call_phone);

        this.tv_clace.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (buttonListener != null) {
                    buttonListener.onCancleButtonClick(CustomButtonPhoneDialog.this);
                }

            }
        });
        this.tv_call_phone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (CustomButtonPhoneDialog.this.buttonListener != null) {
                    CustomButtonPhoneDialog.this.buttonListener.onCallButtonClick(CustomButtonPhoneDialog.this);
                }

            }
        });
        this.tv_send_msg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (CustomButtonPhoneDialog.this.buttonListener != null) {
                    CustomButtonPhoneDialog.this.buttonListener.onSendButtonClick(CustomButtonPhoneDialog.this);
                }

            }
        });
        this.tv_copy_phone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (CustomButtonPhoneDialog.this.buttonListener != null) {
                    CustomButtonPhoneDialog.this.buttonListener.onCopyButtonClick(CustomButtonPhoneDialog.this);
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
        void onCancleButtonClick(CustomButtonPhoneDialog var1);

        void onCopyButtonClick(CustomButtonPhoneDialog var1);
        void onSendButtonClick(CustomButtonPhoneDialog var1);

        void onCallButtonClick(CustomButtonPhoneDialog var1);
    }
}
