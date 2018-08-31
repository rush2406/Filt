package org.technozion.technozion18.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        return new EventViewHolder(inflater.inflate(R.layout.card_event, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        final Event event = events.get(position);
        holder.eventNameTextView.setText(event.getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEventLandingPage(event.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView eventNameTextView;

        public EventViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            eventNameTextView = itemView.findViewById(R.id.eventName);
        }
    }

    private void showEventLandingPage(int id){
        Intent i = new Intent(context, EventLandingPage.class);
        i.putExtra("eventId", id);
        context.startActivity(i);
    }
}
