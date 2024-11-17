package com.anonstudio.quizpert;


import android.content.Intent;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.anonstudio.quizpert.databinding.ActivityQuizHardBinding;
import com.anonstudio.quizpert.model.Ads;
import com.anonstudio.quizpert.model.ChapAttributes;
import com.anonstudio.quizpert.model.Preferences;
import com.anonstudio.quizpert.model.Questions;

import com.anonstudio.quizpert.model.QuizDataBaseHelper;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.*;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.*;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;



public class HardQuizActivity extends AppCompatActivity {
    private ActivityQuizHardBinding binding;
    private String table;
    private String chapter;
    private List<Questions> questionsList;
    //private int questionsListSize;
    private int numOfQuestionsShown;
    //private static final int quizLength = 1;
    private Questions currentQuestion;
    private Boolean hard_quiz_passed = false;
    private CountDownTimer countDownTimer = null;
    private ChapAttributes chapAttributes;
    private Ads ads;
    private final int minimum_age = 14;
    private boolean gone_to_fragment = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_hard);
        binding = ActivityQuizHardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        Preferences preferences = new Preferences(getApplicationContext(),APP_PREF_FILE);
        SharedPreferences sp = preferences.getPreferenceObject();
        SharedPreferences.Editor editor = preferences.getPreferenceEditorObject();
        editor.putBoolean("finish_interstitial",false).apply();
        editor.putBoolean("finish_clicked",false).apply();
        //uses neutral age screen to load ads
      /*  ads = new Ads(getApplicationContext(), HardQuizActivity.this);
        if(sp.getInt("usra",eleven) > minimum_age) {
            //All ages ads
            ads.initAllAgesAd();

        } else {
            //children ads which comply with Coppa.
            ads.initChildCoppaAd();
        }
        ads.loadInterstitialAd();*/



        Intent receiveIntent = getIntent();
        table = receiveIntent.getStringExtra(getString(R.string.quiz_table_pref_key));
        chapter = receiveIntent.getStringExtra(Chapter);
        chapAttributes = new ChapAttributes(HardQuizActivity.this,chapter,table);

        String hqChapter = chapAttributes.goToHardQuizAttribute();
       /* switch(chapter){
            case "Chapter 1":
                hqChapter = "Hard Chap1";
                break;
            case "Chapter 2":
                hqChapter = "Hard Chap2";
                break;
            case "Chapter 3":
                hqChapter = "Hard Chap3";
                break;
            case "Chapter 4":
                hqChapter = "Hard Chap4";
                break;
            case "Chapter 5":
                hqChapter = "Hard Chap5";
                break;
            case "Chapter 6":
                hqChapter = "Hard Chap6";
                break;
            case "Chapter 7":
                hqChapter = "Hard Chap7";
                break;
            case "Chapter 8":
                hqChapter = "Hard Chap8";
                break;
        }*/

        getDataBaseQuestions(hqChapter);
        showHardQuestion();


        binding.hqbutton1.setOnClickListener(v -> { cancelTimer(); checkAnswer(binding.hqbutton1); });

        binding.hqbutton2.setOnClickListener(v -> { cancelTimer(); checkAnswer(binding.hqbutton2); });

        binding.hqbutton3.setOnClickListener(v -> { cancelTimer(); checkAnswer(binding.hqbutton3); });

    }

    private void getDataBaseQuestions(String chapter) {

        QuizDataBaseHelper quizDBHelper = new QuizDataBaseHelper(this);
        questionsList = quizDBHelper.getAllQuestions(table.trim(),chapter);
        //questionsListSize = questionsList.size();
        Collections.shuffle(questionsList);
        quizDBHelper.close();

    }

    /*private String getTblPrefix() {
        String tbl_prefix = "";
        switch (table) {
            case QuizConsts.QuestionsTable.SCI_TABLE_NAME:
                tbl_prefix = "sci";
                break;
            case QuizConsts.QuestionsTable.MUS_TABLE_NAME:
                tbl_prefix = "mus";
                break;
            case QuizConsts.QuestionsTable.HIS_TABLE_NAME:
                tbl_prefix = "his";
                break;
            case QuizConsts.QuestionsTable.MATH_TABLE_NAME:
                tbl_prefix = "math";
                break;
            case QuizConsts.QuestionsTable.ARTS_TABLE_NAME:
                tbl_prefix = "art";
                break;
            case QuizConsts.QuestionsTable.GEO_TABLE_NAME:
                tbl_prefix = "geo";
                break;
        }

        return tbl_prefix;
    }

    private String getChapUnlockBefore(String chapUnlock) {
        String chapUnlockBefore = "";
        String tbl_prefix = getTblPrefix();
        String[] chapters = {};

        switch(table) {
            case QuizConsts.QuestionsTable.SCI_TABLE_NAME:
                chapters = getResources().getStringArray(R.array.sciChapters);
                break;
            case QuizConsts.QuestionsTable.MUS_TABLE_NAME:
                chapters = getResources().getStringArray(R.array.musChapters);
                break;
            case QuizConsts.QuestionsTable.HIS_TABLE_NAME:
               chapters = getResources().getStringArray(R.array.hisChapters);
                break;
            case QuizConsts.QuestionsTable.MATH_TABLE_NAME:
                chapters = getResources().getStringArray(R.array.mathChapters);
                break;
            case QuizConsts.QuestionsTable.ARTS_TABLE_NAME:
                chapters = getResources().getStringArray(R.array.artChapters);
                break;
            case QuizConsts.QuestionsTable.GEO_TABLE_NAME:
                chapters = getResources().getStringArray(R.array.geoChapters);
                break;
        }

       if(chapUnlock.equalsIgnoreCase(chapters[1])) {
           chapUnlockBefore = tbl_prefix + "Unlocked_Chapter_2";
       } else if(chapUnlock.equalsIgnoreCase(chapters[2])) {
           chapUnlockBefore = tbl_prefix + "Unlocked_Chapter_3";
       } else if(chapUnlock.equalsIgnoreCase(chapters[3])) {
           chapUnlockBefore = tbl_prefix + "Unlocked_Chapter_4";
       } else if(chapUnlock.equalsIgnoreCase(chapters[4])) {
           chapUnlockBefore = tbl_prefix + "Unlocked_Chapter_5";
       } else if(chapUnlock.equalsIgnoreCase(chapters[5])) {
           chapUnlockBefore = tbl_prefix + "Unlocked_Chapter_6";
       } else if(chapUnlock.equalsIgnoreCase(chapters[6])) {
           chapUnlockBefore = tbl_prefix + "Unlocked_Chapter_7";
       } else if(chapUnlock.equalsIgnoreCase(chapters[7])) {
            chapUnlockBefore = tbl_prefix + "Unlocked_Chapter_8";
        }


        return chapUnlockBefore;
    }

    private String unlockChapter(String chapter){
        String tbl_prefix = getTblPrefix();
        String chapUnlock = "";
        switch (chapter.trim()) {

            case "Chapter 1":
                chapUnlock = tbl_prefix + "Chapter_2";
                break;
            case "Chapter 2":
                chapUnlock = tbl_prefix + "Chapter_3";
                break;
            case "Chapter 3":
                chapUnlock = tbl_prefix + "Chapter_4";
                break;
            case "Chapter 4":
                chapUnlock = tbl_prefix + "Chapter_5";
                break;
            case "Chapter 5":
                chapUnlock = tbl_prefix + "Chapter_6";
                break;
            case "Chapter 6":
                chapUnlock = tbl_prefix + "Chapter_7";
                break;
            case "Chapter 7":
                chapUnlock = tbl_prefix + "Chapter_8";
                break;
            default:
                break;
            //throw new IllegalStateException("Unexpected value: " + chapter);

        }
        return chapUnlock;

    }
*/

    private void checkAnswer(Button button) {
        if (button.getText().equals(currentQuestion.getRightAnswer())) {

            button.setTextColor(Color.GREEN);
            hard_quiz_passed = true;

        } else {
            button.setTextColor(Color.RED);
            //Locks Chapter on Category class if question was answered wrong
            chapterLockStatus();
            hard_quiz_passed = false;

        }


        goToHardQuizCompletion(hard_quiz_passed);

    }

    private void killHardQuizActivity(boolean hard_quiz_passed) {

        binding.hardQuiz.removeAllViews();
        Bundle bundle = new Bundle();
        bundle.putBoolean(hard_quiz, hard_quiz_passed);
        bundle.putString(getString(R.string.quiz_table_pref_key), table);


        HardQuizCompletion fragment = new HardQuizCompletion();
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            try {
                fragmentTransaction.replace(R.id.hard_quiz, fragment).commit();
                gone_to_fragment = true;
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }





    }



    private void goToHardQuizCompletion(boolean hard_quiz_passed) {

        binding.hardQuiz.removeAllViews();
        //ads.showInterstitialAd();
        Bundle bundle = new Bundle();
        bundle.putBoolean(hard_quiz, hard_quiz_passed);
        bundle.putString(getString(R.string.quiz_table_pref_key), table);


        HardQuizCompletion fragment = new HardQuizCompletion();
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (!gone_to_fragment) {
            try {
                fragmentTransaction.replace(R.id.hard_quiz, fragment).commit();
                gone_to_fragment = true;
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }


        }
    }



    private void startTimer() {
        countDownTimer = new CountDownTimer(countdown_timer_start,countdown_timer_interval) {
            @Override
            public void onTick(long millisUntilFinished) {
                binding.countDown.setText(String.valueOf(millisUntilFinished/countdown_timer_interval));
                if(millisUntilFinished/countdown_timer_interval <= countdown_timer_warning) {
                    binding.countDown.setTextColor(Color.parseColor(quiz_red_color));

                } else {
                    binding.countDown.setTextColor(Color.parseColor(start_blue_color));
                }
            }

            @Override
            public void onFinish() {
                hard_quiz_passed = false;
                goToHardQuizCompletion(hard_quiz_passed);
            }
        };
        countDownTimer.start();

    }

    private void cancelTimer() {
        if(countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private void showHardQuestion() {
        if (numOfQuestionsShown < hardQuizLength) {
            currentQuestion = questionsList.get(0);
            binding.hqquestionTextview.setText(currentQuestion.getQuestion());

            List<String> ls = new ArrayList<>();

            ls.add(currentQuestion.getAnswer1());
            ls.add(currentQuestion.getAnswer2());
            ls.add(currentQuestion.getAnswer3());


            // Random random = new Random(1);

            Collections.shuffle(ls);


            binding.hqbutton1.setText(ls.get(0));
            binding.hqbutton2.setText(ls.get(1));
            binding.hqbutton3.setText(ls.get(2));
            startTimer();


            try {
                ls.clear();
            } catch (UnsupportedOperationException e) {
                e.printStackTrace();
            }


//            binding.button1.setText(currentQuestion.getAnswer1());
//            binding.button2.setText(currentQuestion.getAnswer2());
//            binding.button3.setText(currentQuestion.getAnswer3());
            numOfQuestionsShown++;
        } else {
            Intent bcktoChapters = new Intent(HardQuizActivity.this,CategorySelection.class);
            //bcktoChapters.putExtra(getString(R.string.quiz_table_pref_key),table);
            startActivity(bcktoChapters);
           // ((Activity) Objects.requireNonNull(getActivity())).overridePendingTransition(0,0);

        }
    }



    private void chapterLockStatus() {
        chapAttributes.goToHardChapterUnlockAttribute();
       /* String chapUnlock = unlockChapter(chapter); //chapter = Chapter 1 returns eg. chaplock = sciChapter_2 unlockChapter();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean chapUnlockedBefore = sp.getBoolean(getChapUnlockBefore(chapUnlock),false);// will return sciUnlockedChapterBefore2
        boolean chapUnlocked = sp.getBoolean(chapUnlock,true); //chaplock = sciChapter 2
        if(chapUnlocked && chapUnlockedBefore) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean(chapUnlock, false).apply();
        }*/
    }

    @Override
    protected void onStop() {
        super.onStop();
        chapterLockStatus();
    }

    @Override
    protected void onPause() {

        killHardQuizActivity(hard_quiz_passed);
        super.onPause();
    }


}
