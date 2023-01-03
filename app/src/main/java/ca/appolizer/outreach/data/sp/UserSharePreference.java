package ca.appolizer.outreach.data.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Map;

public class UserSharePreference<T> {
    private static final String TAG = UserSharePreference.class.getName();
    private static SharedPreferences prefs;
    private static UserSharePreference uniqueInstance;
    public static final String PREF_NAME = "profile_info";

    private UserSharePreference() {
    }

    public static UserSharePreference getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new UserSharePreference();
        }
        return uniqueInstance;
    }

    public void persistSharePreference(Context context, String key, T value) {
        SharedPreferences.Editor sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();

        if (value instanceof String) {
            sp.putString(key, (String) value);
        }
        if (value instanceof Long) {
            sp.putLong(key, (Long) value);
        }

        sp.apply();
    }

    public Map<String, ?> getSharePreference(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sp.getAll();
    }

    private SharedPreferences getPrefs() {
        return prefs;
    }

    /**
     * Clears all data in SharedPreferences
     */
    public void clearPrefs(Context context) {
        SharedPreferences.Editor sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        sp.clear();
        sp.apply();
    }
}
