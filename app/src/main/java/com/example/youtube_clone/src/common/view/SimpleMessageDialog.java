package com.example.youtube_clone.src.common.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import com.example.youtube_clone.R;

import java.util.Objects;

public class SimpleMessageDialog extends Dialog implements View.OnClickListener {


    private String mMessage;
    private String mBtnText;
    private Context mContext;
    private TextView mTextViewMessage;
    private Button mBtnConfirm;

    private OnClickListener onClickListener;

    public SimpleMessageDialog(@NonNull Context context, String message, String btnText, OnClickListener onClickListener) {
        super(context);
        mBtnText = btnText;
        mMessage = message;
        mContext = context;
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(Dialog dialog);
    }

    public SimpleMessageDialog(Builder builder) {
        this(builder.mContext, builder.mMessage, builder.mBtnText, builder.onClickListener);
    }

    public static class Builder {
        private String mMessage = "";
        private String mBtnText = "";
        private Context mContext;
        private OnClickListener onClickListener;

        public Builder(Context mContext) {
            this.mContext = mContext;
        }

        public Builder setMessage(String message) {
            mMessage = message;
            return this;
        }

        public Builder setButtonText(String btnText) {
            mBtnText = btnText;
            return this;
        }

        public Builder setOnClickListener(OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
            return this;
        }

        public SimpleMessageDialog build() {
            return new SimpleMessageDialog(this);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_simple_message);

        /* findViewByID */
        mTextViewMessage = findViewById(R.id.dialog_simple_message_tv_desc);
        mBtnConfirm = findViewById(R.id.dialog_simple_message_tv_positive);

        /* Set View */
        mTextViewMessage.setText(mMessage);
        mBtnConfirm.setText(mBtnText);

        /* Set Window */
        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        /* Set On Click Listener */
        mBtnConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_simple_message_tv_positive:
                if (onClickListener != null) {
                    onClickListener.onClick(this);
                } else {
                    dismiss();
                }
                break;
        }
    }
}
