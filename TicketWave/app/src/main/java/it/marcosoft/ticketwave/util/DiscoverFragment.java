package it.marcosoft.ticketwave.util;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import it.marcosoft.ticketwave.adapter.CardAdapter;
import it.marcosoft.ticketwave.CardData;
import it.marcosoft.ticketwave.MainActivity;
import it.marcosoft.ticketwave.R;
import it.marcosoft.ticketwave.adapter.OnDataAddedListener;

public class DiscoverFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_discover, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        CardData[] cardData = new CardData[]{
                new CardData("Madrid", "26 gennaio", "13 febbraio"),
                new CardData("Madrid", "26 gennaio", "13 febbraio"),
                new CardData("Madrid", "26 gennaio", "13 febbraio"),
        };

        CardAdapter cardAdapter = new CardAdapter(cardData, (MainActivity) getActivity());
        recyclerView.setAdapter(cardAdapter);



        return rootView;
    }



}
