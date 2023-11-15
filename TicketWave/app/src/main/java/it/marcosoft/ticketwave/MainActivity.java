package it.marcosoft.ticketwave;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import it.marcosoft.ticketwave.util.UserAuthenticationUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check the login status
        if (UserAuthenticationUtil.isUserLoggedIn(this)) {
            // User is logged in, launch the main activity
            setContentView(R.layout.activity_main);
        } else {
            // User is not logged in, launch the login activity
            setContentView(R.layout.activity_login);

        }

    }
}
