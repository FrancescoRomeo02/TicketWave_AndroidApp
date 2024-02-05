package it.marcosoft.ticketwave.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import com.squareup.picasso.Picasso;

import java.util.List;

import it.marcosoft.ticketwave.R;
import it.marcosoft.ticketwave.data.LikedData;
import it.marcosoft.ticketwave.util.db.DBHelperLiked;

public class LikedAdapter extends RecyclerView.Adapter<LikedAdapter.ViewHolder> {
    // VARIABILI PER ANIMAZIONE LIKE
    ImageView heart;
    ImageView cover;
    AnimatedVectorDrawableCompat avd;
    AnimatedVectorDrawable avd2;

    // VARIABILI PER ANIMAZIONE DISLIKE
    ImageView disheart;
    AnimatedVectorDrawableCompat avd3;
    AnimatedVectorDrawable avd4;

    private final Context context;
    private final List<LikedData> likedDataList;

    public LikedAdapter(Context context, List<LikedData> likedDataList) {
        this.context = context;
        this.likedDataList = likedDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.event_list_item_card, null);
        return new ViewHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LikedData likedData = likedDataList.get(position);

        holder.textTitle.setText(likedData.getEventTitle());
        holder.textLocation.setText(likedData.getEventLocation());
        holder.textDate.setText(likedData.getEventDate());
        holder.textDescription.setText(likedData.getEventDescription());
        Picasso.get().load(likedData.getEventImageUrl()).into(holder.imageView);
        holder.tagCard.setTag(likedData.getEventId());

        // Set the click listener for the card view
        GestureDetector gestureDetector = new GestureDetector(holder.itemView.getContext(),
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onSingleTapConfirmed(@NonNull MotionEvent e) {
                        // Handle single tap (optional)
                        return super.onSingleTapConfirmed(e);
                    }

                    @Override
                    public boolean onDoubleTap(@NonNull MotionEvent e) {

                        //PREPARAZIONE PER ANIMAZIONE LIKE/DISLIKE
                        cover = holder.itemView.findViewById(R.id.event_image);
                        heart = holder.itemView.findViewById(R.id.like_animation);
                        disheart = holder.itemView.findViewById(R.id.dislike_animation);
                        final Drawable drawable=heart.getDrawable();
                        final Drawable drawable2=disheart.getDrawable();


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
                        // Evento già presente nel database
                        dbHelper = new DBHelperLiked(holder.itemView.getContext());
                        dbHelper.getWritableDatabase();

                        boolean isRemoved = dbHelper.removeLikedEvent(likedData.getEventId());

                        //ANIMAZIONE DEL DISLIKE
                        if (disheart != null) {
                            disheart.setAlpha(1f);
                        }
                        if (drawable2 instanceof AnimatedVectorDrawableCompat){
                            avd3 = (AnimatedVectorDrawableCompat) drawable2;
                            avd3.start();
                        } else if (drawable2 instanceof AnimatedVectorDrawable) {
                            avd4=(AnimatedVectorDrawable) drawable2;
                            avd4.start();
                        }

                        long delayMillis = 1000; // 1 seconds
                        new Handler().postDelayed(() -> {
                            // Remove the item from the list only after the delay
                            if (isRemoved) {
                                // Remove the item from the list
                                likedDataList.remove(likedData);
                                // Notify the adapter about the removal
                                notifyDataSetChanged();

                                Toast.makeText(holder.itemView.getContext(), "Disliked event!", Toast.LENGTH_SHORT).show();
                            } else {
                                // Handle the case where removal from the database failed
                                Toast.makeText(holder.itemView.getContext(), "Failed to dislike event!", Toast.LENGTH_SHORT).show();
                            }
                        }, delayMillis);

                        return true;
                    }
                });

        holder.itemView.setOnTouchListener((v, eventCard) -> gestureDetector.onTouchEvent(eventCard));

    }

    @Override
    public int getItemCount() {
        return likedDataList.size();
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
