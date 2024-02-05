package it.marcosoft.ticketwave.util;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import it.marcosoft.ticketwave.R;
import it.marcosoft.ticketwave.adapter.LikedAdapter;
import it.marcosoft.ticketwave.common.ConstantsVar;
import it.marcosoft.ticketwave.data.LikedData;
import it.marcosoft.ticketwave.common.ConstantsVar;
import it.marcosoft.ticketwave.ui.main.MainActivity;
import it.marcosoft.ticketwave.util.db.DBHelperLiked;




public class LikedFragment extends Fragment {

    String userID = "TODO";




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
        List<LikedData> likedDataList = getLikedEventsDataFromDatabase(userID);

        // Creare l'adapter e popolare l'RecyclerView
        LikedAdapter likedAdapter = new LikedAdapter(getContext(), likedDataList);
        recyclerView.setAdapter(likedAdapter);

        Button logoutBtn = rootView.findViewById(R.id.useraccountbutton2);

        logoutBtn.setOnClickListener(v -> {
            ((MainActivity)getActivity()).goToUserPage();
        });

        return rootView;
    }

    // Metodo per ottenere i dati dal database dei liked events
    private List<LikedData> getLikedEventsDataFromDatabase(String userID) {
        DBHelperLiked dbHelperLiked = new DBHelperLiked(requireContext());
        List<LikedData> likedDataList = dbHelperLiked.getAllLikedEventsData();
        dbHelperLiked.close();
        return likedDataList;
    }

    // Metodo per ottenere le date degli eventi liked
    private List<String> getLikedEventDates(List<LikedData> likedDataList) {
        List<String> likedEventDates = new ArrayList<>();
        for (LikedData likedData : likedDataList) {
            // Assuming your LikedData class has a method to get the date
            likedEventDates.add(likedData.getEventDate());
        }
        return likedEventDates;
    }




}
