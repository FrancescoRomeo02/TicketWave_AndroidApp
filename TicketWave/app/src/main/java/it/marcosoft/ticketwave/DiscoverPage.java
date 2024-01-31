package it.marcosoft.ticketwave;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import it.marcosoft.ticketwave.activity.ApiActivity;

public class DiscoverPage extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content view to the fragment_discover.xml layout
        setContentView(R.layout.fragment_discover);

        // Initialize the button and set its click listener
        button = findViewById(R.id.explore_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log a message when the button is pressed
                Log.d("button", "button pressed");

                // Call the method to load the main event list
                loadEventListMain();
            }
        });
    }

    // Method to load the main event list
    public void loadEventListMain() {
        // Create an Intent to start the ApiActivity
        Intent intent = new Intent(this, ApiActivity.class);

        // Start the ApiActivity with the specified intent
        startActivity(intent);
    }
}
