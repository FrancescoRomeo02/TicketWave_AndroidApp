package it.marcosoft.ticketwave.util;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.marcosoft.ticketwave.R;
import it.marcosoft.ticketwave.adapter.LikedAdapter;
import it.marcosoft.ticketwave.data.LikedData;
import it.marcosoft.ticketwave.ui.main.ApiActivity;
import it.marcosoft.ticketwave.ui.main.MainActivity;
import it.marcosoft.ticketwave.util.db.DBHelperLiked;

public class LikedFragment extends Fragment {

    public LikedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recycle_main_liked);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Ottenere i dati dal database dei liked events
        List<LikedData> likedDataList = getLikedEventsDataFromDatabase();

        // Creare l'adapter e popolare l'RecyclerView
        LikedAdapter likedAdapter = new LikedAdapter(getContext(), likedDataList);
        recyclerView.setAdapter(likedAdapter);

        // Return the inflated view for the fragment
        return rootView;
    }

    // Metodo per ottenere i dati dal database dei liked events
    private List<LikedData> getLikedEventsDataFromDatabase() {
        DBHelperLiked dbHelperLiked = new DBHelperLiked(requireContext());
        List<LikedData> likedDataList = dbHelperLiked.getAllLikedEventsData();
        dbHelperLiked.close();
        return likedDataList;
    }
}
