package it.marcosoft.ticketwave.util;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

        // Get the liked event dates
        List<String> likedEventDates = getLikedEventDates(likedDataList);

        // Pass the liked event dates to the CalendarView
        CalendarView calendarView = rootView.findViewById(R.id.calendar_liked);
        setCalendarEvents(calendarView, likedEventDates);

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

    // Metodo per ottenere le date degli eventi liked
    private List<String> getLikedEventDates(List<LikedData> likedDataList) {
        List<String> likedEventDates = new ArrayList<>();
        for (LikedData likedData : likedDataList) {
            // Assuming your LikedData class has a method to get the date
            likedEventDates.add(likedData.getEventDate());
        }
        return likedEventDates;
    }

    // Metodo per impostare gli eventi nel calendario
    private void setCalendarEvents(CalendarView calendarView, List<String> eventDates) {
        for (String date : eventDates) {
            // Convert the date to milliseconds and set it as an event in the calendar
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            try {
                Date parsedDate = sdf.parse(date);
                Log.d("calendar", parsedDate.toString());

                if (parsedDate != null) {
                    Log.d("calendar", calendarView.toString());
                    calendarView.setBackgroundColor(R.style.HighlightedDateStyle);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }


}
