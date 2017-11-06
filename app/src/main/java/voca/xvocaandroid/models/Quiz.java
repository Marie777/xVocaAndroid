package voca.xvocaandroid.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Quiz implements Serializable {
    private ArrayList<Question> questions = new ArrayList<>();
    private ArrayList<String> answers = new ArrayList<>();

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public void addAnswer(String answer) {
        this.answers.add(answer);
    }

    public float getScore() {
        float rightAnswers = 0;
        for (int i = 0; i < questions.size(); i++) {
            if(i >= answers.size()) {
                break;
            }
            Question question = questions.get(i);
            String answer = answers.get(i);

            if (question.isCorrectAnswer(answer)) {
                rightAnswers++;
            }
        }

        if(rightAnswers == 0) {
            return 0;
        }

        return questions.size() / rightAnswers;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public boolean isFinished() {
        return questions.size() <= answers.size();
    }
}
