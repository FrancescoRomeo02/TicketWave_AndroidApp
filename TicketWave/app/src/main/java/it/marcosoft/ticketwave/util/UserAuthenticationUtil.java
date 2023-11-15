package it.marcosoft.ticketwave.util;

import android.content.Context;
import android.content.SharedPreferences;

public class UserAuthenticationUtil {

    private static final String PREF_NAME = "user_login_pref";
    private static final String KEY_LOGIN_STATUS = "login_status";

    public static void setUserLoggedIn(Context context, boolean isLoggedIn) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_LOGIN_STATUS, isLoggedIn);
        editor.apply();
    }

    public static boolean isUserLoggedIn(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(KEY_LOGIN_STATUS, false);
    }
}
