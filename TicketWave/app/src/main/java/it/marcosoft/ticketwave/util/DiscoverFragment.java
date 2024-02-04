package it.marcosoft.ticketwave.util;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.marcosoft.ticketwave.adapter.CardAdapter;
import it.marcosoft.ticketwave.data.CardData;
import it.marcosoft.ticketwave.ui.main.ApiActivity;
import it.marcosoft.ticketwave.ui.main.MainActivity;
import it.marcosoft.ticketwave.R;
import it.marcosoft.ticketwave.util.db.DBHelper;

public class DiscoverFragment extends Fragment {

    // Default constructor for the DiscoverFragment
    public DiscoverFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_travels, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Ottenere i dati dal database
        List<CardData> cardDataList = getTravelsDataFromDatabase();

        // Creare l'adapter e popolare l'RecyclerView
        CardAdapter cardAdapterRecyclerView = new CardAdapter(cardDataList.toArray(new CardData[0]), (MainActivity) getActivity(), this);
        recyclerView.setAdapter(cardAdapterRecyclerView);


        // Return the inflated view for the fragment
        return rootView;
    }

    // Metodo per ottenere i dati dal database
    private List<CardData> getTravelsDataFromDatabase() {
        DBHelper dbHelper = new DBHelper(requireContext());
        List<CardData> cardDataList = dbHelper.getAllTravelData(); // Implementa questo metodo nel DBHelper
        dbHelper.close();
        return cardDataList;
    }

    private CardData getTravelsDataFromDatabase(int id) {
        DBHelper dbHelper = new DBHelper(requireContext());
        CardData cardData = dbHelper.getTravelDataById(id); // Implementa questo metodo nel DBHelper
        dbHelper.close();
        return cardData;
    }

    // Method to handle loading the main event list
    public void loadEventListMain(int id) {
        CardData cardData = getTravelsDataFromDatabase(id);
        // Create an Intent to start the ApiActivity
        Intent intent = new Intent(getActivity(), ApiActivity.class);

        // Pass the layout ID of events_list_main.xml as extra data
        intent.putExtra("layout_id", R.layout.fragment_events_list);
        intent.putExtra("CardData", cardData);
        // Start the ApiActivity with the specified intent
        startActivity(intent);
    }



}


