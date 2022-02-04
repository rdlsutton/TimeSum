package com.radlsuttonedmn.timesum;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created on 12/24/2018.
 * Custom EditText to support OnTouchListener which overrides performClick to support accessibility
 */

public class OnTouchEditText extends androidx.appcompat.widget.AppCompatEditText {

    public OnTouchEditText(Context context) {
        super(context);
    }

    public OnTouchEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OnTouchEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //public OnTouchEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    //    super(context, attrs, defStyleAttr, defStyleRes);
    //}

    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }
}
