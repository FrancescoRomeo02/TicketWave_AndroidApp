package it.marcosoft.ticketwave;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;


import it.marcosoft.ticketwave.util.DiscoverFragment;
import it.marcosoft.ticketwave.util.LikedFragment;
import it.marcosoft.ticketwave.util.CalendarFragment;
import it.marcosoft.ticketwave.util.SharedPreferencesUtil;
import it.marcosoft.ticketwave.util.UserAuthenticationUtil;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check and create preferences file if not exists
        SharedPreferencesUtil.checkAndCreatePreferencesFile(this);

        // Check the login status
        if (UserAuthenticationUtil.getLoginStatus(this)) {
            // User is logged in, launch the main activity
            setContentView(R.layout.activity_main);

            bottomNavigationView = findViewById(R.id.bottomNavigationView);

            // Load the default fragment
            loadFragment(new LikedFragment());

            // Set listener for BottomNavigationView item clicks
            bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
                Fragment fragment;

                switch (item.getItemId()) {
                    case R.id.liked:
                        fragment = new LikedFragment();
                        break;
                    case R.id.discover:
                        // Usa DiscoverFragment come schermata principale
                        fragment = new DiscoverFragment();
                        break;
                    case R.id.calendar:
                        fragment = new CalendarFragment();
                        break;
                    default:
                        return false;
                }

                return loadFragment(fragment);
            });
        } else {
            // User is not logged in, launch the login activity
            setContentView(R.layout.activity_login);
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