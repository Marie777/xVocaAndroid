package voca.xvocaandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterViewFlipper;
import android.widget.Toast;

import voca.xvocaandroid.models.Question;
import voca.xvocaandroid.models.Quiz;

public class QuizActivity extends AppCompatActivity implements QuizAdapter.QuizActionHandler {

    Quiz mQuiz;
    AdapterViewFlipper flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        flipper = findViewById(R.id.quiz_activity_view_flipper);

        flipper.setInAnimation(this, R.animator.question_left_in);
        flipper.setOutAnimation(this, R.animator.question_right_out);

        mQuiz = getMockQuiz();
        QuizAdapter adapter = new QuizAdapter(this, mQuiz, this);
        flipper.setAdapter(adapter);
    }

    private Quiz getMockQuiz() {
        Quiz quiz = new Quiz();

        Question q1 = new Question("Name", "xVoca");
        q1.addWrongAnswer("WhatsApp1");
        q1.addWrongAnswer("Chrome1");
        q1.addWrongAnswer("Gmail1");

        Question q2 = new Question("Name", "xVoca");
        q2.addWrongAnswer("WhatsApp2");
        q2.addWrongAnswer("Chrome2");
        q2.addWrongAnswer("Gmail2");

        Question q3 = new Question("Name", "xVoca");
        q3.addWrongAnswer("WhatsApp3");
        q3.addWrongAnswer("Chrome3");
        q3.addWrongAnswer("Gmail3");

        Question q4 = new Question("Name", "xVoca");
        q4.addWrongAnswer("WhatsApp4");
        q4.addWrongAnswer("Chrome4");
        q4.addWrongAnswer("Gmail4");

        quiz.addQuestion(q1);
        quiz.addQuestion(q2);
        quiz.addQuestion(q3);
        quiz.addQuestion(q4);

        return quiz;
    }

    @Override
    public void onAnswerSelected(String answer) {
        this.mQuiz.addAnswer(answer);

        if (this.mQuiz.isFinished()) {
            Toast.makeText(this, String.format("Score is %f", mQuiz.getScore()), Toast.LENGTH_LONG).show();
        } else {
            flipper.showNext();
        }
    }
}
