package it.marcosoft.ticketwave.ui.main;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import it.marcosoft.ticketwave.NetworkActivity.JsonParser;
import it.marcosoft.ticketwave.R;
import it.marcosoft.ticketwave.common.ApiConstants;
import it.marcosoft.ticketwave.data.CardData;
import it.marcosoft.ticketwave.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class ApiActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private JsonObjectRequest request;
    private RecyclerView recyclerView;
    private CardData cardData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Retrieve CardData from the intent
        this.cardData = getIntent().getParcelableExtra("CardData");

        // Call superclass method after setting CardData
        super.onCreate(savedInstanceState);

        // Retrieve the layout ID from the intent
        int layoutId = getIntent().getIntExtra("layout_id", 0);

        // Set the content view to the specified layout
        if (layoutId != 0) {
            setContentView(layoutId);

            // Populate UI elements with CardData
            populateUI();
        }

        // Initialize the request queue and the RecyclerView
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        recyclerView = findViewById(R.id.recycle_main);

        // Call a method to handle the API call
        makeApiCall(this.cardData);
    }

    // Populate UI elements with CardData
    private void populateUI() {
        TextView eventNameTextView = findViewById(R.id.destination);
        TextView eventDateFromTextView = findViewById(R.id.datesFrom);
        TextView eventDateToTextView = findViewById(R.id.datesTo);

        eventNameTextView.setText(cardData.getDestination());
        eventDateFromTextView.setText(getString(R.string.to_placeholder, cardData.getDateFrom()));
        eventDateToTextView.setText(getString(R.string.to_placeholder,cardData.getDateTo()));
    }

    // Handle the API call
    private void makeApiCall(CardData cardData) {
        // Initialize a button for triggering the API call
        Button buttonParse = findViewById(R.id.button_parse);

        // Set a click listener for the button
        buttonParse.setOnClickListener(v -> {
            // Prepare parameters for the API call
            List<String> params = new ArrayList<>();
            params.add("city=" + cardData.getDestination());
            String dateFrom = DateUtil.convertItalianToISO8601(cardData.getDateFrom());
            String dateTo = DateUtil.convertItalianToISO8601(cardData.getDateTo());
            params.add("startDateTime=" + dateFrom);
            params.add("endDateTime=" + dateTo);

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
