package voca.xvocaandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import voca.xvocaandroid.models.Question;
import voca.xvocaandroid.models.Quiz;

public class QuizAdapter extends BaseAdapter {
    private Quiz quiz;
    private Context ctx;
    private QuizActionHandler quizActionHandler;

    QuizAdapter(Context ctx, Quiz quiz, QuizActionHandler quizActionHandler) {
        this.ctx = ctx;
        this.quiz = quiz;
        this.quizActionHandler = quizActionHandler;
    }

    @Override
    public int getCount() {
        return quiz.getQuestions().size();
    }

    @Override
    public Object getItem(int position) {
        return quiz.getQuestions().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.question_view, null);
        }

        RadioGroup answersLayout = convertView.findViewById(R.id.questions_view_answers_layout);
        Button nextButton = convertView.findViewById(R.id.questions_view_next_btn);
        nextButton.setEnabled(false);

        answersLayout.removeAllViews();

        Question question = quiz.getQuestions().get(position);

        question.getAnswers().forEach(answer -> {
            RadioButton radioButton = new RadioButton(ctx);
            radioButton.setText(answer);

            answersLayout.addView(radioButton);
        });

        answersLayout.setOnCheckedChangeListener((g, i) -> nextButton.setEnabled(true));

        nextButton.setOnClickListener(v -> {
            int selectedId = answersLayout.getCheckedRadioButtonId();
            RadioButton selectedRB = answersLayout.findViewById(selectedId);
            quizActionHandler.onAnswerSelected(selectedRB.getText().toString());
        });

        return convertView;
    }

    interface QuizActionHandler {
        void onAnswerSelected(String answer);
    }
}
