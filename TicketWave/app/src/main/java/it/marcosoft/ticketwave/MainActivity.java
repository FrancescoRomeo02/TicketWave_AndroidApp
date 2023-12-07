package it.marcosoft.ticketwave;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.fragment.app.Fragment;


import it.marcosoft.ticketwave.util.DiscoverFragment;
import it.marcosoft.ticketwave.util.LikedFragment;
import it.marcosoft.ticketwave.util.CalendarFragment;
import it.marcosoft.ticketwave.util.SharedPreferencesUtil;
import it.marcosoft.ticketwave.util.UserAuthenticationUtil;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check and create preferences file if not exists
        SharedPreferencesUtil.checkAndCreatePreferencesFile(this);

        // Check the login status
        if (UserAuthenticationUtil.getLoginStatus(this) | true) {
            // User is logged in, launch the main activity
            setContentView(R.layout.activity_main);

            bottomNavigationView = findViewById(R.id.bottomNavigationView);


            // Load the default fragment
            loadFragment(new DiscoverFragment());
            bottomNavigationView.getMenu().findItem(R.id.discover).setTitle("");
            bottomNavigationView.setSelectedItemId(R.id.discover);


            // Set listener for BottomNavigationView item clicks
            bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
                Fragment fragment;

                int itemId = item.getItemId();


                if (itemId == R.id.liked) {
                    fragment = new LikedFragment();
                    item.setTitle("");
                    bottomNavigationView.getMenu().findItem(R.id.discover).setTitle("Discover");
                    bottomNavigationView.getMenu().findItem(R.id.calendar).setTitle("Calendar");
                   
                } else if (itemId == R.id.discover) {
                    item.setTitle("");
                    bottomNavigationView.getMenu().findItem(R.id.liked).setTitle("Liked");
                    bottomNavigationView.getMenu().findItem(R.id.calendar).setTitle("Calendar");
                    // Usa DiscoverFragment come schermata principale
                    fragment = new DiscoverFragment();
                } else if (itemId == R.id.calendar) {
                    fragment = new CalendarFragment();
                    item.setTitle("");
                    bottomNavigationView.getMenu().findItem(R.id.discover).setTitle("Discover");
                    bottomNavigationView.getMenu().findItem(R.id.liked).setTitle("Liked");
                } else {
                    return false;
                }


                return loadFragment(fragment);
            });
        } else {
            // User is not logged in, launch the login activity
            setContentView(R.layout.activity_register);
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