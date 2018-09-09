package org.technozion.technozion18.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.technozion.technozion18.EventLandingPage;
import org.technozion.technozion18.R;
import org.technozion.technozion18.models.Event;

import java.util.List;

public class EventsRecyclerViewAdapter extends RecyclerView.Adapter<EventsRecyclerViewAdapter.EventViewHolder> {

    List<Event> events;
    Context context;
    LayoutInflater inflater;

    public void setEvents(List<Event> e)
    {
        this.events=e;
    }

    public List<Event> getEvents()
    {
        return events;
    }
    public EventsRecyclerViewAdapter(List<Event> events, Context context){
        this.events = events;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventViewHolder(inflater.inflate(R.layout.event_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        final Event event = events.get(position);

        if(event.getBanner_image() != null)
            Picasso.get().load(event.getBanner_image()).centerCrop().fit().into(holder.eventImage);

        holder.eventNameTextView.setText(event.getName());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEventLandingPage(event.getId());
            }
        });

        holder.eventDateText.setText(getDateFromDay(event.getDay()));

        if(event.getVenue() != null)
            holder.eventVenue.setText(event.getVenue().getName());
        else holder.eventVenue.setText("TBD");

        if(event.getCategory1() != null && event.getCategory2() != null)
            holder.eventCategory.setText(event.getCategory1() + " | " + event.getCategory2());
        else if(event.getCategory1() != null)
            holder.eventCategory.setText(event.getCategory1());
        else if(event.getCategory2() != null)
            holder.eventCategory.setText(event.getCategory2());
        else
            holder.eventCategory.setText("N/A");

        if(event.getEvent_type() == null)
            holder.eventType.setText("N/A");
        else
            holder.eventType.setText(event.getEvent_type());

        setContact(holder, event);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder{
        CardView cardView, contactButton;
        TextView eventNameTextView;
        ImageView eventDate;
        TextView eventDateText;
        ImageView eventImage;
        TextView eventCategory;
        TextView eventVenue;
        TextView eventVenueMore;
        TextView eventType;

        public EventViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview);
            contactButton = itemView.findViewById(R.id.contactButton);
            eventNameTextView = itemView.findViewById(R.id.event_name);
            eventDate = itemView.findViewById(R.id.event_date);
            eventDateText=itemView.findViewById(R.id.event_date_text);
            eventImage=itemView.findViewById(R.id.eventImage);
            eventCategory =itemView.findViewById(R.id.event_category);
            eventVenue=itemView.findViewById(R.id.event_venue);
            eventVenueMore=itemView.findViewById(R.id.event_venue_more);
            eventType=itemView.findViewById(R.id.event_type);
        }

    }

    private void showEventLandingPage(int id){
        Intent i = new Intent(context, EventLandingPage.class);
        i.putExtra("eventId", id);
        context.startActivity(i);
    }

    private String getDateFromDay(String day){
        if(day == null){
            return "TBD";
        } else if(day.compareToIgnoreCase("day 1") == 0){
            return "28th Sep";
        } else if(day.compareToIgnoreCase("day 2") == 0){
            return "29th Sep";
        } else if(day.compareToIgnoreCase("day 3") == 0){
            return "30th Sep";
        } else return day;
    }

    private String getTimeText(String time){
        if(time == null)
            return "";
        else return time;
    }

    private void setContact(EventViewHolder holder, final Event event){
        if(event.getContact_members().size() == 0)
            holder.contactButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "No EVM contact details are available for this event. Dialing TZ Events Coordinator.", Toast.LENGTH_SHORT).show();
                    dial("9999999999");
                }
            });
        else {
            holder.contactButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = 0;
                    while(
                            i < event.getContact_members().size() &&
                            (event.getContact_members().get(i).getPhone_no() == null || event.getContact_members().get(i).getName() == null) ) i++;

                    if(i == event.getContact_members().size()){
                        Toast.makeText(context, "No EVM contact details are available for this event. Dialing TZ Events Coordinator.", Toast.LENGTH_SHORT).show();
                        dial("9999999999");
                        return;
                    }

                    Toast.makeText(context, "Dialing " + event.getContact_members().get(i).getName(), Toast.LENGTH_SHORT);
                    dial(event.getContact_members().get(i).getPhone_no());
                }
            });
        }
    }

    private void dial(String mobile){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + mobile));
        context.startActivity(intent);
    }
}
