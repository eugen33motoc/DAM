package com.example.mathquiz;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Question> questionList;
    private int numberCorrect;
    private int numberIncorect;
    private int totalQuestions;
    private int score;
    private  Question  currentQuestion;
    public Game()
    {
        numberCorrect=0;
        numberIncorect=0;
        totalQuestions=0;
        score=0;
        currentQuestion=new Question(10);
        questionList=new ArrayList<Question>();
    }
    public void makeNewQuestion(){
        currentQuestion=new Question(totalQuestions*2+5);
        totalQuestions++;
        questionList.add(currentQuestion);
    }
    public boolean checkAnswer(int submittedAnswer)
    {
        boolean isCorrect;
        if(currentQuestion.getAnswer()==submittedAnswer)
        {
            numberCorrect++;
            isCorrect=true;
        }else{
            numberIncorect++;
            isCorrect=false;
        }
        score=numberCorrect*10-numberIncorect*10;
        return  isCorrect;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public int getNumberCorrect() {
        return numberCorrect;
    }

    public void setNumberCorrect(int numberCorrect) {
        this.numberCorrect = numberCorrect;
    }

    public int getNumberIncorect() {
        return numberIncorect;
    }

    public void setNumberIncorect(int numberIncorect) {
        this.numberIncorect = numberIncorect;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }
}
