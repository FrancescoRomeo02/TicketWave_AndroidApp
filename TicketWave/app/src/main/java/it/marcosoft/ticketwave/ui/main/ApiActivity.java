package it.marcosoft.ticketwave.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import it.marcosoft.ticketwave.EventModel.Event;
import it.marcosoft.ticketwave.NetworkActivity.JsonParser;
import it.marcosoft.ticketwave.R;
import it.marcosoft.ticketwave.adapter.EventAdapter;
import it.marcosoft.ticketwave.util.Constants;
import it.marcosoft.ticketwave.data.CardData;
import it.marcosoft.ticketwave.util.DateUtil;
import it.marcosoft.ticketwave.viewmodel.EventViewModel;

import java.util.ArrayList;
import java.util.List;

public class ApiActivity extends AppCompatActivity implements JsonParser.OnEventsParsedListener {

    private RequestQueue requestQueue;
    private EventAdapter eventAdapter;
    private EventViewModel eventViewModel;
    private CardData cardData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_events_list); // Adjust to your layout

        // Retrieve CardData from the intent
        this.cardData = getIntent().getParcelableExtra("CardData");

        // Initialize the request queue and the RecyclerView
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        RecyclerView recyclerView = findViewById(R.id.recycle_main); // Adjust to your RecyclerView ID

        // Initialize EventViewModel
        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);

        // Set up the RecyclerView with a LinearLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        // Create EventAdapter and set it to the RecyclerView
        eventAdapter = new EventAdapter(this, new ArrayList<>()); // Adjust the constructor based on your EventAdapter
        recyclerView.setAdapter(eventAdapter);


        makeApiCall(cardData);

        // Set the back button
        Button backButton = findViewById(R.id.backbutton); // Adjust to your button ID
        backButton.setOnClickListener(v -> onBackPressed());

        populateUI();
    }

    // Handle the API call
    private void makeApiCall(CardData cardData) {
        // Prepare parameters for the API call
        List<String> params = new ArrayList<>();
        params.add("city=" + cardData.getDestination());
        String dateFrom = DateUtil.convertItalianToISO8601(cardData.getDateFrom());
        String dateTo = DateUtil.convertItalianToISO8601(cardData.getDateTo());
        params.add("startDateTime=" + dateFrom);
        params.add("endDateTime=" + dateTo);

        // Create a JsonParser to handle the API call and parsing
        JsonParser jsonParser = new JsonParser(Constants.DISCOVERY_EVENTS_ENDPOINT, params, this);

        // Get the JsonObjectRequest from the JsonParser and add it to the request queue
        JsonObjectRequest request = jsonParser.jsonParse();
        requestQueue.add(request);
    }

    @Override
    public void onEventsParsed(List<Event> events) {
        if (events.isEmpty()) {
            // Display a message indicating no events
            Toast.makeText(this, "No events found for the specified criteria", Toast.LENGTH_SHORT).show();
            // Additionally, you can update a TextView to display the message
            TextView msgTextView = findViewById(R.id.doubletap_text);
            msgTextView.setText(R.string.msgNotFound);
        }

        // Update the ViewModel with the parsed events
        eventViewModel.setEvents(events);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Observe the events from the ViewModel and update the RecyclerView
        eventViewModel.getEvents().observe(this, events -> {
            eventAdapter.setEventList(events);
        });
    }

    private void populateUI() {
        TextView eventNameTextView = findViewById(R.id.destination);
        TextView eventDateFromTextView = findViewById(R.id.datesFrom);
        TextView eventDateToTextView = findViewById(R.id.datesTo);

        eventNameTextView.setText(cardData.getDestination());
        eventDateFromTextView.setText(getString(R.string.from_placeholder, cardData.getDateFrom()));
        eventDateToTextView.setText(getString(R.string.to_placeholder,cardData.getDateTo()));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish(); // Finish the current activity (ApiActivity) to return to the previous fragment
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Handle Up button (Back button) by calling onBackPressed()
        return true;
    }
}
