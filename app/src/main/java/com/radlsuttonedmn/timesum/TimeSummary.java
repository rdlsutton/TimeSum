package com.radlsuttonedmn.timesum;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created on 2/20/2018.
 * Data model for time sum app, performs time summation calculations
 */

class TimeSummary {

    private androidx.appcompat.widget.Toolbar mToolbar;
    final private Activity mActivity;

    // Set up the list of time entries
    final private ArrayList<TimeEntry> mTimeEntries;

    // TimeSummary class constructor
    TimeSummary(Activity activity) {

        mActivity = activity;
        mTimeEntries = new ArrayList<>();
    }

    // Pass in the app title so time sum can be updated
    void setAppTitle(androidx.appcompat.widget.Toolbar toolbar) {
        mToolbar = toolbar;
    }

    // Initialize a list of time entries
    ArrayList<TimeEntry> createTimeEntriesList() {

        for (int i = 1; i <= 500; i++) {
            mTimeEntries.add(new TimeEntry());
        }
        mTimeEntries.get(0).setFocusSetting("hours");

        return mTimeEntries;
    }

    // Compute the sum of all the time entries
    private void computeTimeSum() {

        int totalHours = 0;
        int totalMinutes = 0;
        int totalSeconds = 0;
        String totalTime;

        for(int i = 0; i < mTimeEntries.size(); i++){
            totalHours += mTimeEntries.get(i).getHours();
            totalMinutes += mTimeEntries.get(i).getMinutes();
            totalSeconds += mTimeEntries.get(i).getSeconds();
            while(totalSeconds > 59){
                totalMinutes++;
                totalSeconds -= 60;
            }
            while(totalMinutes > 59){
                totalHours++;
                totalMinutes -= 60;
            }
        }
        totalTime = totalHours + ":";
        if (Integer.toString(totalMinutes).length() == 1) {
            totalTime += "0";
        }
        totalTime += Integer.toString(totalMinutes);
        totalTime += ":";
        if (Integer.toString(totalSeconds).length() == 1) {
            totalTime += "0";
        }
        totalTime += Integer.toString(totalSeconds);
        String title = mActivity.getString(R.string.app_title) + ": " + totalTime;
        if (mToolbar != null) {
            mToolbar.setTitle(title);
        }
    }

    // Set all the time entries back to zero
    void clearAll() {
        for(int i = 0; i < mTimeEntries.size(); i++) {
            mTimeEntries.get(i).setHours(0);
            mTimeEntries.get(i).setMinutes(0);
            mTimeEntries.get(i).setSeconds(0);
            mTimeEntries.get(i).setMinutesSetToZero(false);
            mTimeEntries.get(i).setSecondsSetToZero(false);
            mTimeEntries.get(i).setFocusSetting("none");
        }
        mTimeEntries.get(0).setFocusSetting("hours");
    }

    int getFocusPosition() {
        for(int i = 0; i < mTimeEntries.size(); i++) {
            if ( !mTimeEntries.get(i).getFocusSetting().equals("none")) {
                return i;
            }
        }
        return 0;
    }

    void update(String keyId) {
        int currentValue;
        String newValue;

        for(int i = mTimeEntries.size() - 1; i >= 0; i--) {
            switch (keyId) {
                case "0":
                    if (mTimeEntries.get(i).getFocusSetting().equals("seconds")) {
                        currentValue = mTimeEntries.get(i).getSeconds();
                        newValue = currentValue + keyId;
                        if (Integer.parseInt(newValue) == 0) {
                            mTimeEntries.get(i).setSecondsSetToZero(true);
                            mTimeEntries.get(i).setFocusSetting("none");
                            if (i + 1 < mTimeEntries.size()) {
                                mTimeEntries.get(i + 1).setFocusSetting("hours");
                            }
                        } else {
                            if (Integer.parseInt(newValue) < 60) {
                                mTimeEntries.get(i).setSeconds(Integer.parseInt(newValue));
                                computeTimeSum();
                                if (Integer.parseInt(newValue) > 5) {
                                    mTimeEntries.get(i).setFocusSetting("none");
                                    if (i + 1 < mTimeEntries.size()) {
                                        mTimeEntries.get(i + 1).setFocusSetting("hours");
                                    }
                                }
                            }
                        }
                    }
                    if (mTimeEntries.get(i).getFocusSetting().equals("minutes")) {
                        currentValue = mTimeEntries.get(i).getMinutes();
                        newValue = currentValue + keyId;
                        if (Integer.parseInt(newValue) == 0) {
                            mTimeEntries.get(i).setMinutesSetToZero(true);
                            mTimeEntries.get(i).setFocusSetting("seconds");
                        } else {
                            if (Integer.parseInt(newValue) < 60) {
                                mTimeEntries.get(i).setMinutes(Integer.parseInt(newValue));
                                computeTimeSum();
                                if (Integer.parseInt(newValue) > 5) {
                                    mTimeEntries.get(i).setFocusSetting("seconds");
                                }
                            }
                        }
                    }
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                    if (mTimeEntries.get(i).getFocusSetting().equals("seconds")) {
                        mTimeEntries.get(i).setSecondsSetToZero(false);
                        currentValue = mTimeEntries.get(i).getSeconds();
                        newValue = currentValue + keyId;
                        if (Integer.parseInt(newValue) < 60) {
                            mTimeEntries.get(i).setSeconds(Integer.parseInt(newValue));
                            computeTimeSum();
                            if (Integer.parseInt(newValue) > 5) {
                                mTimeEntries.get(i).setFocusSetting("none");
                                if (i + 1 < mTimeEntries.size()) {
                                    mTimeEntries.get(i + 1).setFocusSetting("hours");
                                }
                            }
                        }
                    }
                    if (mTimeEntries.get(i).getFocusSetting().equals("minutes")) {
                        mTimeEntries.get(i).setMinutesSetToZero(false);
                        currentValue = mTimeEntries.get(i).getMinutes();
                        newValue = currentValue + keyId;
                        if (Integer.parseInt(newValue) < 60) {
                            mTimeEntries.get(i).setMinutes(Integer.parseInt(newValue));
                            computeTimeSum();
                            if (Integer.parseInt(newValue) > 5) {
                                mTimeEntries.get(i).setFocusSetting("seconds");
                            }
                        }
                    }
                    if (mTimeEntries.get(i).getFocusSetting().equals("hours")) {
                        currentValue = mTimeEntries.get(i).getHours();
                        newValue = currentValue + keyId;
                        if (Integer.parseInt(newValue) < 100) {
                            mTimeEntries.get(i).setHours(Integer.parseInt(newValue));
                            computeTimeSum();
                            if (Integer.parseInt(newValue) > 9) {
                                mTimeEntries.get(i).setFocusSetting("minutes");
                            }
                        }
                    }
                    break;
                case "clear":
                    if (mTimeEntries.get(i).getFocusSetting().equals("seconds")) {
                        mTimeEntries.get(i).setSecondsSetToZero(false);
                        String secondsText = Integer.toString(mTimeEntries.get(i).getSeconds());
                        if (secondsText.length() == 2) {
                            secondsText = secondsText.substring(0, 1);
                            mTimeEntries.get(i).setSeconds(Integer.parseInt(secondsText));
                            computeTimeSum();
                        } else if (secondsText.length() == 1) {
                            mTimeEntries.get(i).setSeconds(0);
                            computeTimeSum();
                        }
                    }
                    if (mTimeEntries.get(i).getFocusSetting().equals("minutes")) {
                        mTimeEntries.get(i).setMinutesSetToZero(false);
                        String minutesText = Integer.toString(mTimeEntries.get(i).getMinutes());
                        if (minutesText.length() == 2) {
                            minutesText = minutesText.substring(0, 1);
                            mTimeEntries.get(i).setMinutes(Integer.parseInt(minutesText));
                            computeTimeSum();
                        } else if (minutesText.length() == 1) {
                            mTimeEntries.get(i).setMinutes(0);
                            computeTimeSum();
                        }
                    }
                    if (mTimeEntries.get(i).getFocusSetting().equals("hours")) {
                        String hoursText = Integer.toString(mTimeEntries.get(i).getHours());
                        if (hoursText.length() == 2) {
                            hoursText = hoursText.substring(0, 1);
                            mTimeEntries.get(i).setHours(Integer.parseInt(hoursText));
                            computeTimeSum();
                        } else if (hoursText.length() == 1) {
                            mTimeEntries.get(i).setHours(0);
                            computeTimeSum();
                        }
                    }
                    break;
                case "enter":
                    if (mTimeEntries.get(i).getFocusSetting().equals("seconds")) {
                        mTimeEntries.get(i).setFocusSetting("none");
                        if (i + 1 < mTimeEntries.size()) {
                            mTimeEntries.get(i + 1).setFocusSetting("hours");
                        }
                    }
                    if (mTimeEntries.get(i).getFocusSetting().equals("minutes")) {
                        mTimeEntries.get(i).setFocusSetting("seconds");
                    }
                    if (mTimeEntries.get(i).getFocusSetting().equals("hours")) {
                        mTimeEntries.get(i).setFocusSetting("minutes");
                    }
            }
        }

    }

    void addNewEntry() {
        mTimeEntries.add(new TimeEntry());
    }
}
