package org.technozion.technozion18;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import org.technozion.technozion18.adapters.EventTypeButtonRecyclerViewAdapter;
import org.technozion.technozion18.adapters.EventsRecyclerViewAdapter;
import org.technozion.technozion18.adapters.EventsSmallRecyclerViewAdapter;
import org.technozion.technozion18.api_services.responses.ProfileResponse;
import org.technozion.technozion18.common.OnEntitiesReceivedListener;
import org.technozion.technozion18.common.OnEntityReceivedListener;
import org.technozion.technozion18.models.Event;
import org.technozion.technozion18.presenters.EventPresenter;
import org.technozion.technozion18.presenters.UserPresenter;
import org.technozion.technozion18.utils.Constants;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Homepage extends BaseActivity {

    RecyclerView spotlightRecyclerView, workshopsRecyclerView;
    ArrayList<Event> spotlights = new ArrayList<>(), workshops = new ArrayList<>();
    EventsSmallRecyclerViewAdapter spotlightEventsAdapter, workshopsAdapter;
    YouTubeThumbnailView youTubeThumbnailView;

    CardView spotlightsButton, guestLecturesButton, workshopsButton, initiativesButton, attractionsButton;

    CardView registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        setupRegisterButton();
        setUpSpotlights();
        setUpWorkshops();
        setUpYoutubeThumbnail();
        setupUserProfileDisplay();
        setupEventTypeButtons();
    }

    private void setUpWorkshops(){
        workshopsRecyclerView = findViewById(R.id.workshop);
        workshopsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        workshopsAdapter = new EventsSmallRecyclerViewAdapter(workshops, this);
        workshopsRecyclerView.setAdapter(workshopsAdapter);
        workshopsRecyclerView.setNestedScrollingEnabled(false);
        Map<String, String> filters = new HashMap<>();
        filters.put("type", "workshop");
        new EventPresenter().getEvents(filters, new OnEntitiesReceivedListener<Event>(this) {
            @Override
            public void onReceived(List<Event> entities) {
                workshops.addAll(entities);
                workshopsAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setUpSpotlights(){
        spotlightRecyclerView = findViewById(R.id.spotlight);
        spotlightRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        spotlightEventsAdapter = new EventsSmallRecyclerViewAdapter(spotlights, this);
        spotlightRecyclerView.setAdapter(spotlightEventsAdapter);
        spotlightRecyclerView.setNestedScrollingEnabled(false);
        Map<String, String> filters = new HashMap<>();
        filters.put("category", "spotlight");
        new EventPresenter().getEvents(filters, new OnEntitiesReceivedListener<Event>(this) {
            @Override
            public void onReceived(List<Event> entities) {
                spotlights.addAll(entities);
                spotlightEventsAdapter.notifyDataSetChanged();
            }
        });
        spotlightRecyclerView.setHasFixedSize(true);
        spotlightRecyclerView.setItemViewCacheSize(20);
        spotlightRecyclerView.setDrawingCacheEnabled(true);
    }

    private void setUpYoutubeThumbnail(){
        youTubeThumbnailView = findViewById(R.id.why_image);
        youTubeThumbnailView.initialize(Constants.DEVELOPER_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {

            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                //Toast.makeText(ctx,"failed",Toast.LENGTH_SHORT).show();
            }
        });
        youTubeThumbnailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= YouTubeStandalonePlayer.createVideoIntent(Homepage.this,Constants.DEVELOPER_KEY,Constants.YOUTUBE_TRAILER_ID);
                startActivity(intent);
            }
        });
    }

    private void setupRegisterButton(){
        registerButton = findViewById(R.id.registerButton);

        if(!MyApplication.getInstance().getPrefManager().isLoggedIn()){
            registerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Homepage.this, LoginActivity.class));
                }
            });
            return;
        }

        new UserPresenter().getCurrentUserProfile(new OnEntityReceivedListener<ProfileResponse>(this) {
            @Override
            public void onReceived(ProfileResponse entity) {
                if(entity.getProfile() == null){
                    registerButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(Homepage.this, UpdateProfileActivity.class));
                        }
                    });
                }  else if(entity.getProfile().getIs_registered() != null && entity.getProfile().getIs_registered())
                    registerButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showMessage("You're already registered for TZ'18");
                        }
                    });
                else {
                    registerButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // ToDo: open payments
                        }
                    });
                }

            }
        });
    }

    void setupUserProfileDisplay(){

        findViewById(R.id.viewProfileButton).setVisibility(View.GONE);

        if(MyApplication.getInstance().getPrefManager().isLoggedIn())
            new UserPresenter().getCurrentUserProfile(new OnEntityReceivedListener<ProfileResponse>(this) {
                @Override
                public void onReceived(ProfileResponse entity) {
                    if(entity.getProfile() != null) {
                        ((TextView) findViewById(R.id.userFirstName)).setText("Hey " + entity.getProfile().getUser().getFirst_name() + "!");
                        CardView viewProfile = findViewById(R.id.viewProfileButton);
                        findViewById(R.id.viewProfileButton).setVisibility(View.VISIBLE);

                        viewProfile.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(Homepage.this, ViewProfileActivity.class));
                            }
                        });
                    }
                    else
                        ((TextView)findViewById(R.id.userFirstName)).setText("Hey there!");
                }
            });
        else
            ((TextView)findViewById(R.id.userFirstName)).setText("Hey there!");

    }

    private void startEventsListActivity(String type){
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("eventType", type);
        startActivity(i);
    }

    void setupEventTypeButtons(){
       RecyclerView eventTypeRecyclerView = findViewById(R.id.eventTypeRecylerView);
       eventTypeRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
       eventTypeRecyclerView.setNestedScrollingEnabled(false);
       eventTypeRecyclerView.setAdapter(new EventTypeButtonRecyclerViewAdapter(this));
    }
}
