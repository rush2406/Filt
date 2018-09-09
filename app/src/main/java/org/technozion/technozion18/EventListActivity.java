package org.technozion.technozion18;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.technozion.technozion18.adapters.EventsRecyclerViewAdapter;
import org.technozion.technozion18.common.OnEntitiesReceivedListener;
import org.technozion.technozion18.models.Event;
import org.technozion.technozion18.presenters.EventPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventListActivity extends BaseActivity {

    public static EventsRecyclerViewAdapter adapter;
    public  static ArrayList<Event> events = new ArrayList<>();
    private List<String> types, categories, departments, days;
    private Toolbar toolbar;
    private static final String TAG = EventListActivity.class.getSimpleName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        setTitle("Events");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setupFilterLists();
        initializeIntentFiltering();
        setupEventsRecyclerView();

    }

    private void setupFilterLists(){
        types = new ArrayList<>();
        categories = new ArrayList<>();
        departments = new ArrayList<>();
        days = new ArrayList<>();
    }

    private void initializeIntentFiltering(){
        Bundle bundle = getIntent().getExtras();
        if(bundle.containsKey("eventType")){
            String eventType = bundle.getString("eventType");
            String label = bundle.getString("label");
            if(!eventType.equals("spotlight"))
                types.add(eventType);
            else
                categories.add(eventType);
            toolbar.setTitle(label);
            setTitle(label);
        }
    }

    private void setupEventsRecyclerView(){
        RecyclerView eventsRecyclerView = findViewById(R.id.eventsRecyclerView);
        adapter = new EventsRecyclerViewAdapter(events, this);
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        eventsRecyclerView.setAdapter(adapter);
        events.clear();

        new EventPresenter().getEvents(types, categories, departments, days, new OnEntitiesReceivedListener<Event>(this) {
            @Override
            public void onReceived(List<Event> entities) {
                events.addAll(entities);
                Log.d("EventsListActivity", events.size() + "");
                if(events.size() == 0)
                    findViewById(R.id.noItemsMessage).setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();
            }
        });
    }

}
