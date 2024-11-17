package com.anonstudio.quizpert;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.work.impl.model.Preference;

import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static android.content.ContentValues.TAG;
import static com.anonstudio.quizpert.model.constants.Constants.BoolTable.off;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.*;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.*;

import com.anonstudio.quizpert.model.Ads;
import com.anonstudio.quizpert.model.ChapAttributes;
import com.anonstudio.quizpert.model.Preferences;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;

import java.util.Map;
import java.util.Objects;

import com.facebook.ads.*;


public class QuizCompletion extends Fragment {

    private String correct_answers;
    private String incorrect_answers;
    private String qscore;
    private Preferences preferences;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private CountDownTimer countDownTimer = null;
    private TextView ad_time_remaining_textview;

    private AdRequest adRequest;

    private RewardedInterstitialAd rewardedInterstitialAd;
    private AlertDialog powerUpAlertDialog;
    boolean show_ad = false;
    private int minimum_age = 14;
    String ad_supplier = "admob";


    //private RewardedVideoAd rewardedVideoAd;
    private Ads ads;

    int score = 0;
    String finalChapUnlockBefore;
    String qtable;
    String chapter;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //Fragment was loading twice because QuizActivity was cally fragment tranaction twice from
        //goToQuizCompletion and goToQuizCompletionTwo, this was avoided by using a boolean operater gone_to_fragment set to true
        //when it was initially started. If fragent tranaction was loaded again it would not load because gone_to_fragment would be true.
        //This twice repition caused alertdialog to not close upon initially click, it had to be clicked twice for it to close.
        //Ads also were being repeated.

        ;
        //ads = new Ads(getContext(),getActivity());
        preferences = new Preferences(getContext(),APP_PREF_FILE);
        sp = preferences.getPreferenceObject();
        editor = preferences.getPreferenceEditorObject();

        if(sp.getInt("usra",eleven) > minimum_age) {
            //All ages ads
            initAllAgesAd();
            //ads.initAllAgesAd();

        } else {
            //children ads which comply with Coppa.
            initChildCoppaAd();
            //ads.initChildCoppaAd();
        }


        loadRewardedInterstitialAd();
        //ads.loadInterstitialAd();

        assert getArguments() != null;



        qtable = getArguments().getString(getContext().getString(R.string.quiz_table_pref_key));
        chapter = getArguments().getString(Chapter);
        ChapAttributes chapAttributes = new ChapAttributes(getActivity().getApplicationContext(), chapter, qtable);
        score = getArguments().getInt(Quiz_Score);
        final int ques_answered = getArguments().getInt(Quiz_Answered);

        View view;
        Button riskIt = null;

        String chapUnlockBefore = chapAttributes.goToChapterUnlockedBefore();



        if (score >= quizPassed) {
            view = inflater.inflate(R.layout.fragment_quiz_completion, container, false);
            if(!(sp.getBoolean("trophy_dialog_shown",false))) {
                if (score == fullMarks && !sp.getBoolean(chapUnlockBefore, false)) {

                    editor.putBoolean(chapUnlockBefore, true);

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    LayoutInflater inflater_trophy = LayoutInflater.from(getActivity());
                    View dialogView = inflater_trophy.inflate(R.layout.trophy_show, null);
                    builder.setView(dialogView);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.setCancelable(false);
                    alertDialog.show();
                    alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                    dialogView.findViewById(R.id.collect_trophy).setOnClickListener(v -> {
                        alertDialog.dismiss();
                        editor.putBoolean("trophy_dialog_shown", true).apply();
                    });
                }
            }


            riskIt = view.findViewById(R.id.riskIt);
            Animation anmi = AnimationUtils.loadAnimation(getContext(),R.anim.bounce);
            ImageView imageView = view.findViewById(R.id.results_image);
            imageView.startAnimation(anmi);

        } else {
            view = inflater.inflate(R.layout.fragment_quiz_failed, container, false);
        }

        if(sp.getInt("dicePowerUp",zero) > 0  || sp.getInt("explosionPowerUp",zero) > 0 || sp.getInt("mrUnreliablePowerUp",zero) > 0) {
            show_ad = false;
        } else {
            show_ad = true;

        }



        TextView quizScoreTV = view.findViewById(R.id.quizScoreTV);
        TextView correct = view.findViewById(R.id.numofcorrect);
        TextView incorrect = view.findViewById(R.id.numofincorrect);
        Button finish = view.findViewById(R.id.finish);
        Button retry =  view.findViewById(R.id.retry);





    //found here and in hardquizactivity
       boolean chapUnlockedBefore = sp.getBoolean(chapUnlockBefore,false);


        if(chapUnlockedBefore && score >= quizPassed) {
            riskIt.setVisibility(View.GONE);
            if(retry != null) {
                retry.setVisibility(View.GONE);
            }

        }


        try {

                String hold_score = String.valueOf((double) score / ques_answered * maxPercentage);
                if(hold_score.length() > 5) {
                    hold_score = hold_score.substring(0,5);
                }
                if(hold_score.endsWith(".0")) {
                    hold_score = hold_score.substring(0,2);
                }
                if(hold_score.endsWith(".")) {
                    hold_score = hold_score.substring(0,1);
                }

                if(score == fullMarks && ques_answered == fullMarks){
                    hold_score = String.valueOf(maxPercentage);
                }

                qscore = hold_score + " %";
           // }

            if (qscore.equals("NaN %")) { qscore = "0 %";}




            correct_answers = String.valueOf(score);
            incorrect_answers = String.valueOf(ques_answered - score);

            quizScoreTV.setText(qscore);
            correct.setText(correct_answers);
            incorrect.setText(incorrect_answers);



        } catch(Exception e) {
            e.printStackTrace();
        }



        try {
            String finalChapUnlockBefore1 = chapUnlockBefore;
            riskIt.setOnClickListener(v -> {
                dismissAlertDialog();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater1 = LayoutInflater.from(getActivity());
                View dialogView = inflater1.inflate(R.layout.risk_it_all_popup,null);
                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                dialogView.findViewById(R.id.close_risk_it_all_popup).setOnClickListener(v1 -> alertDialog.dismiss());

                dialogView.findViewById(R.id.proceed_button).setOnClickListener(v12 -> {

                    Intent gotoHardQuiz = new Intent(getActivity(), HardQuizActivity.class);
                    gotoHardQuiz.putExtra(getString(R.string.quiz_table_pref_key), qtable);
                    gotoHardQuiz.putExtra(Chapter, chapter);
                    setChapUnlock(score, finalChapUnlockBefore1);
                    Objects.requireNonNull(getActivity()).finish();
                    startActivity(gotoHardQuiz);
                    ((Activity) Objects.requireNonNull(getActivity())).overridePendingTransition(0, 0);
                });

            });
        } catch(NullPointerException e){
            e.printStackTrace();
        }


        finalChapUnlockBefore = chapUnlockBefore;
        finish.setOnClickListener(v -> {
            dismissAlertDialog();
            editor.putBoolean("finish_clicked",true).apply();
            if(sp.getBoolean("quizAdShown",false)) {
                goToCategorySelectionActivity();
            }
            //ads.showInterstitialAd();
            if(!(sp.getBoolean("finish_interstitial",false))) {
                goToCategorySelectionActivity();
            }

        });

        try {
            retry.setOnClickListener(v -> {
                dismissAlertDialog();

                editor.putBoolean("finish_clicked", true).apply();
                if (sp.getBoolean("quizAdShown", false)) {
                    retryQuiz();
                }
                //ads.showInterstitialAd();
                if (!(sp.getBoolean("finish_interstitial", false))) {
                    retryQuiz();
                }

            });
        } catch(Exception ignored) {
        }

        return view;




    }

    private void retryQuiz() {
        Intent goToQuiz = new Intent(getActivity(), QuizActivity.class);
        goToQuiz.putExtra(getString(R.string.quiz_table_pref_key), qtable);
        goToQuiz.putExtra(Chapter, chapter);
        Objects.requireNonNull(getActivity()).finish();
        ((Activity) Objects.requireNonNull(getActivity())).overridePendingTransition(0,0);
        startActivity(goToQuiz);
        ((Activity) Objects.requireNonNull(getActivity())).overridePendingTransition(0,0);
    }

    private void goToCategorySelectionActivity() {
        Intent backtoCategory = new Intent(getActivity(),CategorySelection.class);
        setChapUnlock(score, finalChapUnlockBefore);
        Objects.requireNonNull(getActivity()).finish();
        ((Activity) Objects.requireNonNull(getActivity())).overridePendingTransition(0,0);
        startActivity(backtoCategory);
        ((Activity) Objects.requireNonNull(getActivity())).overridePendingTransition(0,0);
    }



   /* private void goToChapterActivity() {
        Intent bcktoChapters = new Intent(getActivity(),Chapter.class);
        bcktoChapters.putExtra(getString(R.string.quiz_table_pref_key),qtable);
        setChapUnlock(score, finalChapUnlockBefore);
        Objects.requireNonNull(getActivity()).finish();
        startActivity(bcktoChapters);
        ((Activity) Objects.requireNonNull(getActivity())).overridePendingTransition(0,0);
    }

*/


    private void dismissAlertDialog() {
        if(powerUpAlertDialog != null) {
            powerUpAlertDialog.dismiss();
            if(countDownTimer != null) {
                countDownTimer.cancel();
            }
        }
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(six * countdown_timer_interval, countdown_timer_interval) {
            @Override
            public void onTick(long millisUntilFinished) {
                ad_time_remaining_textview.setText(getString(R.string.video_starting, millisUntilFinished / countdown_timer_interval));

            }

            @Override

            public void onFinish() {
                powerUpAlertDialog.dismiss();
                editor.putBoolean("rewardedAd",true).apply();



                if(ad_supplier.equals("admob")) {
                    showRewardedInterstitialAd();
                }
            }
        };
        countDownTimer.start();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(countDownTimer != null) {
            countDownTimer.cancel();
        }

    }





    @Override
    public void onPause() {
        super.onPause();

        if(powerUpAlertDialog != null) {
            powerUpAlertDialog.dismiss();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!sp.getBoolean("power_up_dialog_shown",false)) {
            if (sp.getBoolean("AD_SHOWN", false)) {

                AlertDialog.Builder showPowerUpAlertDialogBuilder = new AlertDialog.Builder(getActivity());
                LayoutInflater show_inflater_powerUpDialog = LayoutInflater.from(getActivity());
                View showPowerUpDialogView = show_inflater_powerUpDialog.inflate(R.layout.show_powerup, null);
                ImageView imageView;
                imageView = showPowerUpDialogView.findViewById(R.id.powerup_image);

                if (sp.getInt("dicePowerUp", zero) > 0) {
                    imageView.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.dice_glow));
                } else if (sp.getInt("explosionPowerUp", zero) > 0) {
                    imageView.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.explosion_glow));
                } else if (sp.getInt("mrUnreliablePowerUp", zero) > 0) {
                    imageView.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.cool_guy_glow));
                }


                showPowerUpAlertDialogBuilder.setView(showPowerUpDialogView);
                AlertDialog showPowerUpAlertDialog = showPowerUpAlertDialogBuilder.create();
                showPowerUpAlertDialog.show();
                showPowerUpAlertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


                final Handler handler = new Handler();
                final Runnable runnable = () -> {
                    showPowerUpAlertDialog.dismiss();
                    editor.putBoolean("power_up_dialog_shown", true).apply();
                };


                handler.postDelayed(runnable, 2500);

            }
        }

        if(sp.getBoolean("finish_clicked",false)) {
            dismissAlertDialog();
            goToCategorySelectionActivity();
        }





    }


    public void initChildCoppaAd()  {
        RequestConfiguration configBuilder =  MobileAds.getRequestConfiguration()
                .toBuilder()
                .setTagForChildDirectedTreatment(RequestConfiguration.TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE)
                .setMaxAdContentRating(RequestConfiguration.MAX_AD_CONTENT_RATING_G)
                .build();
        MobileAds.setRequestConfiguration(configBuilder);
        MobileAds.initialize(getContext(), initializationStatus -> {
        });
        adRequest = new AdRequest.Builder().build();

    }

    public void initAllAgesAd() {

        MobileAds.initialize(getContext(), initializationStatus -> {

        });
        adRequest = new AdRequest.Builder().build();
    }


/*    public void loadFacebookRewardedAd() {
        rewardedVideoAd = new RewardedVideoAd(getActivity().getApplicationContext(), "YOUR_PLACEMENT_ID");
        RewardedVideoAdListener rewardedVideoAdListener = new RewardedVideoAdListener() {

            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {
                Log.e(TAG, "Rewarded video ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Rewarded video ad is loaded and ready to be displayed
                ad_supplier = "facebook";
                loadADialog();
                Log.d(TAG, "Rewarded video ad is loaded and ready to be displayed!");
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Rewarded video ad clicked
                Log.d(TAG, "Rewarded video ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Rewarded Video ad impression - the event will fire when the
                // video starts playing
                Log.d(TAG, "Rewarded video ad impression logged!");
            }

            @Override
            public void onRewardedVideoCompleted() {
                // Rewarded Video View Complete - the video has been played to the end.
                // You can use this event to initialize your reward
                Log.d(TAG, "Rewarded video completed!");

                // Call method to give reward
                // giveReward();
            }

            @Override
            public void onRewardedVideoClosed() {
                // The Rewarded Video ad was closed - this can occur during the video
                // by closing the app, or closing the end card.
                Log.d(TAG, "Rewarded video ad closed!");
            }
        };
        rewardedVideoAd.loadAd(
                rewardedVideoAd.buildLoadAdConfig()
                        .withAdListener(rewardedVideoAdListener)
                        .build());

    }*/


    private void loadADialog() {
        if (!(sp.getBoolean("rewardedAd", false)) && show_ad) {
            AlertDialog.Builder powerUpAlertDialogBuilder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater_powerUpDialog = LayoutInflater.from(getActivity());
            View powerUpDialogView = inflater_powerUpDialog.inflate(R.layout.ad_dialog, null);
            powerUpAlertDialogBuilder.setView(powerUpDialogView);
            powerUpAlertDialog = powerUpAlertDialogBuilder.create();
            powerUpAlertDialog.show();
            powerUpAlertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

            ad_time_remaining_textview = powerUpDialogView.findViewById(R.id.ad_timer_remaining);

            startTimer();
            powerUpDialogView.findViewById(R.id.close_ad_dialog).setOnClickListener(v -> {
                editor.putBoolean("rewardedAd", true).apply();
                dismissAlertDialog();

            });

            powerUpAlertDialog.setOnDismissListener(dialog -> {
                editor.putBoolean("rewardedAd", true).apply();
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                }
            });
        }



    }


    public void loadRewardedInterstitialAd() {

        // Use the test ad unit ID to load an ad.
        RewardedInterstitialAd.load(getContext(), "ca-app-pub-2036687210639655/7289602624",
                new AdRequest.Builder().build(),  new RewardedInterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(RewardedInterstitialAd ad) {
                        if(rewardedInterstitialAd == null) {

                            rewardedInterstitialAd = ad;


                            //Loads AlertDialog informing user that an ad is about to open
                            ad_supplier = "admob";
                            loadADialog();



                        }



                        rewardedInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            //Called when the ad failed to show full screen content.
                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                rewardedInterstitialAd = null;

                            }

                            //Called when ad showed the full screen content.
                            @Override
                            public void onAdShowedFullScreenContent() {
                                rewardedInterstitialAd = null;

                            }

                            //Called when full screen content is dismissed.
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                rewardedInterstitialAd = null;
                            }
                        });









                    }
                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {

                        rewardedInterstitialAd = null;
                    }
                });
    }


    private void adReward() {
        int powerUpEarned = (int) (Math.random() * three);
        if (powerUpEarned == 0) { powerUpEarned = 1; }
        PowerUpsEarned(one, powerUpEarned);
    }

    public void showRewardedInterstitialAd() {
        if(rewardedInterstitialAd != null) {
            Activity activityContext = getActivity();
            rewardedInterstitialAd.show(
                    activityContext,
                    rewardItem -> {
                        // Handle the reward.

                       adReward();

                    });
        }

    }

    public void PowerUpsEarned(int reward_amount, int powerUpType){




        editor.putBoolean("AD_SHOWN",true).apply();
        switch(powerUpType) {

            case dicePowerUp:
               // power_up_drawable = R.drawable.dice_glow;
                int dicePowerUp = sp.getInt("dicePowerUp", zero);
                editor.putInt("dicePowerUp", dicePowerUp + reward_amount);
                break;
            case explosionPowerUp:
               // power_up_drawable = R.drawable.explosion_glow;
                int explosionPowerUp = sp.getInt("explosionPowerUp", zero);
                editor.putInt("explosionPowerUp", explosionPowerUp + reward_amount);
                break;
            case mrUnreliablePowerUp:
               // power_up_drawable = R.drawable.cool_guy_glow;
                int mrUnreliablePowerUp = sp.getInt("mrUnreliablePowerUp", zero);
                editor.putInt("mrUnreliablePowerUp", mrUnreliablePowerUp + reward_amount);
                break;
        }
        editor.apply();




}



    private void setChapUnlock(int score, String chapUnlockBefore) { if(score >= quizPassed){ editor.putBoolean(chapUnlockBefore,true).apply(); } }









}


