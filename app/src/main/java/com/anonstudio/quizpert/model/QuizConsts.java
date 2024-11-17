package com.anonstudio.quizpert.model;

import android.provider.BaseColumns;

public final class QuizConsts {


    public class QuestionsTable implements BaseColumns {
        public static final String SCI_TABLE_NAME = "sci_quiz";
        public static final String MUS_TABLE_NAME = "mus_quiz";
        public static final String HIS_TABLE_NAME = "his_quiz";
        public static final String MATH_TABLE_NAME = "math_quiz";
        public static final String ARTS_TABLE_NAME = "arts_quiz";
        public static final String GEO_TABLE_NAME = "geo_quiz";

        public static final String COLUMN_QUESTIONS = "questions";
        public static final String COLUMN_ANSWER1 = "answer1";
        public static final String COLUMN_ANSWER2 = "answer2";
        public static final String COLUMN_ANSWER3 = "answer3";
        public static final String COLUMN_ANSWER_ID = "answers";
        public static final String CATEGORY = "category";




    }

}


