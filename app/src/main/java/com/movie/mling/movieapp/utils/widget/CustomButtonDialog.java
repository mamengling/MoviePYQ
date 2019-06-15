package com.movie.mling.movieapp.utils.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.movie.mling.movieapp.R;

/**
 * 作者：MLing on 2016/12/16 14:28
 * 邮箱：mamenglingkl1314@163.com
 */
public class CustomButtonDialog {
    private Dialog mDialog;
    private ViewGroup mContent;
    private Context context;
    private TextView mTextView;
    private ImageView mImageHint;
    private Button leftButton;
    private Button rightButton;
    private OnButtonListener buttonListener;

    public CustomButtonDialog(Context context) {
        this.mDialog = new Dialog(context, R.style.dialog);
        this.context=context;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_d_custom_botton,null);
        this.mContent = (ViewGroup) view.findViewById(R.id.content);
        this.mTextView = (TextView) view.findViewById(R.id.text);
        this.mImageHint = (ImageView) view.findViewById(R.id.image_icon);
        this.mImageHint.setVisibility(View.GONE);
        this.leftButton = (Button) view.findViewById(R.id.left);
        this.leftButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (buttonListener != null) {
                    buttonListener.onLeftButtonClick(CustomButtonDialog.this);
                }

            }
        });
        this.rightButton = (Button) view.findViewById(R.id.right);
        this.rightButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (CustomButtonDialog.this.buttonListener != null) {
                    CustomButtonDialog.this.buttonListener.onRightButtonClick(CustomButtonDialog.this);
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

    public void setText(String text) {
        this.mTextView.setText(text);
    }

    public void setImage(int icon) {
        this.mImageHint.setVisibility(View.VISIBLE);
        this.mImageHint.setBackgroundResource(icon);
    }

    public void setText(int textID) {
        this.mTextView.setText(textID);
    }

    public void setLeftButtonText(String text) {
        this.leftButton.setText(text);
    }

    public void setLeftButtonText(int textID) {
        this.leftButton.setText(textID);
    }

    public void setRightButtonText(String text) {
        this.rightButton.setText(text);
    }

    public void setRightButtonText(int textID) {
        this.rightButton.setText(textID);
    }

    public void setLeftButtonTextColor(int color) {
        this.leftButton.setTextColor(ContextCompat.getColor(context,color));
    }

    public void setRightButtonTextColor(int color) {
        this.rightButton.setTextColor(ContextCompat.getColor(context,color));
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
        void onLeftButtonClick(CustomButtonDialog var1);

        void onRightButtonClick(CustomButtonDialog var1);
    }
}
