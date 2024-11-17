package com.anonstudio.quizpert;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.anonstudio.quizpert.databinding.CategorySelectionBinding;
import com.anonstudio.quizpert.model.ChapAttributes;
import com.anonstudio.quizpert.model.Gems;
import com.anonstudio.quizpert.model.Preferences;
import com.anonstudio.quizpert.model.QuizConsts;
import com.anonstudio.quizpert.model.ShowDialog;

import static com.anonstudio.quizpert.model.constants.Constants.IntTable.CHAP_UNLOCK_REQUEST_CODE;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.CHECK_AVAILABLE_GEMS_CODE;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.GTS_CODE;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.RESTART_ACTIVITY;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.S_CODE;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.one;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.APP_PREF_FILE;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.Chapter;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.QUIZ_TBL_PREFIX;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.Quiz_Answered;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.Quiz_Score;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.artPrefix;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.chap;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.geographyPrefix;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.historyPrefix;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.mathematicsPrefix;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.musicPrefix;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.quiz;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.sciencePrefix;


public class CategorySelection extends AppCompatActivity implements ShowDialog.ShowDialogListener, pagefragmenttwo.SendFragmentData {

    private CategorySelectionBinding binding;
    private final Preferences preferences = new Preferences(CategorySelection.this,APP_PREF_FILE);
    String table;
    int table_num;
    String table_prefix;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = CategorySelectionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);




        SharedPreferences.Editor editor = preferences.getPreferenceEditorObject();

        editor.putBoolean("trophy_dialog_shown",false).apply();
        editor.putBoolean("finish_interstitial",false).apply();
        editor.putBoolean("finish_clicked",false).apply();
        editor.putBoolean("rewardedAd",false).apply();
        editor.putBoolean("AD_SHOWN",false).apply();
        editor.putBoolean("power_up_dialog_shown", false).apply();
        editor.putBoolean("quizAdShown",false).apply();





        binding.sciBkIcon.setOnClickListener(v -> openChapterSelectionFragment(QuizConsts.QuestionsTable.SCI_TABLE_NAME, sciencePrefix));

        binding.musBkIcon.setOnClickListener(v -> openChapterSelectionFragment(QuizConsts.QuestionsTable.MUS_TABLE_NAME, musicPrefix));

        binding.artBkIcon.setOnClickListener(v -> openChapterSelectionFragment(QuizConsts.QuestionsTable.ARTS_TABLE_NAME, artPrefix));

        binding.mathBkIcon.setOnClickListener(v -> openChapterSelectionFragment(QuizConsts.QuestionsTable.MATH_TABLE_NAME, mathematicsPrefix));

        binding.hisBkIcon.setOnClickListener(v -> openChapterSelectionFragment(QuizConsts.QuestionsTable.HIS_TABLE_NAME,historyPrefix));

        binding.geoBkIcon.setOnClickListener(v -> openChapterSelectionFragment(QuizConsts.QuestionsTable.GEO_TABLE_NAME, geographyPrefix));

        binding.home.setOnClickListener(v -> goToHome());



    }



    private void openChapterSelectionFragment(String table, String tbl_prefix) {

        Bundle bundle = new Bundle();
        bundle.putString(getString(R.string.quiz_table_pref_key), table);
        bundle.putString(QUIZ_TBL_PREFIX,tbl_prefix);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .addToBackStack("chapterFrag")
                .replace(R.id.frag_container, pagefragmenttwo.class, bundle)
                .commit();

    }

    private void goToHome(){
        finish();
        Intent goToMainActivity = new Intent(CategorySelection.this,MainActivity.class);
        overridePendingTransition(0,0);
        startActivity(goToMainActivity);
        overridePendingTransition(0,0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToHome();

    }


    @Override
    public void setMusicPlayer(int MUSIC_PLAYER_CODE) {

    }

    @Override
    public void setTransactionCode(int TRANSACTION_CODE) {

        switch(TRANSACTION_CODE) {
            case GTS_CODE:

                ShowDialog showStoreDialog = new ShowDialog(CategorySelection.this, R.layout.store, R.id.close_store_popup,CategorySelection.this);
                showStoreDialog.getShowDialog();
                break;
            case CHECK_AVAILABLE_GEMS_CODE:
                Gems checkGems = new Gems(CHAP_UNLOCK_REQUEST_CODE, CategorySelection.this);
                int CODE = checkGems.completeGemsTransaction();
                if (CODE == S_CODE) {





                    ChapAttributes chapAttributes = new ChapAttributes(CategorySelection.this,chap + (table_num - one), table);
                    Log.d("stuff", chap + (table_num - one) + " " + table);
                    chapAttributes.goToChapterUnlockAttribute();

                    ShowDialog showSuccessChapterUnlocked = new ShowDialog(CategorySelection.this, R.layout.success_chapter_bought, R.id.close_success_popup);
                    showSuccessChapterUnlocked.getShowDialog();
                } else {
                    ShowDialog showNotEnoughDialog = new ShowDialog(CategorySelection.this, R.layout.not_enough_gems_popup, R.id.close_not_enough_gems_popup);
                    showNotEnoughDialog.getShowDialog();
                }
            case RESTART_ACTIVITY:
                openChapterSelectionFragment(table,table_prefix);
                break;
        }
    }

    @Override
    public void birthDateCompleted(int BIRTH_DATE_CODE) {

    }


    @Override
    public void sendTable(String quiz_table) {
        table = quiz_table;
    }

    @Override
    public void sendTableNum(int tbl_num) {
        table_num = tbl_num;
    }

    @Override
    public void sendTablePrefix(String tbl_prefix) {
        table_prefix = tbl_prefix;
    }
}
