package org.technozion.technozion18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Filter;
import android.widget.Toast;

import org.technozion.technozion18.adapters.EventsRecyclerViewAdapter;
import org.technozion.technozion18.common.OnEntitiesReceivedListener;
import org.technozion.technozion18.models.Event;
import org.technozion.technozion18.presenters.EventPresenter;

import java.util.ArrayList;
import java.util.List;

public class SearchViewActivity extends AppCompatActivity {

    SearchView searchView;
    EventsRecyclerViewAdapter eventsAdapter;
    RecyclerView rView;
    ArrayList<Event> list;
    ArrayAdapter<String > adapter;
    ArrayList<Event>   contactList;
    ArrayList<Event> contactListFiltered;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        searchView =(SearchViewActivity) findViewById(R.id.searchView);
        rView = (RecyclerView) findViewById(R.id.lv1);
        list = new ArrayList<>();
        eventsAdapter=new EventsRecyclerViewAdapter(list,this);
        rView.setAdapter(eventsAdapter);
        rView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        final OnEntitiesReceivedListener<Event> eventsReceivedListener=new OnEntitiesReceivedListener<Event>(this) {
            @Override
            public void onReceived(List<Event> entities) {
                list.clear();
                list.addAll(entities);// added  all events to list variable;
                eventsAdapter.notifyDataSetChanged();//refresh
            }
        };

        final EventPresenter eventPresenter = new EventPresenter();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                eventPresenter.getEvents(eventsReceivedListener);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                eventPresenter.getEvents(eventsReceivedListener);
                return true;
            }
        });

    }
}
