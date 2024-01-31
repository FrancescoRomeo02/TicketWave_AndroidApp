package it.marcosoft.ticketwave.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import it.marcosoft.ticketwave.R;

/**
 * Activity to allow user to login.
 */
public class WelcomeActivity extends AppCompatActivity {

    private static final String TAG = WelcomeActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }
}
