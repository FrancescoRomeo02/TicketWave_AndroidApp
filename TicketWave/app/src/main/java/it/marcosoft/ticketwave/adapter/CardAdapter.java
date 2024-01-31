package it.marcosoft.ticketwave.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import it.marcosoft.ticketwave.CardData;
import it.marcosoft.ticketwave.MainActivity;
import it.marcosoft.ticketwave.R;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{


    CardData[] cardData;
    Context context;
    OnDataAddedListener onDataAddedListener;

    public CardAdapter(CardData[] cardData, MainActivity activity){
        this.cardData = cardData;
        this.context = activity;

        

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_item_list,  parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CardData cardDataList = cardData[position];
        holder.destination.setText(cardDataList.getDestination());
        holder.datesFrom.setText((cardDataList.getDateFrom()));
        holder.datesTo.setText(cardDataList.getDateTo());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, cardDataList.getDestination(), Toast.LENGTH_SHORT).show();
                onDataAddedListener.onDataAdded(cardDataList);
            }
        });

    }

    @Override
    public int getItemCount() {

        return cardData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView destination;
        TextView datesFrom;

        TextView datesTo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            destination = itemView.findViewById(R.id.destination);
            datesFrom = itemView.findViewById(R.id.datesFrom);
            datesTo = itemView.findViewById(R.id.datesTo);

        }
    }
}
