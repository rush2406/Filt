package org.technozion.technozion18.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.technozion.technozion18.R;
import org.technozion.technozion18.models.Event;

public class EventActionButtonsAdapter extends RecyclerView.Adapter<EventActionButtonsAdapter.ViewHolder>{

    int[] drawableId = {R.drawable.ic_call, R.drawable.ic_maps, R.drawable.ic_shared_colored, R.drawable.ic_notification_colored, R.drawable.ic_register};
    Context context;
    LayoutInflater inflater;
    Event event;

    public EventActionButtonsAdapter(Context context, Event event){
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.event = event;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.event_type_button, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(drawableId[position]).centerCrop().fit().into(holder.imageView);
        switch (position){
            case 1: openMaps(); return;
            case 0: dial(); return;
            case 2: share(); return;
            case 3: showNotifications(); return;
            case 4: register(); return;
        }
    }

    @Override
    public int getItemCount() {
        return drawableId.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    void openMaps(){

    }

    void dial(){

    }

    void share(){

    }

    void showNotifications(){

    }

    void register(){

    }
}
