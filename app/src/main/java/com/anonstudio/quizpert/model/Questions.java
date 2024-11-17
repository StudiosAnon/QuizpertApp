package com.anonstudio.quizpert.model;

import android.graphics.drawable.Drawable;

public class Questions {
    //instance variables
    //private Drawable imageQuestion;
    private String question;
    private String rightAnswer;
    private String category;
    private String answer1;
    private String answer2;
    private String answer3;




    //Questions Constructor

    public Questions() {}

    public Questions(String question, String answer1,String answer2, String answer3, String rightAnswer, String category) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.rightAnswer = rightAnswer;
        this.category = category;

    }

    //New
/*
    public Questions(Drawable imageQuestion, String answer1, String answer2, String answer3, String rightAnswer, String category) {
        this.imageQuestion = imageQuestion;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.rightAnswer = rightAnswer;
        this.category = category;

    }
*/






    //Getters and Setters


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

  /*  public Drawable getImageQuestion() {
        return imageQuestion;
    }

    public void setImageQuestion(Drawable imageQuestion) {
        this.imageQuestion = imageQuestion;
    }
*/
    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }



    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }



    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



}
