package poll.builder;

import poll.question.PollQuestion;

import java.util.ArrayList;
import java.util.List;

public class PollQuestionBuilder {
    private final PollBuilder poolBuilder;
    private String title;
    private int minAnswers;
    private int maxAnswers;
    private final List<String> answers;


    public PollQuestionBuilder(PollBuilder poolBuilder) {
        this.poolBuilder = poolBuilder;
        this.title = "EmptyTitle";
        this.minAnswers = 0;
        this.maxAnswers = 0;
        this.answers = new ArrayList<>();
    }

    public PollQuestionBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public PollQuestionBuilder withMinAnswers(int minAnswers) {
        this.minAnswers = minAnswers;
        return this;
    }

    public PollQuestionBuilder withMaxAnswers(int maxAnswers) {
        this.maxAnswers = maxAnswers;
        return this;
    }

    public PollQuestionBuilder withAnswerVariant(String answerVariant) {
        this.answers.add(answerVariant);
        return this;
    }

    public PollBuilder and() {
        PollQuestion pollQuestion = this.buildQuestion();
        return poolBuilder.addQuestion(pollQuestion);
    }

    public PollQuestion buildQuestion(){
        return new PollQuestion(title, minAnswers, maxAnswers, answers);
    }

}
