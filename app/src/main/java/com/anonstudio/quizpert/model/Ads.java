package com.anonstudio.quizpert.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.anonstudio.quizpert.HardQuizCompletion;
import com.anonstudio.quizpert.MainActivity;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

import static android.content.ContentValues.TAG;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.SET_RISK_IT_ALL_GEMS;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.dicePowerUp;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.explosionPowerUp;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.mrUnreliablePowerUp;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.one;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.three;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.zero;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.APP_PREF_FILE;


public class Ads implements OnUserEarnedRewardListener {
    private Context context;
    private Activity activity;
    private AdRequest adRequest;

    private InterstitialAd mInterstitialAd;




    public Ads(Context context, Activity activity){

        this.context = context;
        this.activity = activity;
    }

    public void initChildCoppaAd()  {
        RequestConfiguration configBuilder =  MobileAds.getRequestConfiguration()
                .toBuilder()
                .setTagForChildDirectedTreatment(RequestConfiguration.TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE)
                .setMaxAdContentRating(RequestConfiguration.MAX_AD_CONTENT_RATING_G)
                .build();
        MobileAds.setRequestConfiguration(configBuilder);


        MobileAds.initialize(context, initializationStatus -> {});
        adRequest = new AdRequest.Builder().build();

    }

    public void initAllAgesAd() {
        //Ads for all ages

        MobileAds.initialize(context, initializationStatus -> { });
        adRequest = new AdRequest.Builder().build();
    }





    public void loadInterstitialAd() {
        InterstitialAd.load(context, "ca-app-pub-2036687210639655/8910855800", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        if(mInterstitialAd == null) {
                            mInterstitialAd = interstitialAd;

                        }


                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when fullscreen content is dismissed.
                                mInterstitialAd = null;

                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when fullscreen content failed to show.
                                mInterstitialAd = null;

                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when fullscreen content is shown.
                                // Make sure to set your reference to null so you don't
                                // show it a second time.
                                mInterstitialAd = null;

                            }
                        });


                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error

                        mInterstitialAd = null;
                    }
                });


    }



    @Override
    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
    }


    public void showInterstitialAd() {
    Preferences preferences = new Preferences(context,APP_PREF_FILE);
    SharedPreferences.Editor editor = preferences.getPreferenceEditorObject();
        if (mInterstitialAd != null) {
            mInterstitialAd.show(activity);
            editor.putBoolean("finish_interstitial",true).apply();
        }
    }

}
