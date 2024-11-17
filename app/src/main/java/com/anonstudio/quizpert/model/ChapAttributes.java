package com.anonstudio.quizpert.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import static com.anonstudio.quizpert.model.constants.Constants.BoolTable.*;
import static com.anonstudio.quizpert.model.constants.Constants.IntTable.one;
import static com.anonstudio.quizpert.model.constants.Constants.StringTable.*;


public class ChapAttributes  {
    private String Chapter;
    private String Table;
    private String tbl_prefix = "";
    private String chapUnlocked = "";
    private Preferences preferences;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private Context context;
    //private String chapUnlockBefore;

    public ChapAttributes(Context context, String Chapter, String Table) {

        this.context = context;
        this.Chapter = Chapter;
        this.Table = Table;
        this.tbl_prefix = getTblPrefix();
        preferences = new Preferences(context, APP_PREF_FILE);
        this.sp = preferences.getPreferenceObject();
        this.editor = preferences.getPreferenceEditorObject();

    }

    public void goToAttemptsAttribute() { putAttempts(); }

    public void goToChapterUnlockAttribute() { saveChapterUnlockedPreferences(unlockChapter()); }

    public void goToHighScoreAttribute(int score) { getHighScore(score); }

    public void goToTrophyAttribute() { putTrophy(); }

    public boolean goToCheckChapterUnlocked() { return checkChapterUnlocked(unlockChapter()); }

    public String goToChapterUnlockedBefore() { return chapterUnlockedBefore(); }

    public String goToHardQuizAttribute() { return hardChapter(); }

    public void goToHardChapterUnlockAttribute() { saveHardChapterUnlockedPreferences(getHardChapterUnlockedPreferences(unlockChapter()), getHardChapterUnlockedBeforePreferences(chapterUnlockedBefore()) ,unlockChapter());}

   /// public void goToHardChapterUnlockedBeforeAttribute() { getHardChapterUnlockedBeforePreferences(chapterUnlockedBefore()); }







    private String getTblPrefix() {

        switch (this.Table) {
            case QuizConsts.QuestionsTable.SCI_TABLE_NAME:
                tbl_prefix = sciencePrefix;
                break;
            case QuizConsts.QuestionsTable.MUS_TABLE_NAME:
                tbl_prefix = musicPrefix;
                break;
            case QuizConsts.QuestionsTable.HIS_TABLE_NAME:
                tbl_prefix = historyPrefix;
                break;
            case QuizConsts.QuestionsTable.MATH_TABLE_NAME:
                tbl_prefix = mathematicsPrefix;
                break;
            case QuizConsts.QuestionsTable.ARTS_TABLE_NAME:
                tbl_prefix = artPrefix;
                break;
            case QuizConsts.QuestionsTable.GEO_TABLE_NAME:
                tbl_prefix = geographyPrefix;
                break;
        }

        return tbl_prefix;
    }


    private String unlockChapter() {

        switch (Chapter.trim()) {

            case chapter1:
                chapUnlocked = tbl_prefix + unlockedChapter2;
                break;
            case chapter2:
                chapUnlocked = tbl_prefix + unlockedChapter3;
                break;
            case chapter3:
                chapUnlocked = tbl_prefix + unlockedChapter4;
                break;
            case chapter4:
                chapUnlocked = tbl_prefix + unlockedChapter5;
                break;
            case chapter5:
                chapUnlocked = tbl_prefix + unlockedChapter6;
                break;
            case chapter6:
                chapUnlocked = tbl_prefix + unlockedChapter7;
                break;
            case chapter7:
                chapUnlocked = tbl_prefix + unlockedChapter8;
                break;
            default:
                break;




        }
        Log.d("stuff", chapUnlocked);
        return chapUnlocked;
    }

    private boolean checkChapterUnlocked(String chapUnlocked) { return sp.getBoolean(chapUnlocked,off); }

    private boolean getHardChapterUnlockedPreferences(String chapUnlock) {
        return sp.getBoolean(chapUnlock,true);
    }

    private boolean getHardChapterUnlockedBeforePreferences(String chapUnlockedBefore) {
        return sp.getBoolean(chapUnlockedBefore,false);
    }

    private void saveHardChapterUnlockedPreferences(boolean chapUnlock, boolean chapUnlockedBefore, String chapUnlocked) {
        if(chapUnlock && chapUnlockedBefore) {
            editor.putBoolean(chapUnlocked, false).apply();
        }
    }

    private void saveChapterUnlockedPreferences(String chapUnlocked) {

        boolean chapter_bool = sp.getBoolean(chapUnlocked, false);
        if (!chapter_bool) {
            editor.putBoolean(chapUnlocked, true).apply();
            Log.d("stuff", String.valueOf(sp.getBoolean(chapUnlocked,false)));
        }

    }

    private void getHighScore(int score) {

        String highScore = "";
        switch (Chapter) {
            case chapter1:
                highScore = sp.getString(tbl_prefix + highScoreChap1, dash);
                break;
            case chapter2:
                highScore = sp.getString(tbl_prefix + highScoreChap2, dash);
                break;
            case chapter3:
                highScore = sp.getString(tbl_prefix + highScoreChap3, dash);
                break;
            case chapter4:
                highScore = sp.getString(tbl_prefix + highScoreChap4, dash);
                break;
            case chapter5:
                highScore = sp.getString(tbl_prefix + highScoreChap5, dash);
                break;
            case chapter6:
                highScore = sp.getString(tbl_prefix + highScoreChap6, dash);
                break;
            case chapter7:
                highScore = sp.getString(tbl_prefix + highScoreChap7, dash);
                break;
            case chapter8:
                highScore = sp.getString(tbl_prefix + highScoreChap8, dash);
                break;
        }
        putHighScore(highScore, score);
    }

    private void putHighScore(String highScore, int quizScore) {

        if (highScore.equals(dash)) { highScore = zero_string; }

        if (Integer.parseInt(highScore) <= quizScore) {
            switch (Chapter) {
                case chapter1:
                    editor.putString(tbl_prefix + highScoreChap1, String.valueOf(quizScore)).apply();
                    break;
                case chapter2:
                    editor.putString(tbl_prefix + highScoreChap2, String.valueOf(quizScore)).apply();
                    break;
                case chapter3:
                    editor.putString(tbl_prefix + highScoreChap3, String.valueOf(quizScore)).apply();
                    break;
                case chapter4:
                    editor.putString(tbl_prefix + highScoreChap4, String.valueOf(quizScore)).apply();
                    break;
                case chapter5:
                    editor.putString(tbl_prefix + highScoreChap5, String.valueOf(quizScore)).apply();
                    break;
                case chapter6:
                    editor.putString(tbl_prefix + highScoreChap6, String.valueOf(quizScore)).apply();
                    break;
                case chapter7:
                    editor.putString(tbl_prefix + highScoreChap7, String.valueOf(quizScore)).apply();
                    break;
                case chapter8:
                    editor.putString(tbl_prefix + highScoreChap8, String.valueOf(quizScore)).apply();
                    break;
            }

        }

    }


    private void putAttempts() {

        String attempts = "";
        switch(Chapter) {
            case chapter1:
                attempts = sp.getString(tbl_prefix + attemptsMadeChap1,dash);
                attempts = addAttempts(attempts);
                editor.putString(tbl_prefix + attemptsMadeChap1,attempts).apply();
                break;
            case chapter2:
                attempts = sp.getString(tbl_prefix + attemptsMadeChap2,dash);
                attempts = addAttempts(attempts);
                editor.putString(tbl_prefix + attemptsMadeChap2,attempts).apply();
                break;
            case chapter3:
                attempts = sp.getString(tbl_prefix + attemptsMadeChap3,dash);
                attempts = addAttempts(attempts);
                editor.putString(tbl_prefix + attemptsMadeChap3,attempts).apply();
                break;
            case chapter4:
                attempts = sp.getString(tbl_prefix + attemptsMadeChap4,dash);
                attempts = addAttempts(attempts);
                editor.putString(tbl_prefix + attemptsMadeChap4,attempts).apply();
                break;
            case chapter5:
                attempts = sp.getString(tbl_prefix + attemptsMadeChap5,dash);
                attempts = addAttempts(attempts);
                editor.putString(tbl_prefix + attemptsMadeChap5,attempts).apply();
                break;
            case chapter6:
                attempts = sp.getString(tbl_prefix + attemptsMadeChap6,dash);
                attempts = addAttempts(attempts);
                editor.putString(tbl_prefix + attemptsMadeChap6,attempts).apply();
                break;
            case chapter7:
                attempts = sp.getString(tbl_prefix + attemptsMadeChap7,dash);
                attempts = addAttempts(attempts);
                editor.putString(tbl_prefix + attemptsMadeChap7,attempts).apply();
                break;
            case chapter8:
                attempts = sp.getString(tbl_prefix + attemptsMadeChap8,dash);
                attempts = addAttempts(attempts);
                editor.putString(tbl_prefix + attemptsMadeChap8,attempts).apply();
                break;
        }


    }

    private String addAttempts(String attempts) {

        if(attempts.equals(dash)) { attempts = zero_string; }

        attempts = String.valueOf(Integer.parseInt(attempts) + 1);
        return attempts;

    }

    private void putTrophy() {

        String trophy = "";
        switch(Chapter) {
            case chapter1:
                trophy = sp.getString(tbl_prefix + trophyChap1,dash);
                trophy = addTrophy();
                editor.putString(tbl_prefix + trophyChap1,trophy).apply();
                break;
            case chapter2:
                trophy = sp.getString(tbl_prefix + trophyChap2,dash);
                trophy = addTrophy();
                editor.putString(tbl_prefix + trophyChap2,trophy).apply();
                break;
            case chapter3:
                trophy = sp.getString(tbl_prefix + trophyChap3,dash);
                trophy = addTrophy();
                editor.putString(tbl_prefix + trophyChap3,trophy).apply();
                break;
            case chapter4:
                trophy = sp.getString(tbl_prefix + trophyChap4,dash);
                trophy = addTrophy();
                editor.putString(tbl_prefix + trophyChap4,trophy).apply();
                break;
            case chapter5:
                trophy = sp.getString(tbl_prefix + trophyChap5,dash);
                trophy = addTrophy();
                editor.putString(tbl_prefix + trophyChap5,trophy).apply();
                break;
            case chapter6:
                trophy = sp.getString(tbl_prefix + trophyChap6,dash);
                trophy = addTrophy();
                editor.putString(tbl_prefix + trophyChap6,trophy).apply();
                break;
            case chapter7:
                trophy = sp.getString(tbl_prefix + trophyChap7,dash);
                trophy = addTrophy();
                editor.putString(tbl_prefix + trophyChap7,trophy).apply();
                break;
            case chapter8:
                trophy = sp.getString(tbl_prefix + trophyChap8,dash);
                trophy = addTrophy();
                editor.putString(tbl_prefix + trophyChap8,trophy).apply();
                break;
        }

    }

    private String addTrophy() { return yes; }

    private String chapterUnlockedBefore() {

        String chapUnlockBefore = "";
        switch (Chapter) {
            case chapter1:
                chapUnlockBefore = tbl_prefix + unlockedChapterBefore2;
                break;
            case chapter2:
                chapUnlockBefore = tbl_prefix + unlockedChapterBefore3;
                break;
            case chapter3:
                chapUnlockBefore = tbl_prefix + unlockedChapterBefore4;
                break;
            case chapter4:
                chapUnlockBefore = tbl_prefix + unlockedChapterBefore5;
                break;
            case chapter5:
                chapUnlockBefore = tbl_prefix + unlockedChapterBefore6;
                break;
            case chapter6:
                chapUnlockBefore = tbl_prefix + unlockedChapterBefore7;
                break;
            case chapter7:
                chapUnlockBefore = tbl_prefix + unlockedChapterBefore8;
                break;

        }

        return chapUnlockBefore;
    }

    private String hardChapter() {

        String hardQuizChapter = "";
        switch(Chapter){
            case chapter1:
                hardQuizChapter = hardChap1;
                break;
            case chapter2:
                hardQuizChapter = hardChap2;
                break;
            case chapter3:
                hardQuizChapter = hardChap3;
                break;
            case chapter4:
                hardQuizChapter = hardChap4;
                break;
            case chapter5:
                hardQuizChapter = hardChap5;
                break;
            case chapter6:
                hardQuizChapter = hardChap6;
                break;
            case chapter7:
                hardQuizChapter = hardChap7;
                break;
            case chapter8:
                hardQuizChapter = hardChap8;
                break;
        }

        return hardQuizChapter;
    }

    private String getChapUnlockBefore() {

        String chapUnlockBefore = "";
        switch(Chapter) {
            case chapter1:
                chapUnlockBefore = tbl_prefix + unlockedChapterBefore2;
                break;
            case chapter2:
                chapUnlockBefore = tbl_prefix + unlockedChapterBefore3;
                break;
            case chapter3:
                chapUnlockBefore = tbl_prefix + unlockedChapterBefore4;
                break;
            case chapter4:
                chapUnlockBefore = tbl_prefix + unlockedChapterBefore5;
                break;
            case chapter5:
                chapUnlockBefore = tbl_prefix + unlockedChapterBefore6;
                break;
            case chapter6:
                chapUnlockBefore = tbl_prefix + unlockedChapterBefore7;
                break;
            case chapter7:
                chapUnlockBefore = tbl_prefix + unlockedChapterBefore8;
                break;
        }

        return chapUnlockBefore;
    }



    }




