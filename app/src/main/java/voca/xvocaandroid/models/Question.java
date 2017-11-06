package voca.xvocaandroid.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {
    private ArrayList<String> answers = new ArrayList<>();
    private String correctAnswer;
    private String question;

    public Question(String question, String correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answers.add(correctAnswer);
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public boolean isCorrectAnswer(String answer) {
        return answer.equals(this.correctAnswer);
    }

    public void addWrongAnswer(String answer) {
        this.answers.add(answer);
    }
}
