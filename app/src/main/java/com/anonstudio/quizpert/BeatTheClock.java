package com.anonstudio.quizpert;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import com.anonstudio.quizpert.databinding.ActivityQuizBinding;
import com.anonstudio.quizpert.databinding.ActivityQuizHardBinding;
import com.anonstudio.quizpert.model.ChapAttributes;
import com.anonstudio.quizpert.model.Gems;
import com.anonstudio.quizpert.model.Preferences;
import com.anonstudio.quizpert.model.Questions;
import com.anonstudio.quizpert.model.QuizDataBaseHelper;
import com.anonstudio.quizpert.model.ShowDialog;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.anonstudio.quizpert.model.QuizConsts.QuestionsTable.ARTS_TABLE_NAME;
import static com.anonstudio.quizpert.model.QuizConsts.QuestionsTable.GEO_TABLE_NAME;
import static com.anonstudio.quizpert.model.QuizConsts.QuestionsTable.HIS_TABLE_NAME;
import static com.anonstudio.quizpert.model.QuizConsts.QuestionsTable.MATH_TABLE_NAME;
import static com.anonstudio.quizpert.model.QuizConsts.QuestionsTable.MUS_TABLE_NAME;
import static com.anonstudio.quizpert.model.QuizConsts.QuestionsTable.SCI_TABLE_NAME;
import static com.anonstudio.quizpert.model.constants.Constants.BoolTable.off;
import static com.anonstudio.quizpert.model.constants.Constants.BoolTable.on;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.POWER_UP_REQUEST_CODE;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.check_answer_runnable_delay;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.countdown_timer_interval;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.countdown_timer_start;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.countdown_timer_warning;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.eight;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.numOfWrongAnswers;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.one;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.quizLength;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.seven;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.six;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.zero;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.APP_PREF_FILE;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.chapter1;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.chapter2;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.chapter3;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.chapter4;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.chapter5;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.chapter6;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.chapter7;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.chapter8;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.hardChap1;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.hardChap2;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.hardChap3;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.hardChap4;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.hardChap5;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.hardChap6;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.hardChap7;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.hardChap8;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.quiz_red_color;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.start_blue_color;


public class BeatTheClock extends AppCompatActivity implements ShowDialog.ShowDialogListener {

    private ActivityQuizHardBinding binding;
    private ChapAttributes chapAttributes;
    private final Preferences preferences =  new Preferences(BeatTheClock.this, APP_PREF_FILE);
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private final String[] quiz_chapters = {chapter1,chapter2,chapter3,chapter4,chapter5,chapter6,chapter7,chapter8,
                                            hardChap1,hardChap2,hardChap3,hardChap4,hardChap5,hardChap6,hardChap7,hardChap8};
    private final String[] quiz_table_list = {MUS_TABLE_NAME,MATH_TABLE_NAME,SCI_TABLE_NAME,HIS_TABLE_NAME,ARTS_TABLE_NAME,GEO_TABLE_NAME};
    private List<Questions> questionsList;
    private List<Questions> hold_questions_list;
    private int questionsListSize;
    private int numOfQuestionsShown;

    private Questions currentQuestion;
    private int questionsAnswered = 0;
    private List<String> hold_questions_answers = new ArrayList<>();
    private CountDownTimer countDownTimer = null;
    MediaPlayer player = null;
    MediaPlayer soundPlayer = null;

    private int musicList[] = {R.raw.air, R.raw.carnival_of_animals, R.raw.chopin, R.raw.gnossienne, R.raw.gymnopedie1};

    final int music_min = 0;
    final int music_max = 4;
    private Animation animBlink;

    int quizScore = 0;
    int highQuizScore = 0;
    //private AdRequest adRequest;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_hard);
        binding = ActivityQuizHardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        /*MobileAds.initialize(BeatTheClock.this, initializationStatus -> {

        });
        adRequest = new AdRequest.Builder().build();
        loadRewardedInterstitialAd();*/

        sp = preferences.getPreferenceObject();
        editor = preferences.getPreferenceEditorObject();

        highQuizScore = sp.getInt("survival_mode_highscore",zero);

        play();

        getDataBaseQuestions();
        moveToNextQuestion();




        binding.hqbutton1.setOnClickListener(v -> { cancelTimer(); checkAnswer(binding.hqbutton1,binding.hqbutton2,binding.hqbutton3); });

        binding.hqbutton2.setOnClickListener(v -> { cancelTimer(); checkAnswer(binding.hqbutton2,binding.hqbutton1,binding.hqbutton3); });

        binding.hqbutton3.setOnClickListener(v -> { cancelTimer(); checkAnswer(binding.hqbutton3,binding.hqbutton2,binding.hqbutton1); });



    }

    private void getDataBaseQuestions() {

        QuizDataBaseHelper quizDBHelper = new QuizDataBaseHelper(this);
        questionsList = quizDBHelper.getAllQuizQuestions();


        questionsListSize = questionsList.size();
        Collections.shuffle(questionsList);
        quizDBHelper.close();

    }

    private void moveToNextQuestion() {
        binding.hqquestionTextview.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.textview_circle));
        binding.hqquestionTextview.clearAnimation();
        //if questionList holds ten questions and all are shown, an indexoutofbounds exception will be thrown.
        if (questionsAnswered < questionsListSize) {

            //binding.questionCounter.setText(String.valueOf((questionsAnswered + one) + " / " + String.valueOf(quizLength + one)));
            currentQuestion = questionsList.get(numOfQuestionsShown);
            binding.hqquestionTextview.setText(currentQuestion.getQuestion());

            hold_questions_answers = new ArrayList<>();


            hold_questions_answers.add(currentQuestion.getAnswer1());
            hold_questions_answers.add(currentQuestion.getAnswer2());
            hold_questions_answers.add(currentQuestion.getAnswer3());


            Collections.shuffle(hold_questions_answers);


            binding.hqbutton1.setText(hold_questions_answers.get(0));
            binding.hqbutton2.setText(hold_questions_answers.get(1));
            binding.hqbutton3.setText(hold_questions_answers.get(2));
            startTimer();
            //returnAnswerBox();

            numOfQuestionsShown++;
        } else {
            ShowDialog showDialog = new ShowDialog(BeatTheClock.this,R.layout.quiz_over, R.id.close_quiz_over_popup, quizScore, highQuizScore);
            showDialog.getShowDialog();
            //beat the entire quiz();
            //IndexBounds
            //goToQuizCompletionFragment();

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
                // closeUnreliableAlertDialog();

                cancelTimer();
                showQuizOverDialog();
                //finishActivity();
                //hard_quiz_passed = false;
                //  goToHardQuizCompletion(hard_quiz_passed);
            }
        };
         countDownTimer.start();

    }

    private void cancelTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private void showQuizOverDialog() {

        ShowDialog quizOverDialog = new ShowDialog(BeatTheClock.this,R.layout.game_continue,R.id.close_continue_popup,quizScore,sp.getInt("survival_mode_highscore",zero));
        quizOverDialog.getShowDialog();
    }



    private void questionAnswered() {
        questionsAnswered++;
    }



    private void checkAnswer(Button button, Button other_choice1, Button other_choice2) {

        if (button.getText().equals(currentQuestion.getRightAnswer())) {

            button.setTextColor(Color.GREEN);
            getSoundState(R.raw.correct_answer);
            binding.hqquestionTextview.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.question_view_correct));
            animBlink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.quick_blink);
            binding.hqquestionTextview.startAnimation(animBlink);
            quizScore += 1;
            if(quizScore > highQuizScore) {
                editor.putInt("survival_mode_highscore",quizScore).apply();
            }
           

            questionAnswered();

            final Handler handler = new Handler();
            final Runnable runnable = () -> {
                button.setTextColor(Color.WHITE);
                other_choice1.setTextColor(Color.WHITE);
                other_choice2.setTextColor(Color.WHITE);
                moveToNextQuestion();

            };

            boolean handler_check = handler.postDelayed(runnable,check_answer_runnable_delay);

            while(handler_check == off) {
                handler.postDelayed(runnable,check_answer_runnable_delay);
            }

        } else {
            button.setTextColor(Color.RED);
            getSoundState(R.raw.wrong_answer);
            if(other_choice1.getText().equals(currentQuestion.getRightAnswer())) {
                other_choice1.setTextColor(Color.GREEN);
            } else if(other_choice2.getText().equals(currentQuestion.getRightAnswer())) {
                other_choice2.setTextColor(Color.GREEN);
            }
            binding.hqquestionTextview.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.question_view_wrong));
            animBlink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
            binding.hqquestionTextview.startAnimation(animBlink);

            final Handler handler = new Handler();
            final Runnable runnable = () -> {
                button.setTextColor(Color.WHITE);
                other_choice1.setTextColor(Color.WHITE);
                other_choice2.setTextColor(Color.WHITE);
                showQuizOverDialog();

            };

            boolean handler_check = handler.postDelayed(runnable,check_answer_runnable_delay);

            while(handler_check == off) {
                handler.postDelayed(runnable,check_answer_runnable_delay);
            }



        }






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
        stopPlayer();

      //  killQuizActivity();
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


    @Override
    public void setMusicPlayer(int MUSIC_PLAYER_CODE) {

        switch(MUSIC_PLAYER_CODE) {
            case six:
                moveToNextQuestion();
                break;
            case seven:
                stopPlayer();
                Intent intent = new Intent(BeatTheClock.this, MainActivity.class);
                finish();
                startActivity(intent);
                break;
            case eight:
                stopPlayer();
                Intent play_again = new Intent(BeatTheClock.this, BeatTheClock.class);
                finish();
                startActivity(play_again);
                break;

        }

    }

    @Override
    public void setTransactionCode(int TRANSACTION_CODE) {

    }

    @Override
    public void birthDateCompleted(int BIRTH_DATE_CODE) {

    }
}
