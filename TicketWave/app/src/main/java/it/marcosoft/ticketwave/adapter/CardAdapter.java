package it.marcosoft.ticketwave.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import it.marcosoft.ticketwave.data.CardData;
import it.marcosoft.ticketwave.R;
import it.marcosoft.ticketwave.util.DiscoverFragment;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private final DiscoverFragment discoverFragment;
    private CardData[] cardData;  // Declare this field

    public CardAdapter(Context context, DiscoverFragment discoverFragment) {
        this.discoverFragment = discoverFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.discover_card_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (cardData != null) {  // Check if cardData is not null
            final CardData currentCardData = cardData[position];
            holder.destination.setText(currentCardData.getDestination());
            holder.datesFrom.append(currentCardData.getDateFrom());
            holder.datesTo.append(currentCardData.getDateTo());
            holder.exploreButton.setTag(currentCardData.getId());

            holder.exploreButton.setOnClickListener((View v) -> {
                int cardId = Integer.parseInt((String) holder.exploreButton.getTag());
                discoverFragment.loadEventListMain(cardId);
            });
        }
    }

    @Override
    public int getItemCount() {
        return (cardData != null) ? cardData.length : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView destination;
        final TextView datesFrom;
        final TextView datesTo;
        final Button exploreButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            destination = itemView.findViewById(R.id.destination);
            datesFrom = itemView.findViewById(R.id.datesFrom);
            datesTo = itemView.findViewById(R.id.datesTo);
            exploreButton = itemView.findViewById(R.id.explore_button);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCardData(CardData[] cardData) {
        // Update the adapter's data and notify the change
        this.cardData = cardData;
        notifyDataSetChanged();
    }
}
