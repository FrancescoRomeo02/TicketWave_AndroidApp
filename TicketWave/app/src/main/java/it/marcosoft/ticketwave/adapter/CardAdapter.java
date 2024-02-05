package it.marcosoft.ticketwave.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import it.marcosoft.ticketwave.data.CardData;
import it.marcosoft.ticketwave.R;
import it.marcosoft.ticketwave.util.DiscoverFragment;
import it.marcosoft.ticketwave.viewmodel.CardViewModel;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private final Context context;
    private final DiscoverFragment discoverFragment;
    private final CardViewModel cardViewModel;

    public CardAdapter(Context context, DiscoverFragment discoverFragment, CardViewModel cardViewModel) {
        this.context = context;
        this.discoverFragment = discoverFragment;
        this.cardViewModel = cardViewModel;
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
        if (cardViewModel.getCardData().getValue() != null) {
            final CardData currentCardData = cardViewModel.getCardData().getValue()[position];
            holder.destination.setText(currentCardData.getDestination());
            holder.datesFrom.append(currentCardData.getDateFrom());
            holder.datesTo.append(currentCardData.getDateTo());
            holder.exploreButton.setTag(currentCardData.getId());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, currentCardData.getDestination(), Toast.LENGTH_SHORT).show();
                }
            });
            holder.exploreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int cardId = Integer.parseInt((String) holder.exploreButton.getTag());
                    discoverFragment.loadEventListMain(cardId);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (cardViewModel.getCardData().getValue() != null) {
            return cardViewModel.getCardData().getValue().length;
        } else {
            return 0;
        }
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
}
