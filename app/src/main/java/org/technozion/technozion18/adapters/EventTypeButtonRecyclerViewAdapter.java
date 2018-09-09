package org.technozion.technozion18.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.technozion.technozion18.EventListActivity;
import org.technozion.technozion18.MainActivity;
import org.technozion.technozion18.R;

public class EventTypeButtonRecyclerViewAdapter extends RecyclerView.Adapter<EventTypeButtonRecyclerViewAdapter.EventTypeButtonViewHolder> {

    String[] eventType = {"event", "proshow", "spotlight", "lectures", "workshop", "initiative", "attractions"};
    String[] label = {"Events", "Proshows", "spotlights", "Guest Lectures", "Workshops", "Initiatives", "Attractions"};
    int[] drawableId = {R.drawable.ic_events, R.drawable.ic_concert, R.drawable.ic_spotlight, R.drawable.ic_guest_lecture, R.drawable.ic_workshops, R.drawable.ic_initiatives, R.drawable.ic_attractions };
    Context context;
    LayoutInflater inflater;

    public EventTypeButtonRecyclerViewAdapter(Context context){
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public EventTypeButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventTypeButtonViewHolder(inflater.inflate(R.layout.event_type_button, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventTypeButtonViewHolder holder, final int position) {
        Picasso.get().load(drawableId[position]).centerCrop().fit().into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, EventListActivity.class);
                i.putExtra("eventType", eventType[position]);
                i.putExtra("label", label[position]);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventType.length;
    }

    class EventTypeButtonViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView imageView;

        public EventTypeButtonViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
