package it.marcosoft.ticketwave.NetworkActivity;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import it.marcosoft.ticketwave.adapter.eventAdapter;
import it.marcosoft.ticketwave.EventModel.Event;

/**
 * Utility class for parsing JSON responses from the Ticketmaster API.
 */
public class JsonParser {
    // API key for accessing the Ticketmaster API
    private static final String API_KEY = "apikey=KqtxCDlnofSteZ63m7gmezFR8PR34o78";

    // Endpoint for the API request
    private final String root;

    // Query parameters for the API request
    private final List<String> queryParams;

    // List to store the parsed events
    private final List<Event> eventsList;

    // Android application context
    private final Context context;

    // RecyclerView to display the parsed events
    private final RecyclerView recyclerView;

    /**
     * Constructor for the JsonParser class.
     *
     * @param root          The root endpoint for the API request.
     * @param queryParams   List of query parameters for the API request.
     * @param context       Android application context.
     * @param recyclerView  RecyclerView to display the parsed events.
     */
    public JsonParser(String root, List<String> queryParams, Context context, RecyclerView recyclerView) {
        this.root = root;
        this.queryParams = queryParams;
        this.eventsList = new ArrayList<>();
        this.context = context;
        this.recyclerView = recyclerView;
    }

    /**
     * Creates a JsonObjectRequest for the API call.
     *
     * @return JsonObjectRequest for the API call.
     */
    public JsonObjectRequest jsonParse() {
        // Build the URL for the API request
        StringBuilder urlBuilder = new StringBuilder("https://app.ticketmaster.com/").append(root).append("?");
        for (String queryParam : queryParams) {
            urlBuilder.append(queryParam).append("&");
        }
        String url = urlBuilder.append(API_KEY).toString();
        Log.d("api", url);

        // Create a JsonObjectRequest for the API call

        return new JsonObjectRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    // Parse the JSON response based on the API endpoint
                    if ("discovery/v2/events".equals(root)) {
                        JSONObject embedded = response.getJSONObject("_embedded");
                        JSONArray events = embedded.getJSONArray("events");
                        for (int i = 0; i < events.length(); i++) {
                            JSONObject eventObj = events.getJSONObject(i);
                            Event event = new Event(eventObj);
                            eventsList.add(event);
                        }
                    } else if ("discovery/v2/events/G5djZ9E6rVE4K".equals(root)) {
                        Event event = new Event(response);
                        eventsList.add(event);
                    } else {
                        // Handle unknown API endpoint
                        throw new Exception();
                    }
                } catch (Exception e) {
                    // Handle the exception (e.g., log it)
                    Log.e("JsonParser", "Error parsing JSON", e);
                }

                // Update the RecyclerView with the parsed events
                recyclerView.setAdapter(new eventAdapter(context, eventsList));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle the Volley error (e.g., log it)
                Log.e("JsonParser", "Volley error", error);
            }
        });
    }
}
