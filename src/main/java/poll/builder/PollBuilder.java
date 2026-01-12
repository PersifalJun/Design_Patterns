package poll.builder;

import poll.Poll;
import poll.PollQuestion;

import java.util.ArrayList;
import java.util.List;

public class PollBuilder {

    public String pollName;
    public List<PollQuestion> pollQuestionList;

    public PollBuilder() {
        this.pollQuestionList = new ArrayList<>();
    }

    public PollBuilder withPollName(String pollName) {
        this.pollName = pollName;
        return this;
    }

    public PollQuestionBuilder pollQuestion(String title) {
        return new PollQuestionBuilder(this).withTitle(title);
    }

    public PollBuilder addQuestion(PollQuestion question) {
        pollQuestionList.add(question);
        return this;
    }

    public PollQuestionBuilder oneVariantPollQuestion(String title) {
        return new PollQuestionBuilder(this)
                .withTitle(title)
                .withMinAnswers(1)
                .withMaxAnswers(1);
    }

    public PollBuilder yesNoPollQuestion(String title) {
        new PollQuestionBuilder(this)
                .withTitle(title)
                .withMinAnswers(1)
                .withMaxAnswers(1)
                .withAnswerVariant("Yes")
                .withAnswerVariant("No")
                .and();
        return this;
    }

    public PollQuestionBuilder notMandatoryQuestion(String title) {
        return new PollQuestionBuilder(this)
                .withTitle(title)
                .withMinAnswers(0)
                .withMaxAnswers(1);
    }

    public Poll build() {
        return new Poll(pollName,pollQuestionList);
    }
}