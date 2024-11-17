package com.anonstudio.quizpert;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.anonstudio.quizpert.model.Ads;
import com.anonstudio.quizpert.model.Gems;
import com.anonstudio.quizpert.model.Preferences;

import java.util.Objects;

import static com.anonstudio.quizpert.model.constants.Constants.IntTable.eleven;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.APP_PREF_FILE;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.hard_quiz;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.SET_RISK_IT_ALL_GEMS;


public class HardQuizCompletion extends Fragment {


    private Ads ads;
    private Preferences preferences;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    String qtable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hard_quiz_completion, container, false);

        ImageView gems_image = view.findViewById(R.id.gems_image);
        ImageView completion_status = view.findViewById(R.id.completion_status);


      /* // ads = new Ads(getContext(),getActivity());
        preferences = new Preferences(getActivity().getApplicationContext(),APP_PREF_FILE);
        sp = preferences.getPreferenceObject();
        editor = preferences.getPreferenceEditorObject();
        if(sp.getInt("usra",eleven) > minimum_age) {
            //All ages ads

            ads.initAllAgesAd();

        } else {
            //children ads which comply with Coppa.

            ads.initChildCoppaAd();
        }
        ads.loadInterstitialAd();*/

        Gems gems = new Gems(SET_RISK_IT_ALL_GEMS ,getContext().getApplicationContext());

        TextView gems_attained = view.findViewById(R.id.receive);

        Button next = view.findViewById(R.id.next);
        assert getArguments() != null;
        qtable = getArguments().getString(getString(R.string.quiz_table_pref_key));
        //String chapter = getArguments().getString("chapter");
        boolean hard_quiz_passed = getArguments().getBoolean(hard_quiz);

        if(hard_quiz_passed) {
            view.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()),R.drawable.quiz_completion_background));
            completion_status.setImageResource(R.drawable.you_win_logo);
            gems_image.setImageResource(R.drawable.gems);
            gems_attained.setText(getString(R.string.receive_gems, String.valueOf(gems.getRisk_it_all_gems_received())));
            gems.setGemAmount();
            next.setBackgroundResource(R.drawable.button_quiz_completion);
        } else {
            view.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()),R.drawable.quiz_failed_background));
            view.findViewById(R.id.hold_gems_status).setVisibility(View.GONE);
            completion_status.setImageResource(R.drawable.you_lose_logo);
            next.setBackgroundResource(R.drawable.button_quiz_failed);

        }

        try {
            next.setOnClickListener(v -> {
                goToCategorySelection();
               /* editor.putBoolean("finish_clicked", true).apply();
                //ads.showInterstitialAd();
                if (!(sp.getBoolean("finish_interstitial", false))) {

                }*/


               // goToChapterActivity();

            });
        } catch(NullPointerException e) {
            e.printStackTrace();
        }




        return view;




    }

    private void goToCategorySelection() {
        Intent bcktoCategory = new Intent(getActivity(),CategorySelection.class);
        //bcktoChapters.putExtra(getString(R.string.quiz_table_pref_key),qtable);
        Objects.requireNonNull(getActivity()).finish();
        startActivity(bcktoCategory);
        ((Activity) Objects.requireNonNull(getActivity())).overridePendingTransition(0,0);
    }

    @Override
    public void onResume() {
      /*  if(sp.getBoolean("finish_clicked",false)) {
            goToChapterActivity();
        }*/
        super.onResume();
    }
}






