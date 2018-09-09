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