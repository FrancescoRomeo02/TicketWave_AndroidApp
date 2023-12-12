package it.marcosoft.ticketwave;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import it.marcosoft.ticketwave.util.SharedPreferencesUtil;
import it.marcosoft.ticketwave.util.UserAuthenticationUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check and create preferences file if not exists
        SharedPreferencesUtil.checkAndCreatePreferencesFile(this);

        // Check the login status
        if (UserAuthenticationUtil.getLoginStatus(this)) {
            // User is logged in, launch the main activity
            setContentView(R.layout.activity_main);
            // Add additional logic for the main activity if needed
        } else {
            // User is not logged in, launch the login activity
            setContentView(R.layout.activity_login);
            // Add additional logic for the login activity if needed
        }

    }
}
