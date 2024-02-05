package it.marcosoft.ticketwave.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import it.marcosoft.ticketwave.R;
import it.marcosoft.ticketwave.data.repository.user.IUserRepository;
import it.marcosoft.ticketwave.data.source.user.BaseUserAuthenticationRemoteDataSource;
import it.marcosoft.ticketwave.databinding.ActivityMainBinding;
import it.marcosoft.ticketwave.ui.login.UserViewModel;
import it.marcosoft.ticketwave.ui.login.UserViewModelFactory;
import it.marcosoft.ticketwave.ui.login.WelcomeActivity;
import it.marcosoft.ticketwave.util.*;
import it.marcosoft.ticketwave.util.auth.SharedPreferencesUtil;
import it.marcosoft.ticketwave.util.auth.UserAuthenticationUtil;
import it.marcosoft.ticketwave.data.source.user.UserAuthenticationRemoteDataSource;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().
                findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.fragment_travels).build();
        */


        // Check and create preferences file if not exists
        SharedPreferencesUtil.checkAndCreatePreferencesFile(this);


        // Check the login status
        if ( SharedPreferencesUtil.getLoginStatus(this)) {


            //TODO rimuovi i due comandi qua sotto, servono finche' non c'e' un bottone di logout
            Log.d("S","DebugMSGxLogin, ricorda di rimuovere");
            SharedPreferencesUtil.setLoginStatus(this,false);

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
