package it.marcosoft.ticketwave.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import it.marcosoft.ticketwave.EventModel.Event;
import it.marcosoft.ticketwave.R;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private final LayoutInflater layoutInflater;
    private final List<Event> eventList;

    public EventAdapter(Context context, List<Event> eventList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.event_list_item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event event = eventList.get(position);

        // Retrieve data from the event object
        String title = event.getName();
        String location = event.getVenue().getName();
        String date = event.getDate();
        String description = event.getClassifications().get(0).toStringPretty();
        String imgUrl = event.getImages().get(0).getUrlImage();
        String id = event.getId();

        // Bind data to the ViewHolder
        holder.textTitle.setText(title);
        holder.textLocation.setText(location);
        holder.textDate.setText(date);
        holder.textDescription.setText(description);
        holder.tagCard.setTag(id);
        Picasso.get().load(imgUrl).into(holder.imageView);

        // Set the click listener for the card view
        GestureDetector gestureDetector = new GestureDetector(holder.itemView.getContext(),
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onSingleTapConfirmed(MotionEvent e) {
                        // Handle single tap (optional)
                        return super.onSingleTapConfirmed(e);
                    }

                    @Override
                    public boolean onDoubleTap(MotionEvent e) {
                        // Handle double tap
                        // TODO: mettere l'evento nel database e fare in modod che si possa recuperare nella sezione LIKED
                        // TODO: mettere un qualche tipo di animazione sul doppio like e far capire all'utente che può fare questa cosa con un messaggio
                        String idEvent = String.valueOf(holder.tagCard.getTag());
                        Log.d("card", "like!");

                        return true;
                    }
                });

        holder.itemView.setOnTouchListener((v, eventCard) -> gestureDetector.onTouchEvent(eventCard));

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView textTitle;
        final TextView textLocation;
        final TextView textDate;
        final TextView textDescription;
        final ImageView imageView;
        final LinearLayout tagCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.event_name);
            textLocation = itemView.findViewById(R.id.event_location);
            textDate = itemView.findViewById(R.id.event_date);
            imageView = itemView.findViewById(R.id.event_image);
            textDescription = itemView.findViewById(R.id.event_description);
            tagCard = itemView.findViewById(R.id.cardId);
        }
    }
}
