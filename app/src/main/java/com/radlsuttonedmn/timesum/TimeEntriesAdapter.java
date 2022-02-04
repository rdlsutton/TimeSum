package com.radlsuttonedmn.timesum;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

/**
 * Created on 12/24/2018.
 * Adapter for the hours, minutes, seconds values
 */

public class TimeEntriesAdapter extends
    RecyclerView.Adapter<TimeEntriesAdapter.ViewHolder> {

    final private List<TimeEntry> mTimeEntries;
    final private Context mContext;

    TimeEntriesAdapter(Context context, List<TimeEntry> timeEntries) {
        mTimeEntries = timeEntries;
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return mTimeEntries.size();
    }

    private void clearAllFocus() {
        for(int i = 0; i < mTimeEntries.size(); i++) {
            mTimeEntries.get(i).setFocusSetting("none");
        }
    }

    // Define the view holder has an inner class
    class ViewHolder extends RecyclerView.ViewHolder {

        final TextView textViewCount;
        final OnTouchEditText editTextHours;
        final OnTouchEditText editTextMinutes;
        final OnTouchEditText editTextSeconds;

        ViewHolder(View itemView) {

            super(itemView);

            textViewCount = itemView.findViewById(R.id.textViewCount);
            editTextHours = itemView.findViewById(R.id.editTextHours);
            editTextMinutes = itemView.findViewById(R.id.editTextMinutes);
            editTextSeconds = itemView.findViewById(R.id.editTextSeconds);

            // Prevent the system keyboard from appearing when an EditText is tapped
            editTextHours.setTextIsSelectable(true);
            editTextMinutes.setTextIsSelectable(true);
            editTextSeconds.setTextIsSelectable(true);

            editTextHours.setOnTouchListener(new View.OnTouchListener() {

               public boolean onTouch(View view, MotionEvent e) {
                   clearAllFocus();

                   // If getAdapterPosition has no position it will return -1
                   if (getAdapterPosition() >= 0) {
                       TimeEntry timeEntry = mTimeEntries.get(getAdapterPosition());
                       timeEntry.setFocusSetting("hours");
                       editTextHours.requestFocus();


                       // Set the cursor to the end of the text
                       editTextHours.setSelection(editTextHours.getText() == null ? 0 : editTextHours.getText().length());
                   }
                   view.performClick();
                   return true;
                }
            });

            editTextMinutes.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View view, MotionEvent e) {
                    clearAllFocus();
                    if (getAdapterPosition() >= 0) {
                        TimeEntry timeEntry = mTimeEntries.get(getAdapterPosition());
                        timeEntry.setFocusSetting("minutes");
                        editTextMinutes.requestFocus();
                        editTextMinutes.setSelection(editTextMinutes.getText() == null ? 0 : editTextMinutes.getText().length());
                    }
                    view.performClick();
                    return true;
                }
            });

            editTextSeconds.setOnTouchListener(new View.OnTouchListener() {

               public boolean onTouch(View view, MotionEvent e) {
                    clearAllFocus();
                    if (getAdapterPosition() >= 0) {
                        TimeEntry timeEntry = mTimeEntries.get(getAdapterPosition());
                        timeEntry.setFocusSetting("seconds");
                        editTextSeconds.requestFocus();
                        editTextSeconds.setSelection(editTextSeconds.getText() == null ? 0 : editTextSeconds.getText().length());
                    }
                    view.performClick();
                    return true;
                }
            });
        }
    }

    // Inflate the layout from XML and return the holder
    @Override
    @NonNull
    public TimeEntriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View timeEntryView = inflater.inflate(R.layout.time_entry, parent, false);

        // Return a new holder instance
        return new ViewHolder(timeEntryView);
    }

    // Populate data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull TimeEntriesAdapter.ViewHolder viewHolder, int position) {

        // Get the data model based on position
        TimeEntry timeEntry = mTimeEntries.get(position);
        String timeValue;

        // Set the item views based on the views and data model
        viewHolder.textViewCount.setText(String.format(Locale.ENGLISH, "%d:  ", position + 1));

        if (timeEntry.getHours() == 0) {
            viewHolder.editTextHours.setText("");
        } else {
            viewHolder.editTextHours.setText(String.format(Locale.ENGLISH,"%d", timeEntry.getHours()));
        }
        if (timeEntry.getFocusSetting().equals("hours")) {
            viewHolder.editTextHours.requestFocus();
            viewHolder.editTextHours.setSelection(
                    viewHolder.editTextHours.getText() == null ? 0 : viewHolder.editTextHours.getText().length());
        } else {
            viewHolder.editTextHours.clearFocus();
        }

        if (timeEntry.getMinutes() == 0) {
            if (timeEntry.isMinutesSetToZero() && timeEntry.getHours() > 0) {
                viewHolder.editTextMinutes.setText(mContext.getString(R.string.dup_zero));
            } else {
                viewHolder.editTextMinutes.setText("");
            }
        } else {
            timeValue = Integer.toString(timeEntry.getMinutes());
            if (!timeEntry.getFocusSetting().equals("minutes") && timeValue.length() == 1) {
                timeValue = "0" + timeValue;
            }
            viewHolder.editTextMinutes.setText(timeValue);
        }
        if (timeEntry.getFocusSetting().equals("minutes")) {
            viewHolder.editTextMinutes.requestFocus();
            viewHolder.editTextMinutes.setSelection(
                    viewHolder.editTextMinutes.getText() == null ? 0 : viewHolder.editTextMinutes.getText().length());
        } else {
            viewHolder.editTextMinutes.clearFocus();
        }

        if (timeEntry.getSeconds() == 0) {
            if (timeEntry.isSecondsSetToZero() && (timeEntry.getHours() > 0 || timeEntry.getMinutes() > 0)) {
                viewHolder.editTextSeconds.setText(mContext.getString(R.string.dup_zero));
            } else {
                viewHolder.editTextSeconds.setText("");
            }
        } else {
            timeValue = Integer.toString(timeEntry.getSeconds());
            if (!timeEntry.getFocusSetting().equals("seconds") && timeValue.length() == 1) {
                timeValue = "0" + timeValue;
            }
            viewHolder.editTextSeconds.setText(timeValue);
        }
        if (timeEntry.getFocusSetting().equals("seconds")) {
            viewHolder.editTextSeconds.requestFocus();
            viewHolder.editTextSeconds.setSelection(
                    viewHolder.editTextSeconds.getText() == null ? 0 : viewHolder.editTextSeconds.getText().length());
        } else {
            viewHolder.editTextSeconds.clearFocus();
        }
    }
}
