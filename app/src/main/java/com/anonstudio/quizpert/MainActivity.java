package com.anonstudio.quizpert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.anonstudio.quizpert.model.Ads;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import static android.content.ContentValues.TAG;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.DEFAULT_GEMS_KEY;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.eight;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.five;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.four;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.nine;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.one;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.seven;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.six;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.ten;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.three;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.two;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.zero;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.*;
import static com.anonstudio.quizpert.model.constants.Constants.BoolTable.*;

import com.anonstudio.quizpert.databinding.ActivityMainBinding;
import com.anonstudio.quizpert.model.MediaPlayerService;
import com.anonstudio.quizpert.model.Preferences;
import com.anonstudio.quizpert.model.ShowDialog;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class MainActivity extends AppCompatActivity implements ShowDialog.ShowDialogListener {

    private ActivityMainBinding binding;
    private Animation animBlink,animRotate,animBounce;
    MediaPlayerService media_service;
    boolean media_bound = off;
    Intent svc;
    private final Preferences preferences = new Preferences(MainActivity.this, APP_PREF_FILE);
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    int heart_amount;
    int gem_amount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        sp = preferences.getPreferenceObject();
        editor = preferences.getPreferenceEditorObject();
        getHeartsAmount();
        getGemsAmount();

        //old guide
        //boolean guide_shown = sp.getBoolean(getString(R.string.user_guide_key),off);
        boolean updated_guide = sp.getBoolean(getString(R.string.updated_menus_pref_key),off);

        if(!updated_guide) {
            ShowDialog showGuideDialog = new ShowDialog(MainActivity.this, R.layout.updated_menu_helper, R.id.close_updated_menus_popup);
            showGuideDialog.getShowDialog();

            editor.putBoolean(getString(R.string.updated_menus_pref_key), on).apply();
        }

                //Implementaion of neutral age screen
//      /*  if(!sp.getBoolean("avc", false)) {
//            ShowDialog neutral_age_screen_dialog = new ShowDialog(MainActivity.this, R.layout.neutral_age_screen, R.id.submit_age);
//            neutral_age_screen_dialog.getShowDialog();
//        }*/





        animBlink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        animRotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        animBounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);

        binding.playButton.startAnimation(animBlink);
        binding.quizpertIcon.startAnimation(animBounce);
        getMusicState();



        binding.scoreIcon.setOnClickListener(v -> {
            ShowDialog showDialog = new ShowDialog(MainActivity.this,R.layout.quizpert_table,R.id.close_score_menu);
            showDialog.getShowDialog();
        });

        //new rules dialog
        binding.rulesIcon.setOnClickListener(v -> {
            ShowDialog showDialog = new ShowDialog(MainActivity.this,R.layout.rules_popup_dialog,R.id.close_rules_popup);
            showDialog.getV2ShowDialog();
        });


        binding.settingsIcon.setOnClickListener(v -> {
            ShowDialog showDialog = new ShowDialog(MainActivity.this,R.layout.settings,R.id.close_settings_popup);
            showDialog.getShowDialog();

        });



        binding.store.setOnClickListener(v -> {
            ShowDialog showDialog = new ShowDialog(MainActivity.this,R.layout.store,R.id.close_store_popup,MainActivity.this);
            showDialog.getShowDialog();
            //is finishng prevents can not perform this action illegall state exceptio with opening fragment
          /*  if(!isFinishing()) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.main_frag_container, StoreMenuFragment.class, null)
                        .addToBackStack("store")
                        .commit();
            }*/
        });

        //new
        binding.playButton.setOnClickListener(v -> {

            ShowDialog showDialog = new ShowDialog(MainActivity.this,R.layout.modes_popup,R.id.close_mode_popup);
            showDialog.getShowDialog();
        });



    }

    private void getHeartsAmount() {
        heart_amount = sp.getInt("hearts",two);

        for(int i = 0; i < String.valueOf(heart_amount).length(); i++) {

            String num = String.valueOf(String.valueOf(heart_amount).charAt(i));

            switch(i) {
                case zero:
                    setImage(Integer.parseInt(num),binding.heartNumOne);

                    break;
                case one:
                    setImage(Integer.parseInt(num),binding.heartNumTwo);

                    break;
                case two:
                    setImage(Integer.parseInt(num),binding.heartNumThree);
                    break;
                case three:
                    setImage(Integer.parseInt(num), binding.heartNumFour);
                    break;
            }

        }



    }

    private void getGemsAmount() {


        gem_amount = sp.getInt(GEMS_PREF_FILE, DEFAULT_GEMS_KEY);


        for(int i = 0; i < String.valueOf(gem_amount).length(); i++) {

            String num = String.valueOf(String.valueOf(gem_amount).charAt(i));

            switch(i) {
                case zero:
                    setImage(Integer.parseInt(num),binding.gemsNumOne);
                    break;
                case one:

                    setImage(Integer.parseInt(num),binding.gemsNumTwo);
                    break;
                case two:
                    setImage(Integer.parseInt(num),binding.gemsNumThree);

                    break;
                case three:

                    setImage(Integer.parseInt(num), binding.gemsNumFour);
                    break;
            }

        }


    }

    private void setImage(int number, ImageView view) {
        switch(number) {
            case zero:
                view.setImageResource(R.drawable.zeroxxxhdpi);
                break;
            case one:
                view.setImageResource(R.drawable.onexxxhdpi);
                break;
            case two:
                view.setImageResource(R.drawable.twoxxxhdpi);
                break;
            case three:
                view.setImageResource(R.drawable.threexxxhdpi);
                break;
            case four:
                view.setImageResource(R.drawable.fourxxxhdpi);
                break;
            case five:
                view.setImageResource(R.drawable.fivexxxhdpi);
                break;
            case six:
                view.setImageResource(R.drawable.sixxxxhdpi);
                break;
            case seven:
                view.setImageResource(R.drawable.sevenxxxhdpi);
                break;
            case eight:
                view.setImageResource(R.drawable.eightxxxhdpi);
                break;
            case nine:
                view.setImageResource(R.drawable.ninexxxhdpi);
                break;



        }
    }



    private void startMusicService() {
        svc = new Intent(this,MediaPlayerService.class);
        startService(svc);
        Intent pause_player = new Intent(this,MediaPlayerService.class);
        bindService(pause_player,connection, Context.BIND_AUTO_CREATE);

    }

    private void getMusicState() {

        boolean music_state = sp.getBoolean(getString(R.string.music_pref_key), on);
        if(music_state) { startMusicService();}
    }

    @Override
    public void setMusicPlayer(int MUSIC_PLAYER_CODE) {
        switch (MUSIC_PLAYER_CODE) {
            case one:

                startMusicService();

                break;
            case two:

                media_service.onStop();
                stopService(svc);
                onStop();
                break;
            case three:
                Intent intent = new Intent(MainActivity.this, CategorySelection.class);
                finish();
                overridePendingTransition(0,0);
                startActivity(intent);
                overridePendingTransition(0,0);
                break;
            case four:
                Intent goToQuiz = new Intent(MainActivity.this, BeatTheClock.class);
                finish();
                overridePendingTransition(0,0);
                startActivity(goToQuiz);
                overridePendingTransition(0,0);
                break;
            case five:
                Intent goToTournaments = new Intent(MainActivity.this, Tournaments.class);
                finish();
                overridePendingTransition(0,0);
                startActivity(goToTournaments);
                overridePendingTransition(0,0);
                break;
            case nine:
                getHeartsAmount();
                break;
            case ten:
                getGemsAmount();
                break;

        }
    }

    @Override
    public void setTransactionCode(int TRANSACTION_CODE) {}

    @Override
    public void birthDateCompleted(int BIRTH_DATE_CODE) {

    }

    @Override
    protected void onPause() {
        super.onPause();

        try{
            if(media_bound) {
                media_service.onPause();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MediaPlayerService.LocalBinder binder = (MediaPlayerService.LocalBinder) service;
            media_service = binder.getService();
            media_bound = on;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            media_bound = off;
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        try {
            media_bound = off;
            unbindService(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onResume() {
        super.onResume();

        if (sp.getBoolean(getString(R.string.music_pref_key),on)) {

            if (svc != null) { svc = new Intent(this, MediaPlayerService.class); startService(svc); }

            try {
                Intent pause_player = new Intent(this, MediaPlayerService.class);
                bindService(pause_player, connection, Context.BIND_AUTO_CREATE);
                media_service.onResume();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}