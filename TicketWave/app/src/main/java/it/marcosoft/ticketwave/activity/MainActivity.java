package it.marcosoft.ticketwave.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import it.marcosoft.ticketwave.R;
import it.marcosoft.ticketwave.util.*;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check and create preferences file if not exists
        SharedPreferencesUtil.checkAndCreatePreferencesFile(this);

        // Check the login status
        if (UserAuthenticationUtil.getLoginStatus(this)) {
            // User is logged in, launch the main activity
            setContentView(R.layout.activity_main);
            setupMainScreen();
        } else {
            // User is not logged in, launch the login activity
            setContentView(R.layout.activity_welcome);
            // Add additional logic for the login activity if needed
        }
    }

    // Method to set up the main screen with BottomNavigationView
    private void setupMainScreen() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Load the default fragment (DiscoverFragment) when the activity is created
        loadFragment(new DiscoverFragment());

        // Set the "Discover" item as selected in the BottomNavigationView
        bottomNavigationView.setSelectedItemId(R.id.discover);

        // Set listener for BottomNavigationView item clicks
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            int itemId = item.getItemId();

            // Determine which fragment to load based on the selected item
            if (itemId == R.id.liked) {
                fragment = new LikedFragment();
                item.setTitle(getString(R.string.liked_label));
            } else if (itemId == R.id.discover) {
                fragment = new DiscoverFragment();
                item.setTitle(getString(R.string.discover_label));
            } else if (itemId == R.id.calendar) {
                fragment = new TravelFragment();
                item.setTitle(getString(R.string.travel_label));
            }

            // Load the selected fragment
            return loadFragment(fragment);
        });
    }

    // Method to load a fragment into the frame container
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
