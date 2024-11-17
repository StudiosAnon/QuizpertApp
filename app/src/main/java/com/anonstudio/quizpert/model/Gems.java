package com.anonstudio.quizpert.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import static com.anonstudio.quizpert.model.constants.Constants.IntTable.*;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.*;
import static java.lang.Integer.parseInt;

public class Gems {

    private int REQUEST_CODE;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private Context context;

    public Gems() {}

    public Gems(Context context) {
        Preferences preferences = new Preferences(context, APP_PREF_FILE);
        this.sp = preferences.getPreferenceObject();
        this.editor = preferences.getPreferenceEditorObject();
    }

    public Gems(int REQUEST_CODE, Context context) {
        this.REQUEST_CODE = REQUEST_CODE;
        this.context = context;
        Preferences preferences = new Preferences(context, APP_PREF_FILE);
        this.sp = preferences.getPreferenceObject();
        this.editor = preferences.getPreferenceEditorObject();
    }

    private int checkAvailabilityOfGems(int REQUEST_CODE) {

        int RETURN_CODE = F_CODE;
        int gems = getGemAmount();
        if(REQUEST_CODE == POWER_UP_REQUEST_CODE) {
            if(gems >= power_ups_gems_needed) {
                RETURN_CODE = S_CODE;
            }
        }

        if(REQUEST_CODE == CHAP_UNLOCK_REQUEST_CODE) {
            if(gems >= chap_unlock_gems_needed) {
                RETURN_CODE = S_CODE;
            }
        }

        return RETURN_CODE;
    }

    public int completeGemsTransaction() {
        int GEMS_STATUS = F_CODE;
        int RECEIVED_CODE = checkAvailabilityOfGems(REQUEST_CODE);
        int gems = getGemAmount();


        if(RECEIVED_CODE == S_CODE) {
            if(REQUEST_CODE == POWER_UP_REQUEST_CODE) {
                gems = gems - power_ups_gems_needed;
            }

            if(REQUEST_CODE == CHAP_UNLOCK_REQUEST_CODE) {
                gems = gems - chap_unlock_gems_needed;

            }
            editor.putInt(GEMS_PREF_FILE,gems).apply();
            GEMS_STATUS = S_CODE;
        }
        return GEMS_STATUS;
    }

    public int getGemAmount() {

       return sp.getInt(GEMS_PREF_FILE, DEFAULT_GEMS_KEY);
    }

    public void setGemAmount() {

        int gemAmount = getGemAmount();
        switch (REQUEST_CODE) {
            case SET_RISK_IT_ALL_GEMS:
                editor.putInt(GEMS_PREF_FILE, gemAmount + risk_it_all_gems_received).apply();
                break;
            case SET_GEMS_ONE:
                editor.putInt(GEMS_PREF_FILE, gemAmount + SET_GEMS_ONE).apply();

                break;
            case SET_GEMS_TWO:
                editor.putInt(GEMS_PREF_FILE, gemAmount + SET_GEMS_TWO).apply();
                break;
            case SET_GEMS_THREE:
                editor.putInt(GEMS_PREF_FILE, gemAmount + SET_GEMS_THREE).apply();
                break;
            case SET_GEMS_FOUR:
                editor.putInt(GEMS_PREF_FILE, gemAmount + SET_GEMS_FOUR).apply();
                break;



        }

    }


    public int getGems_received() {
        return gems_received;
    }

    public int getS_CODE() {
        return S_CODE;
    }

    public int getF_CODE() {
        return F_CODE;
    }

    public int getRisk_it_all_gems_received() {
        return risk_it_all_gems_received * 2;
    }
}
