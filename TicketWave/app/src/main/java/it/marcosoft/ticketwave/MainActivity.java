package it.marcosoft.ticketwave;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import it.marcosoft.ticketwave.NetworkActivity.JsonParser;
import it.marcosoft.ticketwave.common.ApiConstants;
import it.marcosoft.ticketwave.util.*;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /* stuff for API call */
    private RequestQueue requestQueue;
    private JsonObjectRequest request;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check and create preferences file if not exists
        SharedPreferencesUtil.checkAndCreatePreferencesFile(this);

        // Check the login status
        if (UserAuthenticationUtil.getLoginStatus(this) || true) {
            // User is logged in, launch the main activity
            setContentView(R.layout.popi_search_events);

            /*BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

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
            });*/

            setContentView(R.layout.popi_search_events);

            Button buttonParse = findViewById(R.id.button_parse);
            requestQueue = Volley.newRequestQueue(getApplicationContext());  // Use application context

            buttonParse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<String> params = new ArrayList<>();
                    params.add("city=barcelona");
                    params.add("size=2");

                    recyclerView = findViewById(R.id.recycle_main);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));  // Use application context
                    JsonParser jsonParser = new JsonParser(ApiConstants.DISCOVERY_EVENTS_ENDPOINT, params, getApplicationContext(), recyclerView);
                    request = jsonParser.jsonParse();
                    requestQueue.add(request);
                }
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
