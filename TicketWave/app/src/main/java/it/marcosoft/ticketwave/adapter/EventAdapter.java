package it.marcosoft.ticketwave.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import it.marcosoft.ticketwave.EventModel.Event;
import it.marcosoft.ticketwave.R;
import it.marcosoft.ticketwave.data.LikedData;
import it.marcosoft.ticketwave.util.db.DBHelperLiked;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private final LayoutInflater layoutInflater;
    private List<Event> eventList;

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
                        String idEvent = String.valueOf(holder.tagCard.getTag());
                        String userId = "userId"; // Sostituisci "userId" con l'id dell'utente reale

                        // Verifica se l'evento è già presente nel database
                        DBHelperLiked dbHelper = new DBHelperLiked(holder.itemView.getContext());
                        SQLiteDatabase db = dbHelper.getReadableDatabase();

                        String selection = DBHelperLiked.COLUMN_EVENT_ID + " = ? AND " + DBHelperLiked.COLUMN_USER_ID + " = ?";
                        String[] selectionArgs = {idEvent, userId};

                        Cursor cursor = db.query(
                                DBHelperLiked.TABLE_LIKED_EVENTS,
                                null,
                                selection,
                                selectionArgs,
                                null,
                                null,
                                null
                        );

                        boolean isEventAlreadyLiked = cursor.getCount() > 0;
                        cursor.close();
                        db.close();

                        // Aggiungi l'evento al database solo se non è già presente
                        if (!isEventAlreadyLiked) {
                            dbHelper = new DBHelperLiked(holder.itemView.getContext());
                            db = dbHelper.getWritableDatabase();

                            // Utilizza la nuova classe LikedData estesa
                            LikedData likedData = new LikedData(
                                    idEvent,
                                    userId,
                                    title,
                                    location,
                                    date,
                                    description,
                                    imgUrl
                            );

                            dbHelper.addLikedEvent(likedData);

                            db.close();

                            Log.d("card", "like!");
                            Toast.makeText(holder.itemView.getContext(), "Event added to liked list", Toast.LENGTH_SHORT).show();
                        } else {
                            // Evento già presente nel database
                            Log.d("card", "Event already liked!");
                            Toast.makeText(holder.itemView.getContext(), "Event already added to liked list", Toast.LENGTH_SHORT).show();
                        }

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

    public void setEventList(List<Event> events) {
        this.eventList = events;
        notifyDataSetChanged();
    }
}
