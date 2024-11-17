package com.anonstudio.quizpert;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.fragment.app.Fragment;

import com.anonstudio.quizpert.model.ShowDialog;

public class ClosedButtonListener extends Fragment implements View.OnTouchListener {



    ImageView close_button;
    int close_button_id;
    Context context;
    private ButtonListener listener;
    public ClosedButtonListener(ImageView close_button, int close_button_id, Context context,ButtonListener listener) {
        this.close_button = close_button;
        this.close_button_id = close_button_id;
        this.context = context;
        this.listener = listener;


    }



    public interface ButtonListener  {
        void setClosedButtonCode(int CLOSED_BUTTON_CODE);

    }



    @Override
    public boolean onTouch(View view, MotionEvent event) {

        switch(event.getAction()){

            case MotionEvent.ACTION_DOWN:
                // touch down code
                close_button.setImageResource(R.drawable.dark_close_buttonxxxhdpi);
                break;

            case MotionEvent.ACTION_MOVE:
                // touch move code
                break;

            case MotionEvent.ACTION_UP:
                // touch up code

                close_button.setImageResource(R.drawable.close_buttonxxxhdpi);

                if(close_button_id == R.id.close_chapter_popup) {

                    listener.setClosedButtonCode(100);
                }
                else  {
                    listener.setClosedButtonCode(200);
                }


                break;
        }
        return true;
    }


}
