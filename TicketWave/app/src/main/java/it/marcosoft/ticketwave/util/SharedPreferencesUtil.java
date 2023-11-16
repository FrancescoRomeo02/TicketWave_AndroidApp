package it.marcosoft.ticketwave.util;
// PreferencesUtil.java

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

    private static final String PREF_NAME = "user_pref";
    private static final String KEY_LOGIN_STATUS = "login_status";

    /**
     * Checks if the preferences file exists, and creates it with a default value if not.
     *
     * @param context The application context.
     */
    public static void checkAndCreatePreferencesFile(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // If the preference file doesn't exist, create it and set a default value (e.g., false)
        if (!preferences.contains(KEY_LOGIN_STATUS)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(KEY_LOGIN_STATUS, false);
            editor.apply();
        }
    }

}
