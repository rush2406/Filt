package org.technozion.technozion18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.technozion.technozion18.adapters.EventsRecyclerViewAdapter;
import org.technozion.technozion18.common.OnEntitiesReceivedListener;
import org.technozion.technozion18.models.Event;
import org.technozion.technozion18.presenters.EventPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Event> events = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView eventsRecyclerView = findViewById(R.id.eventsRecyclerView);
        final EventsRecyclerViewAdapter adapter = new EventsRecyclerViewAdapter(events, this);
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
}
