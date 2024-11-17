package com.anonstudio.quizpert;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.anonstudio.quizpert.model.ChapAttributes;
import com.anonstudio.quizpert.model.Gems;
import com.anonstudio.quizpert.model.Preferences;
import com.anonstudio.quizpert.model.QuizConsts;
import com.anonstudio.quizpert.model.ShowDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.anonstudio.quizpert.model.QuizConsts.QuestionsTable.ARTS_TABLE_NAME;
import static com.anonstudio.quizpert.model.QuizConsts.QuestionsTable.GEO_TABLE_NAME;
import static com.anonstudio.quizpert.model.QuizConsts.QuestionsTable.HIS_TABLE_NAME;
import static com.anonstudio.quizpert.model.QuizConsts.QuestionsTable.MATH_TABLE_NAME;
import static com.anonstudio.quizpert.model.QuizConsts.QuestionsTable.MUS_TABLE_NAME;
import static com.anonstudio.quizpert.model.QuizConsts.QuestionsTable.SCI_TABLE_NAME;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.CHAP_UNLOCK_REQUEST_CODE;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.CHECK_AVAILABLE_GEMS_CODE;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.GTS_CODE;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.RESTART_ACTIVITY;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.S_CODE;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.chapter_start;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.eight;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.five;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.four;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.one;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.seven;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.six;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.three;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.two;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.zero;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.APP_PREF_FILE;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.Chapter;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.QUIZ_TBL_PREFIX;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.chap;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.chapter1;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.chapter2;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.chapter3;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.chapter4;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.chapter5;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.chapter6;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.chapter7;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.chapter8;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.quiz;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.unlockedChapter1;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.unlockedChapter2;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.unlockedChapter3;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.unlockedChapter4;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.unlockedChapter5;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.unlockedChapter6;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.unlockedChapter7;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.unlockedChapter8;




public class pagefragmenttwo extends Fragment implements ShowDialog.ShowDialogListener, ClosedButtonListener.ButtonListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private final int array_size = 8;
    private final ImageView[] imageViewSet = new ImageView[array_size];


    private final int[] chapterBannerSet = {R.drawable.science_chapter_bannerxxxhdpi,R.drawable.music_chapter_bannerxxxhdpi,R.drawable.history_chapter_bannerxxxhdpi,
    R.drawable.math_chapter_bannerxxxhdpi,R.drawable.art_chapter_bannerxxxhdpi,R.drawable.geography_chapter_bannerxxxhdpi};

    private final int[] locked_science_drawables = {R.drawable.space_iconxxxhdpi,R.drawable.plants_locked_iconxxxhdpi,R.drawable.chemistry_locked_iconxxxhdpi,R.drawable.scientists_locked_iconxxxhdpi,
            R.drawable.geology_locked_iconxxxhdpi,R.drawable.physics_locked_iconxxxhdpi,R.drawable.random_science_locked_iconxxxhdpi,R.drawable.quantum_mechanics_locked_iconxxxhdpi};
    private final int[] locked_music_drawables = {R.drawable.notes_and_scales_iconxxxhdpi,R.drawable.instruments_locked_iconxxxhdpi,R.drawable.composers_locked_iconxxxhdpi,R.drawable.musical_terms_locked_iconxxxhdpi,
            R.drawable.baroque_period_locked_iconxxxhdpi,R.drawable.jazz_locked_iconxxxhdpi,R.drawable.music_random_lockedxxxhdpi,R.drawable.guess_the_singer_locked_iconxxxhdpi};

    private final int[] science_drawables = {R.drawable.space_iconxxxhdpi,R.drawable.plants_iconxxxhdpi,R.drawable.chemistry_iconxxxhdpi,R.drawable.scientists_iconxxxhdpi,
                                        R.drawable.geology_iconxxxhdpi,R.drawable.physics_iconxxxhdpi,R.drawable.random_science_iconxxxhdpi,R.drawable.quantum_mechanics_iconxxxhdpi};
    private final int[] music_drawables = {R.drawable.notes_and_scales_iconxxxhdpi,R.drawable.instruments_iconxxxhdpi,R.drawable.composers_iconxxxhdpi,R.drawable.musical_terms_iconxxxhdpi,
            R.drawable.baroque_period_iconxxxhdpi,R.drawable.jazz_iconxxxhdpi,R.drawable.music_randomxxxhdpi,R.drawable.guess_the_singer_iconxxxhdpi};

    private final int[] history_drawables = {R.drawable.indus_valley_iconxxxhdpi,R.drawable.mesopotamia_iconxxxhdpi,R.drawable.egypt_iconxxxhdpi,R.drawable.china_iconxxxhdpi,
                                            R.drawable.greece_iconxxxhdpi,R.drawable.rome_iconxxxhdpi,R.drawable.tarascan_empire_iconxxxhdpi,R.drawable.history_random_iconxxxhdpi};

    private final int[] locked_history_drawables = {R.drawable.indus_valley_locked_iconxxxhdpi,R.drawable.mesopotamia_locked_iconxxxhdpi,R.drawable.egypt_locked_iconxxxhdpi,R.drawable.china_locked_iconxxxhdpi,
            R.drawable.greece_locked_iconxxxhdpi,R.drawable.rome_locked_iconxxxhdpi,R.drawable.tarascan_empire_locked_iconxxxhdpi,R.drawable.history_random_locked_iconxxxhdpi};


    private final int[] math_drawables = {R.drawable.addition_iconxxxhdpi,R.drawable.subtraction_iconxxxhdpi,R.drawable.multiplication_iconxxxhdpi,R.drawable.division_iconxxxhdpi,
            R.drawable.exponents_iconxxxhdpi,R.drawable.math_random_iconxxxhdpi,R.drawable.algebra_iconxxxhdpi,R.drawable.worded_problems_iconxxxhdpi};

    private final int[] locked_math_drawables = {R.drawable.addition_iconxxxhdpi,R.drawable.subtraction_locked_iconxxxhdpi,R.drawable.multiplication_locked_iconxxxhdpi,R.drawable.division__locked_iconxxxhdpi,
            R.drawable.exponents_locked_iconxxxhdpi,R.drawable.math_random_locked_iconxxxhdpi,R.drawable.algebra_locked_iconxxxhdpi,R.drawable.worded_problems_locked_iconxxxhdpi};

    private final int[] art_drawables = {R.drawable.who_painted_iconxxxhdpi,R.drawable.who_sculpted_iconxxxhdpi,R.drawable.art_termsxxxhdpi,R.drawable.renaissance_artists_iconxxxhdpi,
            R.drawable.baroque_artists_iconxxxhdpi,R.drawable.movie_villains_iconxxxhdpi,R.drawable.movie_dirctors_iconxxxhdpi,R.drawable.random_art_iconxxxhdpi};


    private final int[] locked_art_drawables = {R.drawable.who_painted_locked_iconxxxhdpi,R.drawable.who_sculpted_locked_iconxxxhdpi,R.drawable.art_terms_lockedxxxhdpi,R.drawable.renaissance_artists_locked_iconxxxhdpi,
            R.drawable.baroque_artists_locked_iconxxxhdpi,R.drawable.movie_villains_locked_iconxxxhdpi,R.drawable.movie_directors_locked_iconxxxhdpi,R.drawable.random_art_locked_iconxxxhdpi};


    private final int[] geography_drawables = {R.drawable.americas_iconxxxhdpi,R.drawable.europe_iconxxxhdpi,R.drawable.africa_iconxxxhdpi,R.drawable.asia_iconxxxhdpi,
            R.drawable.oceania_iconxxxhdpi,R.drawable.africaxxxhdpi,R.drawable.asiaxxxhdpi,R.drawable.europexxxhdpi};

    private final int[] locked_geography_drawables = {R.drawable.americas_locked_iconxxxhdpi,R.drawable.europe_locked_iconxxxhdpi,R.drawable.africa_locked_iconxxxhdpi,R.drawable.asia_locked_iconxxxhdpi,
            R.drawable.oceania_locked_iconxxxhdpi,R.drawable.africa_lockedxxxhdpi,R.drawable.asia_lockedxxxhdpi,R.drawable.europe_lockedxxxhdpi};

    private Preferences preferences;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private String[] chapter;
    private boolean chapterPassed = false;
    private String cp;

    private String quiz_table;

    private int tbl_num = 0;
    SendFragmentData sendFragmentData;

    private String tbl_name;
    ImageView closeChapterFragment;

    private List<Boolean> booleanList = new ArrayList<>();


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public pagefragmenttwo() {
        super(R.layout.fragment_page_two);
        // Required empty public constructor
    }

    @Override
    public void setClosedButtonCode(int CLOSED_BUTTON_CODE) {
       if(CLOSED_BUTTON_CODE == 100) {
           returnToCategorySelection();
        }
    }
    /*
    */

   /* class MyCustomTouchListener implements View.OnTouchListener {
        @SuppressLint("ClickableViewAccessibility")
        ImageView close_button;
        int close_button_id;
        public MyCustomTouchListener(ImageView close_button, int close_button_id) {
            this.close_button = close_button;
            this.close_button_id = close_button_id;
        }
        public boolean onTouch(View v, MotionEvent event)
        {
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

                    returnToCategorySelection();

                    break;
            }
            return true;
        }


    }*/

    public interface SendFragmentData {
        void sendTable(String quiz_table);
        void sendTableNum(int tbl_num);
        void sendTablePrefix(String tbl_prefix);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            sendFragmentData = (SendFragmentData) getActivity();
        } catch (Exception ignored) {}

        OnBackPressedCallback callback = new OnBackPressedCallback(false) {
            @Override
            public void handleOnBackPressed() {
                //requireActivity().onBackPressed();
                //return to Main Activity
                returnToCategorySelection();
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }








  /*  public String getFragmentTable() {
        return quiz_table;
    }

    public int getFragmentTblNum() {
        return tbl_num;
    }*/

    public void returnToCategorySelection(){

        Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        preferences = new Preferences(getContext(),APP_PREF_FILE);
        sp = preferences.getPreferenceObject();
        editor = preferences.getPreferenceEditorObject();




        closeChapterFragment = view.findViewById(R.id.close_chapter_popup);
        ClosedButtonListener closedButtonListener = new ClosedButtonListener(
                closeChapterFragment,
                R.id.close_chapter_popup,
                getContext(),this
               );
        closeChapterFragment.setOnTouchListener(closedButtonListener);
       /* closeChapterFragment.setOnClickListener(v ->
        {
            returnToCategorySelection(); });*/




        getChapterPassed();
        putBoolChapters();


        quiz_table = requireArguments().getString(Objects.requireNonNull(getContext()).getString(R.string.quiz_table_pref_key));
        tbl_name = requireArguments().getString(Objects.requireNonNull(QUIZ_TBL_PREFIX)).trim();

        booleanList = boolChapters(quiz_table);
        //chapter = getContext().getResources().getStringArray(R.array.chapters);


        //position is where chapter is located
        //eg.1 space chapter i science topic is - 0
        //eg.2 plants chapter in science topic is - 1
        //eg.3 chemistry chapter in science topic is -2 and so forth

        //CHAPTER IMAGEVIEWS
        ImageView chapter_one = view.findViewById(R.id.chapter_one);
        ImageView chapter_two = view.findViewById(R.id.chapter_two);
        ImageView chapter_three = view.findViewById(R.id.chapter_three);
        ImageView chapter_four = view.findViewById(R.id.chapter_four);
        ImageView chapter_five = view.findViewById(R.id.chapter_five);
        ImageView chapter_six = view.findViewById(R.id.chapter_six);
        ImageView chapter_seven = view.findViewById(R.id.chapter_seven);
        ImageView chapter_eight = view.findViewById(R.id.chapter_eight);

        ImageView chapter_banner = view.findViewById(R.id.chapter_banner);


        imageViewSet[0] = chapter_one;
        imageViewSet[1] = chapter_two;
        imageViewSet[2] = chapter_three;
        imageViewSet[3] = chapter_four;
        imageViewSet[4] = chapter_five;
        imageViewSet[5] = chapter_six;
        imageViewSet[6] = chapter_seven;
        imageViewSet[7] = chapter_eight;


        int[] unlocked_drawables = {};
        int[] locked_drawables = {};
        switch(quiz_table) {
            case SCI_TABLE_NAME:
                unlocked_drawables = science_drawables;
                locked_drawables = locked_science_drawables;
                chapter_banner.setImageResource(chapterBannerSet[0]);
                break;
            case MUS_TABLE_NAME:
                unlocked_drawables = music_drawables;
                locked_drawables = locked_music_drawables;
                chapter_banner.setImageResource(chapterBannerSet[1]);
                break;
            case HIS_TABLE_NAME:
                unlocked_drawables = history_drawables;
                locked_drawables = locked_history_drawables;
                chapter_banner.setImageResource(chapterBannerSet[2]);
                break;
            case MATH_TABLE_NAME:
                unlocked_drawables = math_drawables;
                locked_drawables = locked_math_drawables;
                chapter_banner.setImageResource(chapterBannerSet[3]);
                break;
            case ARTS_TABLE_NAME:
                unlocked_drawables = art_drawables;
                locked_drawables = locked_art_drawables;
                chapter_banner.setImageResource(chapterBannerSet[4]);
                break;
            case GEO_TABLE_NAME:
                unlocked_drawables = geography_drawables;
                locked_drawables = locked_geography_drawables;
                chapter_banner.setImageResource(chapterBannerSet[5]);
                break;

        }


        for(int i = 0; i<imageViewSet.length; i++) {

            if(booleanList.get(i)){
                imageViewSet[i].setImageResource(unlocked_drawables[i]);
            } else {
                imageViewSet[i].setImageResource(locked_drawables[i]);
            }


        }

        chapter_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cp = tbl_name + unlockedChapter1;
                tbl_num = one;
                checkRules(zero, quiz_table);
            }
        });

        chapter_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cp = tbl_name + unlockedChapter2;
                tbl_num = two;
                checkRules(one, quiz_table);

            }
        });
        chapter_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cp = tbl_name + unlockedChapter3;
                tbl_num = three;
                checkRules(two, quiz_table);
            }
        });
        chapter_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cp = tbl_name + unlockedChapter4;
                tbl_num = four;
                checkRules(three,quiz_table);
            }
        });
        chapter_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cp = tbl_name + unlockedChapter5;
                tbl_num = five;
                checkRules(four, quiz_table);
            }
        });
        chapter_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cp = tbl_name + unlockedChapter6;
                tbl_num = six;
                checkRules(five, quiz_table);
            }
        });
        chapter_seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cp = tbl_name + unlockedChapter7;
                tbl_num = seven;
                checkRules(six, quiz_table);
            }
        });
        chapter_eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cp = tbl_name + unlockedChapter8;
                tbl_num = eight;
                checkRules(seven, quiz_table);
            }
        });







        super.onViewCreated(view, savedInstanceState);
    }

    private void checkRules(int position, String table) {

        if(!sp.getBoolean(getString(R.string.quizpert_rules_one), false) && sp.getBoolean(cp,false)) {
            showRulesDialog(position, table);
        } else if (sp.getBoolean(cp, false)) {
            goToQuiz(position, table);
        } else if (!sp.getBoolean(cp,false)) {
            boolean chapBool = checkPreviousChapsUnlocked(tbl_num,tbl_name);
            if(!chapBool) {
                ShowDialog showLockedMenu = new ShowDialog(getContext(), R.layout.locked_chapter_dialog, R.id.close_locked_chapter_popup);
                showLockedMenu.getV2ShowDialog();
            } else {
                sendFragmentData.sendTable(table);
                sendFragmentData.sendTableNum(tbl_num);
                sendFragmentData.sendTablePrefix(tbl_name);
                ShowDialog showPayGemsDialog = new ShowDialog(Objects.requireNonNull(getActivity()), R.layout.buy_chapter, R.id.close_buy_chapter_popup);
                showPayGemsDialog.getShowDialog();
            }
        }
    }

    /*private void checkRules(int position, String tbl_name) {

        if(!sp.getBoolean(getString(R.string.quizpert_rules_one), false) && sp.getBoolean(cp,false)) {
            showRulesDialog(position, table);
        } else if (sp.getBoolean(cp, false)) {
            goToQuiz(position,table);
        } else if (!sp.getBoolean(cp,false)) {
            boolean chapBool = checkPreviousChapsUnlocked(tbl_num,tbl_name);
            if(!chapBool) {
                ShowDialog showLockedMenu = new ShowDialog(getContext(), R.layout.unlock_other_chapters, R.id.close_locked_menu);
                showLockedMenu.getV2ShowDialog();
            } else {
                ShowDialog showPayGemsDialog = new ShowDialog(getContext(), R.layout.go_to_store, R.id.pay_gems);
                showPayGemsDialog.getShowDialog();
            }
        }
    }*/

    private boolean checkPreviousChapsUnlocked(int tbl_num, String tbl_name) {

        boolean returnChapBool = true;
        for(int i = chapter_start;i < tbl_num;i++) {
            boolean chapBool = sp.getBoolean(tbl_name + getString(R.string.chapter_) + i,false);

            if(!chapBool) {
                returnChapBool = false;
                break;
            } else {
                returnChapBool = true;
            }
        }

        return returnChapBool;
    }

    private void showRulesDialog(int position, String table) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.rules_popup_dialog,null);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

       /* ImageView closeRulesPopup = dialogView.findViewById(R.id.close_rules_popup);
        ClosedButtonListener closedButtonListener = new ClosedButtonListener(
                closeRulesPopup,
                R.id.close_rules_popup,
                getContext(),this
        );
        closeRulesPopup.setOnTouchListener(closedButtonListener);*/

        dialogView.findViewById(R.id.close_rules_popup).setOnClickListener(v -> {
            alertDialog.dismiss();
            goToQuiz(position,table);
            editor.putBoolean(getString(R.string.quizpert_rules_one),true).apply();
        });
        /*dialogView.findViewById(R.id.dont_show_again).setOnClickListener(v -> {

            editor.putBoolean(getString(R.string.quizpert_rules_one),true).apply();
            alertDialog.dismiss();
            goToQuiz(position,table);
        });*/

    }

    /*private void showRulesDialog(int position, String table) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.rules_one,null);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        dialogView.findViewById(R.id.good_to_know).setOnClickListener(v -> {alertDialog.dismiss(); goToQuiz(position,table);});
        dialogView.findViewById(R.id.dont_show_again).setOnClickListener(v -> {

            editor.putBoolean(getString(R.string.quizpert_rules_one),true).apply();
            alertDialog.dismiss();
            goToQuiz(position,table);
        });

    }
*/
    private void goToQuiz(int position,String table) {
        getChapterPassed();

        //New In Add New
        String[] hold_tblNames = Objects.requireNonNull(getContext()).getResources().getStringArray(R.array.quiz_table_chp1);
        int pos = 0;
        switch(table) {
            case SCI_TABLE_NAME:
                pos = 0;
                break;
            case MUS_TABLE_NAME:
                pos = 1;
                break;
            case HIS_TABLE_NAME:
                pos = 2;
                break;
            case MATH_TABLE_NAME:
                pos = 3;
                break;
            case ARTS_TABLE_NAME:
                pos = 4;
                break;
            case GEO_TABLE_NAME:
                pos = 5;
                break;

        }


        try {
            if (chapterPassed || cp.equals(hold_tblNames[pos])) {
                Intent intent = new Intent(getActivity(), QuizActivity.class);
                intent.putExtra(getString(R.string.quiz_table_pref_key), table);
                switch (position) {
                    case 0:
                        intent.putExtra(Chapter, chapter1);
                        break;
                    case 1:
                        intent.putExtra(Chapter, chapter2);
                        break;
                    case 2:
                        intent.putExtra(Chapter, chapter3);
                        break;
                    case 3:
                        intent.putExtra(Chapter, chapter4);
                        break;
                    case 4:
                        intent.putExtra(Chapter, chapter5);
                        break;
                    case 5:
                        intent.putExtra(Chapter, chapter6);
                        break;
                    case 6:
                        intent.putExtra(Chapter, chapter7);
                        break;
                    case 7:
                        intent.putExtra(Chapter, chapter8);
                        break;
                }
                Objects.requireNonNull(getActivity()).finish();
                try {
                   // stopService(svc);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                startActivity(intent);

            }
        } catch(NullPointerException e) {
            e.printStackTrace();
        }

    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
   //  * @param param1 Parameter 1.
     //* @param param2 Parameter 2.
     * @return A new instance of fragment PageFragment.
     *//*
    // TODO: Rename and change types and number of parameters
    public static pagefragmenttwo newInstance(String param1, String param2) {
        pagefragmenttwo fragment = new pagefragmenttwo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }*/

  /*  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page, container, false);
    }*/

    private void getChapterPassed() { chapterPassed = sp.getBoolean(cp,false); }

    private void putBoolChapters() {

        if (sp.getInt(getString(R.string.update_chapters_boolean_pref_key),getResources().getInteger(R.integer.update_chap_boolean_pref_file_default_key)) == one) {

            //Science
            editor.putBoolean(getString(R.string.sci_chap1_pref_key), true);
            editor.putBoolean(getString(R.string.sci_chap2_pref_key), false);
            editor.putBoolean(getString(R.string.sci_chap3_pref_key), false);
            editor.putBoolean(getString(R.string.sci_chap4_pref_key), false);
            editor.putBoolean(getString(R.string.sci_chap5_pref_key), false);
            editor.putBoolean(getString(R.string.sci_chap6_pref_key), false);
            editor.putBoolean(getString(R.string.sci_chap7_pref_key), false);
            editor.putBoolean(getString(R.string.sci_chap8_pref_key), false);

            //Music
            editor.putBoolean(getString(R.string.mus_chap1_pref_key), true);
            editor.putBoolean(getString(R.string.mus_chap2_pref_key), false);
            editor.putBoolean(getString(R.string.mus_chap3_pref_key), false);
            editor.putBoolean(getString(R.string.mus_chap4_pref_key), false);
            editor.putBoolean(getString(R.string.mus_chap5_pref_key), false);
            editor.putBoolean(getString(R.string.mus_chap6_pref_key), false);
            editor.putBoolean(getString(R.string.mus_chap7_pref_key), false);
            editor.putBoolean(getString(R.string.mus_chap8_pref_key), false);

            //History
            editor.putBoolean(getString(R.string.his_chap1_pref_key), true);
            editor.putBoolean(getString(R.string.his_chap2_pref_key), false);
            editor.putBoolean(getString(R.string.his_chap3_pref_key), false);
            editor.putBoolean(getString(R.string.his_chap4_pref_key), false);
            editor.putBoolean(getString(R.string.his_chap5_pref_key), false);
            editor.putBoolean(getString(R.string.his_chap6_pref_key), false);
            editor.putBoolean(getString(R.string.his_chap7_pref_key), false);
            editor.putBoolean(getString(R.string.his_chap8_pref_key), false);

            //Arts
            editor.putBoolean(getString(R.string.art_chap1_pref_key), true);
            editor.putBoolean(getString(R.string.art_chap2_pref_key), false);
            editor.putBoolean(getString(R.string.art_chap3_pref_key), false);
            editor.putBoolean(getString(R.string.art_chap4_pref_key), false);
            editor.putBoolean(getString(R.string.art_chap5_pref_key), false);
            editor.putBoolean(getString(R.string.art_chap6_pref_key), false);
            editor.putBoolean(getString(R.string.art_chap7_pref_key), false);
            editor.putBoolean(getString(R.string.art_chap8_pref_key), false);

            //Mathematics
            editor.putBoolean(getString(R.string.math_chap1_pref_key), true);
            editor.putBoolean(getString(R.string.math_chap2_pref_key), false);
            editor.putBoolean(getString(R.string.math_chap3_pref_key), false);
            editor.putBoolean(getString(R.string.math_chap4_pref_key), false);
            editor.putBoolean(getString(R.string.math_chap5_pref_key), false);
            editor.putBoolean(getString(R.string.math_chap6_pref_key), false);
            editor.putBoolean(getString(R.string.math_chap7_pref_key), false);
            editor.putBoolean(getString(R.string.math_chap8_pref_key), false);

            //Geography
            editor.putBoolean(getString(R.string.geo_chap1_pref_key), true);
            editor.putBoolean(getString(R.string.geo_chap2_pref_key), false);
            editor.putBoolean(getString(R.string.geo_chap3_pref_key), false);
            editor.putBoolean(getString(R.string.geo_chap4_pref_key), false);
            editor.putBoolean(getString(R.string.geo_chap5_pref_key), false);
            editor.putBoolean(getString(R.string.geo_chap6_pref_key), false);
            editor.putBoolean(getString(R.string.geo_chap7_pref_key), false);
            editor.putBoolean(getString(R.string.geo_chap8_pref_key), false);
            editor.apply();
            editor.putInt(getString(R.string.update_chapters_boolean_pref_key), two).apply();
        }
    }

    private List<Boolean> boolChapters(String table) {

        List<Boolean> bL = new ArrayList<>();
        switch(table) {
            case SCI_TABLE_NAME:
                List<Boolean> scibooleanList = new ArrayList<>();
                scibooleanList.add(sp.getBoolean(getString(R.string.sci_chap1_pref_key),true));
                scibooleanList.add(sp.getBoolean(getString(R.string.sci_chap2_pref_key),false));
                scibooleanList.add(sp.getBoolean(getString(R.string.sci_chap3_pref_key),false));
                scibooleanList.add(sp.getBoolean(getString(R.string.sci_chap4_pref_key),false));
                scibooleanList.add(sp.getBoolean(getString(R.string.sci_chap5_pref_key),false));
                scibooleanList.add(sp.getBoolean(getString(R.string.sci_chap6_pref_key),false));
                scibooleanList.add(sp.getBoolean(getString(R.string.sci_chap7_pref_key),false));
                scibooleanList.add(sp.getBoolean(getString(R.string.sci_chap8_pref_key),false));
                bL = scibooleanList;
                break;
            case MUS_TABLE_NAME:
                List<Boolean> musbooleanList = new ArrayList<>();
                musbooleanList.add(sp.getBoolean(getString(R.string.mus_chap1_pref_key),true));
                musbooleanList.add(sp.getBoolean(getString(R.string.mus_chap2_pref_key),false));
                musbooleanList.add(sp.getBoolean(getString(R.string.mus_chap3_pref_key),false));
                musbooleanList.add(sp.getBoolean(getString(R.string.mus_chap4_pref_key),false));
                musbooleanList.add(sp.getBoolean(getString(R.string.mus_chap5_pref_key),false));
                musbooleanList.add(sp.getBoolean(getString(R.string.mus_chap6_pref_key),false));
                musbooleanList.add(sp.getBoolean(getString(R.string.mus_chap7_pref_key),false));
                musbooleanList.add(sp.getBoolean(getString(R.string.mus_chap8_pref_key),false));
                bL = musbooleanList;
                break;
            case HIS_TABLE_NAME:
                List<Boolean> hisbooleanList = new ArrayList<>();
                hisbooleanList.add(sp.getBoolean(getString(R.string.his_chap1_pref_key),true));
                hisbooleanList.add(sp.getBoolean(getString(R.string.his_chap2_pref_key),false));
                hisbooleanList.add(sp.getBoolean(getString(R.string.his_chap3_pref_key),false));
                hisbooleanList.add(sp.getBoolean(getString(R.string.his_chap4_pref_key),false));
                hisbooleanList.add(sp.getBoolean(getString(R.string.his_chap5_pref_key),false));
                hisbooleanList.add(sp.getBoolean(getString(R.string.his_chap6_pref_key),false));
                hisbooleanList.add(sp.getBoolean(getString(R.string.his_chap7_pref_key),false));
                hisbooleanList.add(sp.getBoolean(getString(R.string.his_chap8_pref_key),false));
                bL = hisbooleanList;
                break;
            case ARTS_TABLE_NAME:
                List<Boolean> artbooleanList = new ArrayList<>();
                artbooleanList.add(sp.getBoolean(getString(R.string.art_chap1_pref_key),true));
                artbooleanList.add(sp.getBoolean(getString(R.string.art_chap2_pref_key),false));
                artbooleanList.add(sp.getBoolean(getString(R.string.art_chap3_pref_key),false));
                artbooleanList.add(sp.getBoolean(getString(R.string.art_chap4_pref_key),false));
                artbooleanList.add(sp.getBoolean(getString(R.string.art_chap5_pref_key),false));
                artbooleanList.add(sp.getBoolean(getString(R.string.art_chap6_pref_key),false));
                artbooleanList.add(sp.getBoolean(getString(R.string.art_chap7_pref_key),false));
                artbooleanList.add(sp.getBoolean(getString(R.string.art_chap8_pref_key),false));
                bL = artbooleanList;
                break;
            case MATH_TABLE_NAME:
                List<Boolean> mathbooleanList = new ArrayList<>();
                mathbooleanList.add(sp.getBoolean(getString(R.string.math_chap1_pref_key),true));
                mathbooleanList.add(sp.getBoolean(getString(R.string.math_chap2_pref_key),false));
                mathbooleanList.add(sp.getBoolean(getString(R.string.math_chap3_pref_key),false));
                mathbooleanList.add(sp.getBoolean(getString(R.string.math_chap4_pref_key),false));
                mathbooleanList.add(sp.getBoolean(getString(R.string.math_chap5_pref_key),false));
                mathbooleanList.add(sp.getBoolean(getString(R.string.math_chap6_pref_key),false));
                mathbooleanList.add(sp.getBoolean(getString(R.string.math_chap7_pref_key),false));
                mathbooleanList.add(sp.getBoolean(getString(R.string.math_chap8_pref_key),false));
                bL = mathbooleanList;
                break;
            case GEO_TABLE_NAME:
                List<Boolean> geobooleanList = new ArrayList<>();
                geobooleanList.add(sp.getBoolean(getString(R.string.geo_chap1_pref_key),true));
                geobooleanList.add(sp.getBoolean(getString(R.string.geo_chap2_pref_key),false));
                geobooleanList.add(sp.getBoolean(getString(R.string.geo_chap3_pref_key),false));
                geobooleanList.add(sp.getBoolean(getString(R.string.geo_chap4_pref_key),false));
                geobooleanList.add(sp.getBoolean(getString(R.string.geo_chap5_pref_key),false));
                geobooleanList.add(sp.getBoolean(getString(R.string.geo_chap6_pref_key),false));
                geobooleanList.add(sp.getBoolean(getString(R.string.geo_chap7_pref_key),false));
                geobooleanList.add(sp.getBoolean(getString(R.string.geo_chap8_pref_key),false));
                bL = geobooleanList;
                break;

        }

        return bL;
    }

    @Override
    public void setMusicPlayer(int MUSIC_PLAYER_CODE) {

    }

    @Override
    public void setTransactionCode(int TRANSACTION_CODE) {

    }



    @Override
    public void birthDateCompleted(int BIRTH_DATE_CODE) {

    }


}