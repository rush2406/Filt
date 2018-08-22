package org.technozion.technozion18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.technozion.technozion18.common.OnEntityReceivedListener;
import org.technozion.technozion18.models.Event;
import org.technozion.technozion18.presenters.EventPresenter;

public class EventLandingPage extends AppCompatActivity {

    Event event = null;
    int eventId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_landing_page);

        if(getIntent().getIntExtra("eventId", 0) != 0) {
            eventId = getIntent().getIntExtra("eventId", 0);
            final TextView textView = findViewById(R.id.textView);
            new EventPresenter().getEvent(eventId, new OnEntityReceivedListener<Event>(this) {
                @Override
                public void onReceived(Event event) {
                    EventLandingPage.this.event = event;
                    textView.setText(event.getName());
                }
            });
        }
    }
}
