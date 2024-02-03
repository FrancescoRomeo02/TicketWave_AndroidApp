package it.marcosoft.ticketwave.util.auth;

import android.content.Context;
import android.content.SharedPreferences;

public class UserAuthenticationUtil {

    private static final String PREF_NAME = "user_pref";
    private static final String KEY_LOGIN_STATUS = "login_status";

    /**
     * Sets the login status in the preferences file.
     *
     * @param context    The application context.
     * @param isLoggedIn The login status to be set.
     */
    public static void setLoginStatus(Context context, boolean isLoggedIn) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_LOGIN_STATUS, isLoggedIn);
        editor.apply();
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

}
