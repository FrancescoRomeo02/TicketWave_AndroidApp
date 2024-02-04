package it.marcosoft.ticketwave.util.auth;
// PreferencesUtil.java

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class SharedPreferencesUtil {

    private static final String PREF_NAME = "user_pref";
    private static final String KEY_LOGIN_STATUS = "login_status";

    private final Application application;
    public SharedPreferencesUtil(Application application) {
        this.application = application;
    }


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

    /**
     * Gets the login status from the preferences file.
     *
     * @param context The application context.
     * @return The login status.
     */

    public static boolean getLoginStatus(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(KEY_LOGIN_STATUS, false);
    }


    /**
     * Sets the login status to true after the user logs in.
     *
     * @param context The application context.
     */
    public static void setLoginStatus(Context context,boolean status) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_LOGIN_STATUS, status);
        editor.apply();
    }






}
