package org.technozion.technozion18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.text.Html;
import android.widget.Toast;

import org.technozion.technozion18.common.OnEntityReceivedListener;
import org.technozion.technozion18.models.Event;
import org.technozion.technozion18.presenters.EventPresenter;
import org.technozion.technozion18.utils.ResizableCustomView;

public class EventLandingPage extends AppCompatActivity {

    Event event = null;
    int eventId = 0;
    final String TAG="EventLandingPage";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_landing_page);

        if(getIntent().getIntExtra("eventId", 0) != 0) {
            eventId = getIntent().getIntExtra("eventId", 0);
            new EventPresenter().getEvent(eventId, new OnEntityReceivedListener<Event>(this) {
                final ImageView event_image=findViewById(R.id.event_image);
              //  final TextView curved_data=findViewById(R.id.curve_shape_data);
              //  final TextView event_main_text=findViewById(R.id.event_main_text);
               // final TextView event_small_desc=findViewById(R.id.event_small_desc);
                final TextView calender_date=findViewById(R.id.calender_date);
                final TextView clock_data=findViewById(R.id.clock_data);
                final ImageView share_data=findViewById(R.id.share_data);
                final TextView description_data=findViewById(R.id.description_data);
                final TextView entry_charge=findViewById(R.id.entry_charge);
                final TextView address_text=findViewById(R.id.address_text);
                final TextView terms_text=findViewById(R.id.terms_text);
                final TextView textView = findViewById(R.id.textView);
                @Override
                public void onReceived(Event event) {
                    EventLandingPage.this.event = event;


                   // event_main_text.setText(event.getName());
                    description_data.setText(Html.fromHtml(event.getContents().get(0).getContent()).toString());
                    description_data.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(EventLandingPage.this, "click", Toast.LENGTH_SHORT).show();

                        }
                    });
                    //curved_data.setText((event.getEvent_type()));
                    calender_date.setText(event.getEvent_time());
                    //added textview , Max Lines(you want to show at normal),and view more label true so that you can expand
                   // ResizableCustomView.doResizeTextView(mResizableTextView, MAX_LINES, "View More", true);
                   // event_small_desc.setText(event.getAlias());
                    description_data.setEnabled(false);
                    makeTextViewResizable(description_data, 3, "View More", true);

                }



            });
        }
    }

    public static void makeTextViewResizable(TextView description_data, int i, String view_more, boolean b) {
    }
}
