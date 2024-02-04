package it.marcosoft.ticketwave.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import com.squareup.picasso.Picasso;

import java.util.List;

import it.marcosoft.ticketwave.R;
import it.marcosoft.ticketwave.data.LikedData;
import it.marcosoft.ticketwave.util.db.DBHelperLiked;

public class LikedAdapter extends RecyclerView.Adapter<LikedAdapter.ViewHolder> {

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

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LikedData likedData = likedDataList.get(position);

        holder.textTitle.setText(likedData.getEventTitle());
        holder.textLocation.setText(likedData.getEventLocation());
        holder.textDate.setText(likedData.getEventDate());
        holder.textDescription.setText(likedData.getEventDescription());
        Picasso.get().load(likedData.getEventImageUrl()).into(holder.imageView);

        holder.tagCard.setTag(likedData.getEventId());

        GestureDetector gestureDetector = new GestureDetector(context,
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onSingleTapConfirmed(MotionEvent e) {
                        // Handle single tap (optional)
                        return super.onSingleTapConfirmed(e);
                    }

                    @Override
                    public boolean onDoubleTap(MotionEvent e) {
                        String idEvent = String.valueOf(holder.tagCard.getTag());
                        String userId = "userId"; // TODO: id dell'utente dal db

                        DBHelperLiked dbHelper = new DBHelperLiked(context);
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

                        if (!isEventAlreadyLiked) {
                            dbHelper = new DBHelperLiked(context);
                            db = dbHelper.getWritableDatabase();

                            dbHelper.addLikedEvent(likedData);

                            db.close();

                            Toast.makeText(context, "Event added to liked list", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Event already added to liked list", Toast.LENGTH_SHORT).show();
                        }

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
