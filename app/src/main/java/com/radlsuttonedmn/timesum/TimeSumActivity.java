package com.radlsuttonedmn.timesum;

import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageButton;
import android.view.View;
import java.util.ArrayList;


public class TimeSumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_sum_layout);

        // Add a tool bar to use as an action bar at the top of the screen
        final androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.open_title));
        }
        ImageButton buttonClearAll = findViewById(R.id.buttonClearAll);

        // Add a recyclerView in the activity layout
        final RecyclerView recyclerViewTimeEntries = findViewById(R.id.recyclerViewTimeEntries);
        DividerItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        if (ContextCompat.getDrawable(this, R.drawable.list_divider) != null) {

            // noinspection ConstantConditions
            itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.list_divider));
            recyclerViewTimeEntries.addItemDecoration(itemDecoration);
        }

        // Initialize the list of time entries
        final TimeSummary timeSum = new TimeSummary(this);
        timeSum.setAppTitle(toolbar);
        ArrayList<TimeEntry> timeEntries = timeSum.createTimeEntriesList();

        // Create adapter passing in the time entries data
        final TimeEntriesAdapter adapter = new TimeEntriesAdapter(this, timeEntries);

        // Attach the adapter to the recyclerView to populate items
        recyclerViewTimeEntries.setAdapter(adapter);

        // Set layout manager to position the items
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewTimeEntries.setLayoutManager(linearLayoutManager);

        // Add a scrollListener to enable endless scrolling
        final EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                addNewEntry(timeSum, adapter);
            }
        };

        recyclerViewTimeEntries.addOnScrollListener(scrollListener);

        // Respond to pressing the Clear All button
        buttonClearAll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               timeSum.clearAll();
               adapter.notifyDataSetChanged();
               scrollListener.resetState();
               if (toolbar != null) {
                   toolbar.setTitle(getString(R.string.open_title));
               }
               scrollToFocus(recyclerViewTimeEntries, timeSum);
            }
        });

        // Connect to the keyboard buttons
        Button buttonSeven = findViewById(R.id.button_7);
        buttonSeven.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                timeSum.update("7");
                adapter.notifyDataSetChanged();
                scrollToFocus(recyclerViewTimeEntries, timeSum);
            }
        });

        Button buttonEight = findViewById(R.id.button_8);
        buttonEight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                timeSum.update("8");
                adapter.notifyDataSetChanged();
                scrollToFocus(recyclerViewTimeEntries, timeSum);
            }
        });

        Button buttonNine = findViewById(R.id.button_9);
        buttonNine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                timeSum.update("9");
                adapter.notifyDataSetChanged();
                scrollToFocus(recyclerViewTimeEntries, timeSum);
            }
        });

        Button buttonFour = findViewById(R.id.button_4);
        buttonFour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                timeSum.update("4");
                adapter.notifyDataSetChanged();
                scrollToFocus(recyclerViewTimeEntries, timeSum);
            }
        });

        Button buttonFive = findViewById(R.id.button_5);
        buttonFive.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                timeSum.update("5");
                adapter.notifyDataSetChanged();
                scrollToFocus(recyclerViewTimeEntries, timeSum);
            }
        });

        Button buttonSix = findViewById(R.id.button_6);
        buttonSix.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                timeSum.update("6");
                adapter.notifyDataSetChanged();
                scrollToFocus(recyclerViewTimeEntries, timeSum);
            }
        });

        Button buttonOne = findViewById(R.id.button_1);
        buttonOne.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                timeSum.update("1");
                adapter.notifyDataSetChanged();
                scrollToFocus(recyclerViewTimeEntries, timeSum);
            }
        });

        Button buttonTwo = findViewById(R.id.button_2);
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                timeSum.update("2");
                adapter.notifyDataSetChanged();
                scrollToFocus(recyclerViewTimeEntries, timeSum);
            }
        });

        Button buttonThree = findViewById(R.id.button_3);
        buttonThree.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                timeSum.update("3");
                adapter.notifyDataSetChanged();
                scrollToFocus(recyclerViewTimeEntries, timeSum);
            }
        });

        Button buttonClear = findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                timeSum.update("clear");
                adapter.notifyDataSetChanged();
            }
        });

        Button buttonZero = findViewById(R.id.button_0);
        buttonZero.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                timeSum.update("0");
                adapter.notifyDataSetChanged();
                scrollToFocus(recyclerViewTimeEntries, timeSum);
            }
        });

        Button buttonEnter = findViewById(R.id.buttonEnter);
        buttonEnter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                timeSum.update("enter");
                adapter.notifyDataSetChanged();
                scrollToFocus(recyclerViewTimeEntries, timeSum);
            }
        });
    }

    private void scrollToFocus(RecyclerView recyclerViewTimeEntries, TimeSummary timeSum) {
        if (recyclerViewTimeEntries.getLayoutManager() != null) {
            recyclerViewTimeEntries.getLayoutManager().scrollToPosition(timeSum.getFocusPosition());
        }
    }

    private void addNewEntry(TimeSummary timeSum, TimeEntriesAdapter adapter) {

        timeSum.addNewEntry();
        adapter.notifyDataSetChanged();
    }
}
