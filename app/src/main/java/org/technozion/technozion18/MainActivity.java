package org.technozion.technozion18;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.yalantis.filter.adapter.FilterAdapter;
import com.yalantis.filter.listener.FilterListener;
import com.yalantis.filter.widget.Filter;
import com.yalantis.filter.widget.FilterItem;

import org.jetbrains.annotations.NotNull;
import org.technozion.technozion18.adapters.EventsRecyclerViewAdapter;
import org.technozion.technozion18.common.OnEntitiesReceivedListener;
import org.technozion.technozion18.models.Event;
import org.technozion.technozion18.presenters.EventPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements EditSettingsDialog.OnDataPass{

    public  static ArrayList<Event> events = new ArrayList<>();
    private SettingsData mSettings;
    public static EventsRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().hasExtra("settings")) {
            mSettings = (SettingsData) getIntent().getSerializableExtra("settings");
        }
        else {
            mSettings = new SettingsData();
        }

        RecyclerView eventsRecyclerView = findViewById(R.id.eventsRecyclerView);
        adapter = new EventsRecyclerViewAdapter(events, this);
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        eventsRecyclerView.setAdapter(adapter);

        new EventPresenter().getEvents(new OnEntitiesReceivedListener<Event>(this) {
            @Override
            public void onReceived(List<Event> entities) {
                events.addAll(entities);
                Log.d("MainActivity", events.size() + "");
                adapter.notifyDataSetChanged();
            }
        });
    }

   public void onChangeSettings(MenuItem item) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("settingsData", mSettings);
        bundle.putString("title", "Edit Settings");

        FragmentManager fm = getSupportFragmentManager();
        EditSettingsDialog editSettingsDialog = EditSettingsDialog.newInstance("Edit Settings");
        editSettingsDialog.setArguments(bundle);

        editSettingsDialog.show(fm, "fragment_edit_settings_dialog");
        //Log.d("changed","Yesssssssssssssss!");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDataPass(SettingsData data) {
     mSettings = data;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}

/*public class MainActivity extends AppCompatActivity implements FilterListener<Tag> {

    ArrayList<Event> events = new ArrayList<>();
    public Filter<Tag> mFilter;
    RecyclerView eventsRecyclerView;
    private String[] mTitles;
    private int[] mColors;
    EventsRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImagePipelineConfig config = ImagePipelineConfig
                .newBuilder(this)
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(this, config);
        mColors = getResources().getIntArray(R.array.colors);

        mTitles = getResources().getStringArray(R.array.job_titles);

        mFilter = (Filter<Tag>)findViewById(R.id.filter);
        mFilter.setAdapter(new MainActivity.Adapter(MainActivity.getTags(mTitles,mColors)));
        mFilter.setListener(MainActivity.this);

        mFilter.setNoSelectedItemText(getString(R.string.str_all_selected));
        mFilter.build();


       eventsRecyclerView = findViewById(R.id.eventsRecyclerView);
        adapter = new EventsRecyclerViewAdapter(events, this);
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        eventsRecyclerView.setAdapter(adapter);

        new EventPresenter().getEvents(new OnEntitiesReceivedListener<Event>(this) {
            @Override
            public void onReceived(List<Event> entities) {
                events.addAll(entities);
                Log.d("MainActivity", events.size() + "");
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void calculateDiff(final List<Event> oldList, final List<Event> newList) {
        DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return oldList.size();
            }

            @Override
            public int getNewListSize() {
                return newList.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
            }
        }).dispatchUpdatesTo(adapter);
    }

    private static List<Tag> getTags(String[] mTitles,int [] mColors) {
        List<Tag> tags = new ArrayList<>();

        for (int i = 0; i < mTitles.length; ++i) {
            tags.add(new Tag(mTitles[i], mColors[i]));
        }

        return tags;
    }

    @Override
    public void onNothingSelected() {
        if (eventsRecyclerView != null) {
            Log.d("Nothing","Heyyyy");
            adapter.setEvents(events);
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onFiltersSelected(@NotNull ArrayList<Tag> filters) {
        List<Event> newQuestions = findByTags(filters);
        List<Event> oldQuestions = adapter.getEvents();
        adapter.setEvents(newQuestions);
        calculateDiff(oldQuestions, newQuestions);
    }

    @Override
    public void onFilterSelected(Tag item) {
        if (item.getText().equals(mTitles[0])) {
            mFilter.deselectAll();
            mFilter.collapse();
        }
     Log.d("Selected","Yessssssssssss");
    }

    @Override
    public void onFilterDeselected(Tag item) {

    }
    private List<Event> findByTags(List<Tag> tags) {
        List<Event> questions = new ArrayList<>();

        for (Event question : events) {
            for (Tag tag : tags) {
                if (question.hasTag(tag.getText()) && !questions.contains(question)) {
                    questions.add(question);
                }
            }
        }

        return questions;
    }

    class Adapter extends FilterAdapter<Tag> {

        Adapter(@NotNull List<? extends Tag> items) {
            super(items);
        }

        @NotNull
        @Override
        public FilterItem createView(int position, Tag item) {
            FilterItem filterItem = new FilterItem(MainActivity.this);

            filterItem.setStrokeColor(mColors[0]);
            filterItem.setTextColor(mColors[0]);
            filterItem.setCornerRadius(14);
            filterItem.setCheckedTextColor(ContextCompat.getColor(MainActivity.this, android.R.color.white));
            filterItem.setColor(ContextCompat.getColor(MainActivity.this, android.R.color.white));
            filterItem.setCheckedColor(mColors[position]);
            filterItem.setText(item.getText());
            filterItem.deselect();

            return filterItem;
        }
    }

}*/