package com.radlsuttonedmn.timesum;

/**
 * Created on 2/25/2018.
 * Class that defines an hours, minutes, seconds entity
 */

class TimeEntry {

    private int mHours;
    private int mMinutes;
    private int mSeconds;
    private boolean mMinutesSetToZero;
    private boolean mSecondsSetToZero;
    private String mFocusSetting;

    TimeEntry() {
        mHours = 0;
        mMinutes = 0;
        mSeconds = 0;
        mMinutesSetToZero = false;
        mSecondsSetToZero = false;
        mFocusSetting = "none";
    }

    int getHours() {
        return mHours;
    }

    int getMinutes() {
        return mMinutes;
    }

    int getSeconds() {
        return mSeconds;
    }

    boolean isMinutesSetToZero() { return mMinutesSetToZero; }

    boolean isSecondsSetToZero() { return mSecondsSetToZero; }

    String getFocusSetting() { return mFocusSetting; }


    void setHours(int inHours) { mHours = inHours; }

    void setMinutes(int inMinutes) {
        mMinutes = inMinutes;
    }

    void setSeconds(int inSeconds) {
        mSeconds = inSeconds;
    }

    void setMinutesSetToZero(boolean inValue) { mMinutesSetToZero = inValue; }

    void setSecondsSetToZero(boolean inValue) { mSecondsSetToZero = inValue; }

    void setFocusSetting(String inFocus) { mFocusSetting = inFocus; }
}
