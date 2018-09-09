package org.technozion.technozion18.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.technozion.technozion18.EventLandingPage;
import org.technozion.technozion18.R;
import org.technozion.technozion18.models.Event;
import org.w3c.dom.Text;

import java.util.List;
import java.util.Random;

public class EventsSmallRecyclerViewAdapter extends RecyclerView.Adapter<EventsSmallRecyclerViewAdapter.EventViewHolder> {

    List<Event> events;
    Context context;
    LayoutInflater inflater;

    public EventsSmallRecyclerViewAdapter(List<Event> events, Context context){
        this.events = events;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventViewHolder(inflater.inflate(R.layout.event_card_small, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        final Event event = events.get(position);
        holder.eventNameTextView.setText(event.getName().toUpperCase());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEventLandingPage(event.getId());
            }
        });

        holder.eventDate.setText(event.getDay() == null ? "" : event.getDay());

        if(event.getIs_banner() == false)
            Picasso.get().load(R.drawable.back_image).fit().into(holder.eventImage);
        else
            Picasso.get().load(event.getBanner_image()).error(R.drawable.back_image).fit().into(holder.eventImage);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView eventNameTextView;
        TextView eventDate;
        ImageView eventImage;
        RelativeLayout translucentLayer;

        public EventViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview);
            eventNameTextView = itemView.findViewById(R.id.event_name);
            eventDate = itemView.findViewById(R.id.event_date);
            eventImage = itemView.findViewById(R.id.eventImage);
            translucentLayer = itemView.findViewById(R.id.translucentLayer);
            Random rnd = new Random();
            int color = Color.argb(125, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            translucentLayer.setBackgroundColor(color);
        }

    }

    private void showEventLandingPage(int id){
        Intent i = new Intent(context, EventLandingPage.class);
        i.putExtra("eventId", id);
        context.startActivity(i);
    }
}
