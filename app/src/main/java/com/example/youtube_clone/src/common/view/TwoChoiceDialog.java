package com.example.youtube_clone.src.common.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.example.youtube_clone.R;

import java.util.Objects;

public class TwoChoiceDialog extends Dialog implements View.OnClickListener {

    public static final int BUTTON_LEFT = 0;
    public static final int BUTTON_RIGHT = 1;

    private Context mContext;
    private String mMessage;
    private OnClickListener onClickListener;

    public TwoChoiceDialog(@NonNull Context context, String message, OnClickListener onClickListener) {
        super(context);
        mContext = context;
        mMessage = message;
        this.onClickListener = onClickListener;
    }

    public TwoChoiceDialog(Builder builder) {
        this(builder.mContext, builder.mMessage, builder.onClickListener);
    }

    public interface OnClickListener {
        void onClick(Dialog dialog, int which);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_two_choice);

        /* Set Text */
        ((TextView) findViewById(R.id.tv_desc_dialog_posneg)).setText(mMessage);

        /* Set OnClick Listener */
        findViewById(R.id.two_choice_dialog_tv_right).setOnClickListener(this);
        findViewById(R.id.two_choice_dialog_tv_left).setOnClickListener(this);

        /* Set Window */
        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public boolean dispatchTouchEvent(@NonNull MotionEvent ev) {
        Rect dialogBounds = new Rect();
        findViewById(R.id.two_choice_dialog).getHitRect(dialogBounds);
        if (!dialogBounds.contains((int) ev.getX(), (int) ev.getY())) {
            dismiss();
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.two_choice_dialog_tv_right:
                if (onClickListener != null) {
                    onClickListener.onClick(this, BUTTON_RIGHT);
                } else {
                    dismiss();
                }
                break;
            case R.id.two_choice_dialog_tv_left:
                if (onClickListener != null) {
                    onClickListener.onClick(this, BUTTON_LEFT);
                } else {
                    dismiss();
                }
                break;
        }
    }


    public static class Builder {
        private Context mContext;
        private String mMessage;
        private OnClickListener onClickListener;

        public Builder(Context mContext) {
            this.mContext = mContext;
        }

        public Builder setMessage(String message) {
            this.mMessage = message;
            return this;
        }

        public Builder setOnClickListener(OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
            return this;
        }

        public TwoChoiceDialog build() {
            return new TwoChoiceDialog(this);
        }
    }
}
