package it.marcosoft.ticketwave.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import it.marcosoft.ticketwave.NetworkActivity.JsonParser;
import it.marcosoft.ticketwave.R;
import it.marcosoft.ticketwave.common.ApiConstants;

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ApiActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private JsonObjectRequest request;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Retrieve the layout ID from the intent
        int layoutId = getIntent().getIntExtra("layout_id", 0);

        // Set the content view to the specified layout
        if (layoutId != 0) {
            setContentView(layoutId);
        }

        // Call the superclass method after setting the content view
        super.onCreate(savedInstanceState);

        // Initialize the request queue and the RecyclerView
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        recyclerView = findViewById(R.id.recycle_main);

        // Call a method to handle the API call
        makeApiCall();

        // Additional initializations or specific logic for your activity
    }

    private void makeApiCall() {
        // Initialize a button for triggering the API call
        Button buttonParse = findViewById(R.id.button_parse);

        // Set a click listener for the button
        buttonParse.setOnClickListener(v -> {
            // Prepare parameters for the API call
            List<String> params = new ArrayList<>();
            params.add("city=barcelona");
            params.add("size=2");

            // Set up the RecyclerView with a LinearLayoutManager
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            // Create a JsonParser to handle the API call and parsing
            JsonParser jsonParser = new JsonParser(ApiConstants.DISCOVERY_EVENTS_ENDPOINT, params, getApplicationContext(), recyclerView);

            // Get the JsonObjectRequest from the JsonParser and add it to the request queue
            request = jsonParser.jsonParse();
            requestQueue.add(request);
        });
    }

    // Other methods if needed
}
