package com.anonstudio.quizpert;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.anonstudio.quizpert.model.Preferences;

import static com.anonstudio.quizpert.model.constants.Constants.StringTable.APP_PREF_FILE;

public class TournamentFragment extends Fragment {

    private Preferences preferences;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public TournamentFragment() {
        super(R.layout.tournaments_fragment);
        // Required empty public constructor
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        preferences = new Preferences(getContext(), APP_PREF_FILE);
        sp = preferences.getPreferenceObject();
        editor = preferences.getPreferenceEditorObject();
        String tournament = "The Novice";

        ImageView right_arrow = view.findViewById(R.id.arrow_right);
        ImageView left_arrow = view.findViewById(R.id.arrow_left);

        right_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }



}
