package org.technozion.technozion18;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
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
            final String TAG="EventLandingPage";
            eventId = getIntent().getIntExtra("eventId", 0);
            final ImageView event_image=findViewById(R.id.event_image);
//            final TextView curved_data=findViewById(R.id.curve_shape_data);
//            final TextView event_main_text=findViewById(R.id.event_main_text);
//            final TextView event_small_desc=findViewById(R.id.event_small_desc);
            final TextView calender_date=findViewById(R.id.calender_date);
            final TextView clock_data=findViewById(R.id.clock_data);
            final ImageView share_data=findViewById(R.id.share_data);
//            final EditText description_data=findViewById(R.id.description_data);
//            final EditText entry_charge=findViewById(R.id.entry_charge);
//            final EditText address_text=findViewById(R.id.address_text);
//            final EditText terms_text=findViewById(R.id.terms_text);
            final TextView textView = findViewById(R.id.textView);
//            new EventPresenter().getEvent(eventId, new OnEntityReceivedListener<Event>(this) {
//                @Override
//                public void onReceived(Event event) {
//                    EventLandingPage.this.event = event;
//                    event_main_text.setText(event.getName());
//                    event_main_text.setTextColor(Color.WHITE);
//                    event_main_text.setBackgroundColor(getResources().getColor(R.color.translucentBlack));
//                    event_main_text.setTextSize(getResources().getDimension(R.dimen.textsize_event_main));
//                    curved_data.setText((event.getEvent_type()));
//                    calender_date.setText(event.getEvent_time());
//                    description_data.setText(event.getEvent_type());
//                    event_small_desc.setText(event.getAlias());
//                    event_small_desc.setTextColor(Color.WHITE);
//                    event_small_desc.setBackgroundColor(getResources().getColor(R.color.translucentBlack));
//                    event_small_desc.setTextSize(getResources().getDimension(R.dimen.textsize_small_desc));
//                    description_data.setEnabled(false);
//
//                }
//            });
        }
    }
}
