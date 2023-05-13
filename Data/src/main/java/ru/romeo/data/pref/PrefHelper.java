package ru.romeo.data.pref;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class PrefHelper {
    public static final String PREF_KEY = "ru.romeo.cg";
    public static final String TRACK_PROGRESS = "trackProgress";
    public static final String THEME_COL_MAIN = "themeMain";
    public static final String THEME_COL_SEC = "themeSecond";
    public static final String LAST_LESSON = "lastLesson";
    public static final String LAST_CHAPTER = "lastChapter";

    public static final int DEF_INT = 0;
    public static final long DEF_LONG = 0;
    public static final boolean DEF_BOOL = false;

    public static SharedPreferences prefs;
    public static SharedPreferences.Editor editor;

    public static void init(Activity activity) {
        prefs = activity.getSharedPreferences(
                PREF_KEY, Context.MODE_PRIVATE);
    }

    public static void put(String key, int value) {
        prefs.edit().putInt(PREF_KEY+"."+key, value).apply();
    }

    public static void put(String key, long value) {
        prefs.edit().putLong(PREF_KEY+"."+key, value).apply();
    }

    public static void put(String key, String value) {
        prefs.edit().putString(PREF_KEY+"."+key, value).apply();
    }

    public static String getString(String key, String def) {
        return prefs.getString(PREF_KEY+"."+key, def);
    }

    public static int getInt(String key, int def) {
        return prefs.getInt(PREF_KEY+"."+key, def);
    }

    public static long getLong(String key, long def) {
        return prefs.getLong(PREF_KEY+"."+key, def);
    }

    public static boolean getBoolean(String key, boolean def) {
        return prefs.getBoolean(key, def);
    }
}
