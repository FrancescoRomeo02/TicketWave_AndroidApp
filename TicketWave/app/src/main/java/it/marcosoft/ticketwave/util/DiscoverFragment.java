package it.marcosoft.ticketwave.util;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.marcosoft.ticketwave.adapter.CardAdapter;
import it.marcosoft.ticketwave.data.CardData;
import it.marcosoft.ticketwave.ui.main.ApiActivity;

import it.marcosoft.ticketwave.R;
import it.marcosoft.ticketwave.ui.main.MainActivity;
import it.marcosoft.ticketwave.util.db.DBHelper;
import it.marcosoft.ticketwave.viewmodel.CardViewModel;

public class DiscoverFragment extends Fragment {

    private CardViewModel cardViewModel;
    private CardAdapter cardAdapterRecyclerView;

    public DiscoverFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cardViewModel = new ViewModelProvider(this).get(CardViewModel.class);
        cardAdapterRecyclerView = new CardAdapter(getContext(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_travels, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(cardAdapterRecyclerView);

        // Observe changes in the ViewModel's data
        cardViewModel.getCardData().observe(getViewLifecycleOwner(), new Observer<CardData[]>() {
            @Override
            public void onChanged(CardData[] cardData) {
                // Update the RecyclerView adapter with new data
                cardAdapterRecyclerView.setCardData(cardData);
            }
        });

        // Get and set data from the database
        List<CardData> cardDataList = getTravelsDataFromDatabase();
        cardViewModel.setCardData(cardDataList.toArray(new CardData[0]));

        Button logoutBtn = rootView.findViewById(R.id.useraccountbutton);

        logoutBtn.setOnClickListener(v -> {
            ((MainActivity)getActivity()).goToUserPage();
        });


        // Return the inflated view for the fragment
        return rootView;
    }

    private List<CardData> getTravelsDataFromDatabase() {
        DBHelper dbHelper = new DBHelper(requireContext());
        List<CardData> cardDataList = dbHelper.getAllTravelData();
        dbHelper.close();
        return cardDataList;
    }

    private CardData getTravelsDataFromDatabase(int id) {
        DBHelper dbHelper = new DBHelper(requireContext());
        CardData cardData = dbHelper.getTravelDataById(id);
        dbHelper.close();
        return cardData;
    }

    public void loadEventListMain(int id) {
        CardData cardData = getTravelsDataFromDatabase(id);
        Intent intent = new Intent(getActivity(), ApiActivity.class);
        intent.putExtra("layout_id", R.layout.fragment_events_list);
        intent.putExtra("CardData", cardData);
        startActivity(intent);
    }
}

