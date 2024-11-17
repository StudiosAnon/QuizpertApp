package com.anonstudio.quizpert.model;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    private final String APP_PREFS_FILE;
    private final Context context;

    public Preferences(Context context, String APP_PREFS_FILE) {
        this.APP_PREFS_FILE = APP_PREFS_FILE;
        this.context = context;
    }

    public SharedPreferences getPreferenceObject() {
        return context.getSharedPreferences(APP_PREFS_FILE,Context.MODE_PRIVATE);

    }

    public SharedPreferences.Editor getPreferenceEditorObject() {
        SharedPreferences pref = context.getSharedPreferences(APP_PREFS_FILE,Context.MODE_PRIVATE);
        return pref.edit();
    }

}
