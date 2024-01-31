package it.marcosoft.ticketwave.util;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import it.marcosoft.ticketwave.R;
import it.marcosoft.ticketwave.activity.ApiActivity;

public class DiscoverFragment extends Fragment {

    // Default constructor for the DiscoverFragment
    public DiscoverFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discover, container, false);

        // Initialize the "Explore" button and set its click listener
        Button exploreButton = view.findViewById(R.id.explore_button);
        exploreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the button click within the DiscoverFragment
                loadEventListMain();
            }
        });

        // Return the inflated view for the fragment
        return view;
    }

    // Method to handle loading the main event list
    private void loadEventListMain() {
        // Create an Intent to start the ApiActivity
        Intent intent = new Intent(getActivity(), ApiActivity.class);

        // Pass the layout ID of events_list_main.xml as extra data
        intent.putExtra("layout_id", R.layout.events_list_main);

        // Start the ApiActivity with the specified intent
        startActivity(intent);
    }

    // Interface to handle the "Explore" button click event
    public interface OnExploreButtonClickListener {
        void onExploreButtonClick(String data);
    }
}
