package com.anonstudio.quizpert;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.anonstudio.quizpert.databinding.ActivityQuizBinding;
import com.anonstudio.quizpert.model.Ads;
import com.anonstudio.quizpert.model.ChapAttributes;
import com.anonstudio.quizpert.model.Gems;
import com.anonstudio.quizpert.model.Preferences;
import com.anonstudio.quizpert.model.Questions;
import com.anonstudio.quizpert.model.QuizDataBaseHelper;
import com.facebook.ads.Ad;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import static android.content.ContentValues.TAG;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.*;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.*;
import static com.anonstudio.quizpert.model.constants.Constants.BoolTable.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;




public class QuizActivity extends AppCompatActivity {


    private List<Questions> questionsList;
    private ActivityQuizBinding binding;
    private String table;
    private String chapter;
    private int questionsListSize;
    private int numOfQuestionsShown;

    private Questions currentQuestion;
    private int quizScore = 0;
    private int wrongAnswers = 0;
    private int power_up_count = 0;

    private int diamonds;
    private int questionsAnswered = 0;


    private boolean processRunning = off;
    private boolean gone_to_fragment = off;

    private CountDownTimer countDownTimer = null;

    private Animation animBlink;

    MediaPlayer player = null;
    MediaPlayer soundPlayer = null;

    private int musicList[] = {R.raw.air, R.raw.carnival_of_animals, R.raw.chopin, R.raw.gnossienne, R.raw.gymnopedie1};

    final int music_min = 0;
    final int music_max = 4;
    private String mr_unreliable_answers[] = {};
    private final int limit_of_power_up = 4;
    private List<String> ls = new ArrayList<>();
    AlertDialog unreliable_alertDialog;
    private ChapAttributes chapAttributes;
    private final Preferences preferences =  new Preferences(QuizActivity.this, APP_PREF_FILE);
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private Ads ads;
    private int minimum_age = 14;










    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_quiz);


        //getIntent must be declared after the setContentView();

        Intent getQuizTable = getIntent();



        table = getQuizTable.getStringExtra(getString(R.string.quiz_table_pref_key));
        chapter = getQuizTable.getStringExtra(Chapter);







        Gems gems = new Gems(POWER_UP_REQUEST_CODE, QuizActivity.this);
        chapAttributes = new ChapAttributes(QuizActivity.this, chapter, table);

        sp = preferences.getPreferenceObject();
        editor = preferences.getPreferenceEditorObject();

        editor.putBoolean("trophy_dialog_shown",false).apply();
        editor.putBoolean("finish_interstitial",false).apply();
        editor.putBoolean("finish_clicked",false).apply();
        editor.putBoolean("rewardedAd",false).apply();
        editor.putBoolean("AD_SHOWN",false).apply();
        editor.putBoolean("power_up_dialog_shown", false).apply();
        editor.putBoolean("quizAdShown",false).apply();




        //

        //


        /*AudienceNetworkAds.initialize(this);
        loadFacebookInterstitialAd();*/


//used neutral age screen to load appropriate ads
    /*    if(sp.getInt("dicePowerUp",zero) > 0  || sp.getInt("explosionPowerUp",zero) > 0 || sp.getInt("mrUnreliablePowerUp",zero) > 0) {

            ads = new Ads(getApplicationContext(), QuizActivity.this);
            if(sp.getInt("usra",eleven) > minimum_age) {
                //All ages ads
                ads.initAllAgesAd();

            } else {
                //children ads which comply with Coppa.
                ads.initChildCoppaAd();
            }
            ads.loadInterstitialAd();


        }*/

        AtomicInteger dicePowerUp = new AtomicInteger(sp.getInt("dicePowerUp", zero));
        AtomicInteger explosionPowerUp = new AtomicInteger(sp.getInt("explosionPowerUp", zero));
        AtomicInteger mrUnreliablePowerUp = new AtomicInteger(sp.getInt("mrUnreliablePowerUp", zero));




        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.chapter.setText(chapter);
        play();

        int gem_amount = gems.getGemAmount();

        binding.circleExplosionTv.setText(String.valueOf(explosionPowerUp.get()));
        binding.circleDiceTv.setText(String.valueOf(dicePowerUp.get()));
        binding.circleMrUnreliableTv.setText(String.valueOf(mrUnreliablePowerUp.get()));



        if(explosionPowerUp.get() == 0){
            binding.circleExplosionTv.setVisibility(View.GONE);
            binding.circleExplosion.setVisibility(View.GONE);
        }

        if(dicePowerUp.get() == 0){
            binding.circleDiceTv.setVisibility(View.GONE);
            binding.circleDice.setVisibility(View.GONE);
        }

        if(mrUnreliablePowerUp.get() == 0){
            binding.circleMrUnreliableTv.setVisibility(View.GONE);
            binding.circleMrUnreliable.setVisibility(View.GONE);
        }




        binding.diamonds.setText(String.valueOf(gem_amount));

        mr_unreliable_answers = getResources().getStringArray(R.array.mr_unreliable_answers);

        getDataBaseQuestions();
        moveToNextQuestion();

        binding.mrUnreliable.setOnClickListener(v -> {

            //Skips question
            if(power_up_count != limit_of_power_up) {
                if (!processRunning) {

                    if(mrUnreliablePowerUp.get() > 0) {
                        mrUnreliableAlertDialog(v);
                        power_up_count = power_up_count + one;
                        editor.putInt("mrUnreliablePowerUp", mrUnreliablePowerUp.get() - one).apply();
                        binding.circleMrUnreliableTv.setVisibility(View.GONE);
                        binding.circleMrUnreliable.setVisibility(View.GONE);
                        processRunning = on;
                        binding.mrUnreliable.setBackgroundColor(Color.GRAY);
                        binding.explosion.setBackgroundColor(Color.GRAY);
                        binding.dice.setBackgroundColor(Color.GRAY);
                        mrUnreliablePowerUp.set(sp.getInt("mrUnreliablePowerUp", zero));

                    }

                    if (gems.completeGemsTransaction() == gems.getS_CODE()) {
                        power_up_count = power_up_count + one;

                        binding.diamonds.setText(String.valueOf(gems.getGemAmount()));
                        processRunning = on;
                        binding.mrUnreliable.setBackgroundColor(Color.GRAY);
                        binding.explosion.setBackgroundColor(Color.GRAY);
                        binding.dice.setBackgroundColor(Color.GRAY);

                        mrUnreliableAlertDialog(v);

                        /*AlertDialog.Builder builder = new AlertDialog.Builder(QuizActivity.this);
                        ViewGroup viewGroup = findViewById(android.R.id.content);
                        View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.mr_unreliable_custom_view, viewGroup, off);
                        builder.setView(dialogView);
                        unreliable_alertDialog = builder.create();
                        unreliable_alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        unreliable_alertDialog.show();

                        Collections.shuffle(Arrays.asList(mr_unreliable_answers, questionsAnswered));
                        TextView unreliable_answers = dialogView.findViewById(R.id.mr_unreliable_answer);
                        int num_of_answers = 3;
                        String answer = mr_unreliable_answers[questionsAnswered] + " ";
                        answer = answer + ls.get((int) (Math.random() * num_of_answers) + music_min);
                        unreliable_answers.setText(answer);

                        Button okBtn = dialogView.findViewById(R.id.ok_btn);
                        okBtn.setOnClickListener(v1 -> unreliable_alertDialog.dismiss());*/


                    }
                }
            }

        });

        binding.explosion.setOnClickListener(v -> {
            if(power_up_count != limit_of_power_up) {
                if (!processRunning) {

                    if(explosionPowerUp.get() > 0) {
                        power_up_count = power_up_count + one;
                        editor.putInt("explosionPowerUp", explosionPowerUp.get() - one).apply();

                        binding.circleExplosionTv.setVisibility(View.GONE);
                        binding.circleExplosion.setVisibility(View.GONE);
                        processRunning = on;
                        binding.mrUnreliable.setBackgroundColor(Color.GRAY);
                        binding.explosion.setBackgroundColor(Color.GRAY);
                        binding.dice.setBackgroundColor(Color.GRAY);
                        explosionPowerUp.set(sp.getInt("explosionPowerUp",zero));
                        removeAnswer();
                    }

                    else if (gems.completeGemsTransaction() == gems.getS_CODE() ) {
                        power_up_count = power_up_count + one;
                        binding.diamonds.setText(String.valueOf(gems.getGemAmount()));
                        processRunning = on;
                        binding.mrUnreliable.setBackgroundColor(Color.GRAY);
                        binding.explosion.setBackgroundColor(Color.GRAY);
                        binding.dice.setBackgroundColor(Color.GRAY);
                        removeAnswer();

                    }
                }
            }

        });

        binding.dice.setOnClickListener(v -> {
            if(power_up_count != limit_of_power_up) {
                if (!processRunning) {

                    if(dicePowerUp.get() > 0) {
                        power_up_count = power_up_count + one;
                        editor.putInt("dicePowerUp", dicePowerUp.get() - one).apply();
                        binding.circleDiceTv.setVisibility(View.GONE);
                        binding.circleDice.setVisibility(View.GONE);
                        binding.mrUnreliable.setBackgroundColor(Color.GRAY);
                        binding.explosion.setBackgroundColor(Color.GRAY);
                        binding.dice.setBackgroundColor(Color.GRAY);
                        dicePowerUp.set(sp.getInt("dicePowerUp", zero));
                        cancelTimer();
                        moveToNextQuestion();

                    }

                    else if (gems.completeGemsTransaction() == gems.getS_CODE()) {
                        power_up_count = power_up_count + one;
                        binding.diamonds.setText(String.valueOf(gems.getGemAmount()));
                        binding.mrUnreliable.setBackgroundColor(Color.GRAY);
                        binding.explosion.setBackgroundColor(Color.GRAY);
                        binding.dice.setBackgroundColor(Color.GRAY);

                        cancelTimer();
                        moveToNextQuestion();

                    }
                }
            }

        });


        binding.button1.setOnClickListener(v -> { cancelTimer(); checkAnswer(binding.button1, binding.button2, binding.button3); });

        binding.button2.setOnClickListener(v -> { cancelTimer(); checkAnswer(binding.button2, binding.button1, binding.button3); });

        binding.button3.setOnClickListener(v -> { cancelTimer(); checkAnswer(binding.button3, binding.button1, binding.button2); });

    }


    private void mrUnreliableAlertDialog(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(QuizActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.mr_unreliable_custom_view, viewGroup, off);
        builder.setView(dialogView);
        unreliable_alertDialog = builder.create();
        unreliable_alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        unreliable_alertDialog.show();

        Collections.shuffle(Arrays.asList(mr_unreliable_answers, questionsAnswered));
        TextView unreliable_answers = dialogView.findViewById(R.id.mr_unreliable_answer);
        int num_of_answers = 3;
        String answer = mr_unreliable_answers[questionsAnswered] + " ";
        answer = answer + ls.get((int) (Math.random() * num_of_answers) + music_min);
        unreliable_answers.setText(answer);

        Button okBtn = dialogView.findViewById(R.id.ok_btn);
        okBtn.setOnClickListener(v1 -> unreliable_alertDialog.dismiss());

    }



    private void getSoundState(int music_file) {
        boolean music_state = sp.getBoolean(getString(R.string.sound_pref_key), on);
        if (music_state) { getSoundPlayer(music_file); }
    }




    public void play() {

        if (sp.getBoolean(getString(R.string.music_pref_key), on)) {
            if (player == null) {

                final int range = (music_max - music_min) + 1;
                int music_track = (int) (Math.random() * range) + music_min;
                player = MediaPlayer.create(this, musicList[music_track]);


                player.setOnCompletionListener(mp -> {
                    player.release();
                    player = null;
                    play();

                });


            }
            player.start();
        }
    }


    public void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        killQuizActivity();
    }


    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    private void answerWrong() {
        questionAnswered();
        getSoundState(R.raw.wrong_answer);
        switch (wrongAnswers) {
            case 1:
                binding.imageView1.setImageResource(R.drawable.ic_error_logo);
                break;
            case 2:
                binding.imageView2.setImageResource(R.drawable.ic_error_logo);
                break;
            case 3:
                binding.imageView3.setImageResource(R.drawable.ic_error_logo);
                break;
        }
        if(wrongAnswers == 3) {
         goToQuizCompletionFragment();
        }

    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(countdown_timer_start, countdown_timer_interval) {
            @Override
            public void onTick(long millisUntilFinished) {
                binding.countDown.setText(String.valueOf(millisUntilFinished / countdown_timer_interval));
                if (millisUntilFinished / countdown_timer_interval <= countdown_timer_warning) {

                    binding.countDown.setTextColor(Color.parseColor(quiz_red_color));


                } else {
                    binding.countDown.setTextColor(Color.parseColor(start_blue_color));
                }
            }

            @Override
            //does this register as question answered - no it does not
            public void onFinish() {
                if (!gone_to_fragment) {
                    closeUnreliableAlertDialog();
                    wrongAnswers++;
                    answerWrong();
                    cancelTimer();
                    moveToNextQuestion();
                }
            }
        };
        countDownTimer.start();

    }

    private void closeUnreliableAlertDialog() {
        try {
            if (unreliable_alertDialog.isShowing()) {
                unreliable_alertDialog.dismiss();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    private void cancelTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private void removeAnswer() {
        List<Integer> wrongAnswers = new ArrayList<>();

        if (!currentQuestion.getRightAnswer().trim().equals(binding.button1.getText().toString().trim())) {
            wrongAnswers.add(1);
        }
        if (!currentQuestion.getRightAnswer().equals(binding.button2.getText().toString().trim())) {
            wrongAnswers.add(2);
        }
        if (!currentQuestion.getRightAnswer().equals(binding.button3.getText().toString().trim())) {
            wrongAnswers.add(3);
        }

        Collections.shuffle(wrongAnswers);
        Random random = new Random();

        int wrongAnswerID = wrongAnswers.get(random.nextInt(2));
        switch (wrongAnswerID) {
            case 1:
                binding.button1.setVisibility(View.INVISIBLE);
                break;
            case 2:
                binding.button2.setVisibility(View.INVISIBLE);
                break;
            case 3:
                binding.button3.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private void returnAnswerBox() {
        if (binding.button1.getVisibility() == View.INVISIBLE) {
            binding.button1.setVisibility(View.VISIBLE);
        } else if (binding.button2.getVisibility() == View.INVISIBLE) {
            binding.button2.setVisibility(View.VISIBLE);
        } else if (binding.button3.getVisibility() == View.INVISIBLE) {
            binding.button3.setVisibility(View.VISIBLE);
        }
    }

    private void getDataBaseQuestions() {
        QuizDataBaseHelper quizDBHelper = new QuizDataBaseHelper(this);
        questionsList = quizDBHelper.getAllQuestions(table.trim(), chapter);
        questionsListSize = questionsList.size();
        Collections.shuffle(questionsList);
        quizDBHelper.close();


    }


    private void moveToNextQuestion() {

        processRunning = off;

        binding.questionTextview.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.textview_circle));

        binding.questionTextview.clearAnimation();
        if(power_up_count != limit_of_power_up) {

            binding.mrUnreliable.setBackgroundColor(Color.WHITE);
            binding.explosion.setBackgroundColor(Color.WHITE);
            binding.dice.setBackgroundColor(Color.WHITE);
        }
        //if questionList holds ten questions and all are shown, an indexoutofbounds exception will be thrown.
        if (questionsAnswered <= quizLength) {
            binding.questionCounter.setText(String.valueOf((questionsAnswered + one) + " / " + String.valueOf(quizLength + one)));
            currentQuestion = questionsList.get(numOfQuestionsShown);
            binding.questionTextview.setText(currentQuestion.getQuestion());

            ls = new ArrayList<>();


            ls.add(currentQuestion.getAnswer1());
            ls.add(currentQuestion.getAnswer2());
            ls.add(currentQuestion.getAnswer3());

            // Random random = new Random(1);

            Collections.shuffle(ls);


            binding.button1.setText(ls.get(0));
            binding.button2.setText(ls.get(1));
            binding.button3.setText(ls.get(2));
            startTimer();
            returnAnswerBox();

            numOfQuestionsShown++;
        } else {
            //IndexBounds
            goToQuizCompletionFragment();

        }
    }


    private void killQuizActivity() {

        binding.quiz.removeAllViews();
        if(binding.questionTextview.getVisibility() != View.GONE) {
            binding.questionTextview.setVisibility(View.GONE);
        }
        Bundle bundle = new Bundle();
        bundle.putString(getString(R.string.quiz_table_pref_key),table);
        bundle.putString(Chapter,chapter);
        bundle.putInt(Quiz_Score,quizScore);
        bundle.putInt(Quiz_Answered,questionsAnswered);

        QuizCompletion fragment = new QuizCompletion();
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();


        try{fragmentTransaction.replace(R.id.quiz,fragment).commit();
            gone_to_fragment = true;
        } catch(IllegalStateException e) { e.printStackTrace(); }

    }


    private void goToQuizCompletionFragment2() {
        goToQuizCompletionFragment(); }


    private void goToQuizCompletionFragment() {

        binding.questionTextview.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.textview_circle));

        binding.questionTextview.clearAnimation();
        binding.questionTextview.setVisibility(View.GONE);
        binding.quiz.removeAllViews();
        /*if(sp.getInt("dicePowerUp",zero) > 0  || sp.getInt("explosionPowerUp",zero) > 0 || sp.getInt("mrUnreliablePowerUp",zero) > 0) {
            ads.showInterstitialAd();
            editor.putBoolean("quizAdShown",true).apply();

        }*/
        try {
            player.stop();
            player.release();
        } catch(Exception ignored){}

        //binding.quiz.removeAllViews();
       /* */

        chapAttributes.goToAttemptsAttribute();
        chapAttributes.goToHighScoreAttribute(quizScore);

        if(quizScore >= quizPassed) { chapAttributes.goToChapterUnlockAttribute(); if(quizScore == fullMarks) { chapAttributes.goToTrophyAttribute(); } }
        Bundle bundle = new Bundle();
        bundle.putString(getString(R.string.quiz_table_pref_key),table);
        bundle.putString(Chapter,chapter);
        bundle.putInt(Quiz_Score,quizScore);
        bundle.putInt(Quiz_Answered,questionsAnswered);
        QuizCompletion fragment = new QuizCompletion();
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(!gone_to_fragment) {
            try {

                fragmentTransaction.replace(R.id.quiz, fragment).commit();
                gone_to_fragment = true;
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    private void questionAnswered() {
        questionsAnswered++;
    }

    private void getSoundPlayer(int music_file) {
        if (soundPlayer == null){
            soundPlayer = MediaPlayer.create(this,music_file);
            soundPlayer.setOnCompletionListener(mp -> {
                soundPlayer.release();
                soundPlayer = null;

            });
        }
        soundPlayer.start();
    }



    private void checkAnswer(Button button, Button other_choice1, Button other_choice2) {

        if (button.getText().equals(currentQuestion.getRightAnswer())) {

            button.setTextColor(Color.GREEN);
            getSoundState(R.raw.correct_answer);
            binding.questionTextview.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.question_view_correct));
            animBlink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.quick_blink);
            binding.questionTextview.startAnimation(animBlink);
            quizScore += 1;
            binding.Score.setText(String.valueOf(quizScore));

            questionAnswered();

        } else {
            button.setTextColor(Color.RED);
            getSoundState(R.raw.wrong_answer);
            if(other_choice1.getText().equals(currentQuestion.getRightAnswer())) {
                other_choice1.setTextColor(Color.GREEN);
            } else if(other_choice2.getText().equals(currentQuestion.getRightAnswer())) {
                other_choice2.setTextColor(Color.GREEN);
            }
            binding.questionTextview.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.question_view_wrong));
            animBlink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
            binding.questionTextview.startAnimation(animBlink);
            wrongAnswers += 1;

            answerWrong();
            switch (wrongAnswers) {
                case 1:
                    binding.imageView1.setImageResource(R.drawable.ic_error_logo);
                    break;
                case 2:
                    binding.imageView2.setImageResource(R.drawable.ic_error_logo);
                    break;
                case 3:
                    binding.imageView3.setImageResource(R.drawable.ic_error_logo);
                    break;
            }
            if(wrongAnswers == numOfWrongAnswers) {
                goToQuizCompletionFragment();

            }

        }


        final Handler handler = new Handler();
        final Runnable runnable = () -> {
            button.setTextColor(Color.WHITE);
            other_choice1.setTextColor(Color.WHITE);
            other_choice2.setTextColor(Color.WHITE);
            if(!gone_to_fragment) {

                moveToNextQuestion();
            }
        };

        boolean handler_check = handler.postDelayed(runnable,check_answer_runnable_delay);

        while(handler_check == off) {
            handler.postDelayed(runnable,check_answer_runnable_delay);
        }



    }



}






