package model;

import java.util.ArrayList;
import java.util.List;

public class PollQuestion {
    private String title;
    private int minAnswers;
    private int maxAnswers;
    private List<String> answers;

    public PollQuestion(PollQuestionBuilder questionBuilder) {
        this.title = questionBuilder.title;
        this.minAnswers = questionBuilder.minAnswers;
        this.maxAnswers = questionBuilder.maxAnswers;
        this.answers = questionBuilder.answers;

        if (minAnswers < 0
                || maxAnswers <= 0
                || minAnswers > maxAnswers
                || minAnswers >= answers.size()
        ) {
            throw new IllegalArgumentException("Illegal max or min answers count");
        }
    }

    public String getTitle() {
        return title;
    }

    public int getMinAnswers() {
        return minAnswers;
    }

    public int getMaxAnswers() {
        return maxAnswers;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public static class PollQuestionBuilder{
        private final Poll.PollBuilder poolBuilder;
        private String title;
        private int minAnswers;
        private int maxAnswers;
        private List<String> answers = new ArrayList<>();


        public PollQuestionBuilder(Poll.PollBuilder poolBuilder) {
            this.poolBuilder = poolBuilder;
        }

        public PollQuestionBuilder withTitle(String title){
            this.title = title;
            return this;
        }
        public PollQuestionBuilder withMinAnswers(int minAnswers){
            this.minAnswers = minAnswers;
            return this;
        }
        public PollQuestionBuilder withMaxAnswers(int maxAnswers){
            this.maxAnswers = maxAnswers;
            return this;
        }
        public PollQuestionBuilder withAnswerVariant(String answerVariant){
            this.answers.add(answerVariant);
            return this;
        }

        public Poll.PollBuilder and(){
            poolBuilder.addQuestion(new PollQuestion(this));
            return poolBuilder;
        }
    }

}
