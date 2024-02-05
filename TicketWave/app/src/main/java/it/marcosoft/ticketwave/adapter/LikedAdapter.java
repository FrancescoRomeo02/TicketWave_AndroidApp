package it.marcosoft.ticketwave.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import com.squareup.picasso.Picasso;

import java.util.List;

import it.marcosoft.ticketwave.R;
import it.marcosoft.ticketwave.data.LikedData;

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
