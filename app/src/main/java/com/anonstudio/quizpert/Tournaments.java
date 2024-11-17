package com.anonstudio.quizpert;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.NoCopySpan;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.anonstudio.quizpert.databinding.ActivityMainBinding;
import com.anonstudio.quizpert.databinding.TournamentsBinding;
import com.anonstudio.quizpert.model.Preferences;

import static com.anonstudio.quizpert.model.constants.Constants.StringTable.APP_PREF_FILE;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.QUIZ_TBL_PREFIX;

public class Tournaments extends AppCompatActivity {
    private TournamentsBinding binding;
    private Preferences preferences = new Preferences(Tournaments.this, APP_PREF_FILE);
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    String tournament = "Novice";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tournaments);
        binding = TournamentsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);



        ImageView right_arrow = view.findViewById(R.id.arrow_right);
        ImageView left_arrow = view.findViewById(R.id.arrow_left);



        binding.arrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchTournament(tournament,"right");
            }
        });

        binding.arrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchTournament(tournament,"left");
            }
        });

    }

    private void switchTournament(String tournament_name, String arrow) {
        switch(tournament_name) {
            case "Novice":
                if(arrow.equals("right")) {
                    tournament = "Practitioner";
                    binding.trophy.setImageResource(R.drawable.cup_twoxxxhdpi);
                    binding.prize.setImageResource(R.drawable.prize_twoxxxhdpi);
                } else {
                    tournament = "Journeyman";
                    binding.trophy.setImageResource(R.drawable.cup_threexxxhdpi);
                    binding.prize.setImageResource(R.drawable.prize_threexxxhdpi);
                }
                break;
            case "Practitioner":
                if(arrow.equals("right")) {
                    tournament = "Journeyman";
                    binding.trophy.setImageResource(R.drawable.cup_threexxxhdpi);
                    binding.prize.setImageResource(R.drawable.prize_threexxxhdpi);
                } else {
                    tournament = "Novice";
                    binding.trophy.setImageResource(R.drawable.cup_onexxxhdpi);
                    binding.prize.setImageResource(R.drawable.prize_onexxxhdpi);
                }
                break;
            case "Journeyman":
                if(arrow.equals("right")) {
                    tournament = "Novice";
                    binding.trophy.setImageResource(R.drawable.cup_onexxxhdpi);
                    binding.prize.setImageResource(R.drawable.prize_onexxxhdpi);
                } else {
                    tournament = "Practitioner";
                    binding.trophy.setImageResource(R.drawable.cup_twoxxxhdpi);
                    binding.prize.setImageResource(R.drawable.prize_twoxxxhdpi);
                }
                break;


        }
    }

/*    private void openTournamentFragment() {

        //Bundle bundle = new Bundle();
        //bundle.putString("tournament", tournament);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .addToBackStack("tournamentFrag")
                .replace(R.id.tournaments_frag_container, TournamentFragment.class,null)
                .commit();

    }*/

}
