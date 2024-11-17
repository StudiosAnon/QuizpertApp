package com.anonstudio.quizpert.model;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import com.anonstudio.quizpert.R;

public class MediaPlayerService extends Service {
        private static final String TAG = null;
        MediaPlayer player;
        boolean allow_rebind;
        private final IBinder binder = new LocalBinder();


        public IBinder onBind(Intent arg0) {

            return binder;
        }

        public class LocalBinder extends Binder {
            public MediaPlayerService getService() {
                return MediaPlayerService.this;
            }
        }

        public int onStartCommand(Intent intent, int flags, int startId) {


            player.start();
            player.setLooping(true); // Set looping


            return START_STICKY;
        }


        public boolean onUnbind(Intent arg0) {
            // TODO Auto-generated method stub

            return allow_rebind;
        }

        public void onStop() {

            player.stop();
            player.release();
        }

    public void onResume() {
        if(player == null) {
            player = MediaPlayer.create(this, R.raw.listz);
        }
        try {
            player.start();
        } catch(Exception e) {
            e.printStackTrace();
        }
        }
    public void onPause() {

            player.pause();
        }
    @Override
        public void onDestroy() {
            try {
                player.stop();
                player.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onLowMemory() {

        }


    @Override
    public void onCreate() {
        super.onCreate();

        if(player == null) {
            player = MediaPlayer.create(this, R.raw.listz);
        }



    }
}