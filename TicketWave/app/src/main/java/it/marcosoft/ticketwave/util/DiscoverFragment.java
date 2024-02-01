package it.marcosoft.ticketwave.util;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.marcosoft.ticketwave.adapter.CardAdapter;
import it.marcosoft.ticketwave.CardData;
import it.marcosoft.ticketwave.MainActivity;
import it.marcosoft.ticketwave.R;
import it.marcosoft.ticketwave.adapter.OnDataAddedListener;

public class DiscoverFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_discover, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Ottenere i dati dal database
        List<CardData> cardDataList = getTravelDataFromDatabase();

        // Creare l'adapter e popolare l'RecyclerView
        CardAdapter cardAdapter = new CardAdapter(cardDataList.toArray(new CardData[0]), (MainActivity) getActivity());
        recyclerView.setAdapter(cardAdapter);

        return rootView;
    }

    // Metodo per ottenere i dati dal database
    private List<CardData> getTravelDataFromDatabase() {
        DBHelper dbHelper = new DBHelper(requireContext());
        List<CardData> cardDataList = dbHelper.getAllTravelData(); // Implementa questo metodo nel DBHelper
        dbHelper.close();
        return cardDataList;
    }
}
