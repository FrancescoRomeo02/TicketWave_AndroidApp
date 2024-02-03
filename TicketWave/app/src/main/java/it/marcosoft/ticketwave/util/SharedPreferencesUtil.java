package it.marcosoft.ticketwave.util;
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
     * Writes a String value using SharedPreferences API.
     * @param sharedPreferencesFileName The name of file where to write data.
     * @param key The key associated with the value to write.
     * @param value The value to write associated with the key.
     */
    public void writeStringData(String sharedPreferencesFileName, String key, String value) {
        SharedPreferences sharedPref = application.getSharedPreferences(sharedPreferencesFileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Writes a boolean value using SharedPreferences API.
     * @param sharedPreferencesFileName The name of file where to write data.
     * @param key The key associated with the value to write.
     * @param value The value to write associated with the key.
     */
    public void writeBooleanData(String sharedPreferencesFileName, String key, boolean value) {
        SharedPreferences sharedPref = application.getSharedPreferences(sharedPreferencesFileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * Writes a set of String values using SharedPreferences API.
     * @param sharedPreferencesFileName The name of file where to write data.
     * @param key The key associated with the value to write.
     * @param value The value to write associated with the key.
     */
    public void writeStringSetData(String sharedPreferencesFileName, String key, Set<String> value) {
        SharedPreferences sharedPref = application.getSharedPreferences(sharedPreferencesFileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putStringSet(key, value);
        editor.apply();
    }

    /**
     * Returns the String value associated with the key passed as argument
     * using SharedPreferences API.
     * @param sharedPreferencesFileName The name of file where to read the data.
     * @param key The key associated with the value to read.
     * @return The String value associated with the key passed as argument.
     */
    public String readStringData(String sharedPreferencesFileName, String key) {
        SharedPreferences sharedPref = application.getSharedPreferences(sharedPreferencesFileName,
                Context.MODE_PRIVATE);
        return sharedPref.getString(key, null);
    }

    /**
     * Returns the boolean value associated with the key passed as argument
     * using SharedPreferences API.
     * @param sharedPreferencesFileName The name of file where to read the data.
     * @param key The key associated with the value to read.
     * @return The boolean value associated with the key passed as argument.
     */
    public boolean readBooleanData(String sharedPreferencesFileName, String key) {
        SharedPreferences sharedPref = application.getSharedPreferences(sharedPreferencesFileName,
                Context.MODE_PRIVATE);
        return sharedPref.getBoolean(key, false);
    }

    /**
     * Returns the set of String values associated with the key passed as argument
     * using SharedPreferences API.
     * @param sharedPreferencesFileName The name of file where to read the data.
     * @param key The key associated with the value to read.
     * @return The set of String values associated with the key passed as argument.
     */
    public Set<String> readStringSetData(String sharedPreferencesFileName, String key) {
        SharedPreferences sharedPref = application.getSharedPreferences(sharedPreferencesFileName,
                Context.MODE_PRIVATE);
        return sharedPref.getStringSet(key, null);
    }

    /**
     * Deletes data saved in files created with SharedPreferences API.
     * @param sharedPreferencesFileName The file name where the information is saved.
     */
    public void deleteAll(String sharedPreferencesFileName) {
        SharedPreferences sharedPref = application.getSharedPreferences(sharedPreferencesFileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
    }



    /**

     Checks if the preferences file exists, and creates it with a default value if not.*
     @param context The application context.*/
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
