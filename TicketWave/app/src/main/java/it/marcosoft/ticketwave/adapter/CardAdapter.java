package it.marcosoft.ticketwave.adapter;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.apache.commons.lang3.ArrayUtils;

import it.marcosoft.ticketwave.data.CardData;
import it.marcosoft.ticketwave.R;
import it.marcosoft.ticketwave.util.DiscoverFragment;
import it.marcosoft.ticketwave.util.db.DBHelper;

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

    @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (cardData != null) {  // Check if cardData is not null
            final CardData currentCardData = cardData[position];
            holder.destination.setText(currentCardData.getDestination());
            holder.datesFrom.setText(holder.itemView.getContext().getString(R.string.from) + " " + currentCardData.getDateFrom());
            holder.datesTo.setText(holder.itemView.getContext().getString(R.string.to) + currentCardData.getDateTo());
            holder.exploreButton.setTag(currentCardData.getId());

            holder.exploreButton.setOnClickListener((View v) -> {
                int cardId = Integer.parseInt((String) holder.exploreButton.getTag());
                discoverFragment.loadEventListMain(cardId);
            });

            holder.dellButton.setOnClickListener((View v) -> {
                int cardId = Integer.parseInt((String) holder.exploreButton.getTag());
                DBHelper dbHelper = new DBHelper(v.getContext());
                // Find the index of the card with the matching ID
                int indexToRemove = -1;
                for (int i = 0; i < cardData.length; i++) {
                    if (Integer.parseInt(cardData[i].getId()) == cardId) {
                        indexToRemove = i;
                        break;
                    }
                }

                // Remove the card from the list if found
                if (indexToRemove != -1) {
                    // Assuming cardData is an array, if it's a list, use cardData.remove(indexToRemove);
                    cardData = ArrayUtils.remove(cardData, indexToRemove);

                    // Notify the adapter that the data set has changed
                    notifyDataSetChanged();

                    // Now you can delete the card from the database if needed
                    dbHelper.deleteCard(cardId);
                }
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
        final Button dellButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            destination = itemView.findViewById(R.id.destination);
            datesFrom = itemView.findViewById(R.id.datesFrom);
            datesTo = itemView.findViewById(R.id.datesTo);
            exploreButton = itemView.findViewById(R.id.explore_button);
            dellButton = itemView.findViewById(R.id.dell_button);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCardData(CardData[] cardData) {
        // Update the adapter's data and notify the change
        this.cardData = cardData;
        notifyDataSetChanged();
    }
}
