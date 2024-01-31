package it.marcosoft.ticketwave;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import it.marcosoft.ticketwave.util.*;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check and create preferences file if not exists
        SharedPreferencesUtil.checkAndCreatePreferencesFile(this);

        // Check the login status
        if (UserAuthenticationUtil.getLoginStatus(this) || true) {
            // User is logged in, launch the main activity
            setContentView(R.layout.activity_main);

            BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

            // Load the default fragment
            loadFragment(new DiscoverFragment());

            bottomNavigationView.setSelectedItemId(R.id.discover);

            // Set listener for BottomNavigationView item clicks
            bottomNavigationView.setOnItemSelectedListener(item -> {
                Fragment fragment = null;
                int itemId = item.getItemId();

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

                return loadFragment(fragment);
            });
        } else {
            // User is not logged in, launch the login activity
           // setContentView(R.layout.activity_register);
            // Add additional logic for the login activity if needed
        }
    }

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
