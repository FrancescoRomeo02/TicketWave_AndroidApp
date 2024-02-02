package it.marcosoft.ticketwave.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import it.marcosoft.ticketwave.EventModel.Event;
import it.marcosoft.ticketwave.R;

public class eventAdapter extends RecyclerView.Adapter<eventAdapter.ViewHolder> {
    private final LayoutInflater layoutInflater;
    private final List<Event> eventList;

    public eventAdapter(Context context, List<Event> eventList){
        this.layoutInflater = LayoutInflater.from(context);
        this.eventList = eventList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.event_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = eventList.get(position).getName();
        String location = eventList.get(position).getVenue().getName();
        String date = eventList.get(position).getDate();
        String imgUrl = eventList.get(position).getImages().get(0).getUrlImage();

        holder.textTitle.setText(title);
        holder.textLocation.setText(location);
        holder.textDate.setText(date);
        Picasso.get().load(imgUrl).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textTitle, textLocation, textDate;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.event_name);
            textLocation = itemView.findViewById(R.id.event_description);
            textDate = itemView.findViewById((R.id.event_date));
            imageView = itemView.findViewById(R.id.event_image);
        }
    }
}
