package ca.appolizer.outreach.data.sp;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class UserSharePreference<T> {
    private final static String SHARE_PREF = "profile_info";
    private static UserSharePreference<?> instance;

    private UserSharePreference() {
    }

    public static UserSharePreference<?> getInstance() {
        if (instance == null) {
            instance = new UserSharePreference<>();
        }
        return instance;
    }

    public void persistSharePreference(Context context, T type) {
        SharedPreferences.Editor sp = context.getSharedPreferences(SHARE_PREF, Context.MODE_PRIVATE).edit();
        
        sp.putString("token", "");
        sp.apply();
    }

    public Map<String, ?> getSharePreference(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SHARE_PREF, Context.MODE_PRIVATE);
        return sp.getAll();
    }
}
