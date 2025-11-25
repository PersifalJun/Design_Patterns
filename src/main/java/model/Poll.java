package model;

import java.util.ArrayList;
import java.util.List;

public class Poll {
    private String pollName;
    private List<PollQuestion> pollQuestionList;

    public Poll(PollBuilder pollBuilder) {
        this.pollName = pollBuilder.pollName;
        this.pollQuestionList = pollBuilder.pollQuestionList;
    }

    public String getPollName() {
        return pollName;
    }

    public List<PollQuestion> getPollQuestionList() {
        return pollQuestionList;
    }

    public static PollBuilder builder() {
        return new PollBuilder();
    }

    public static class PollBuilder {

        private String pollName;
        private List<PollQuestion> pollQuestionList = new ArrayList<>();

        public PollBuilder withPollName(String pollName){
            this.pollName = pollName;
            return this;
        }
        public PollQuestion.PollQuestionBuilder pollQuestion(String title){
            return new PollQuestion.PollQuestionBuilder(this).withTitle(title);
        }

        void addQuestion(PollQuestion question) {
            pollQuestionList.add(question);
        }

        public PollQuestion.PollQuestionBuilder oneVariantPollQuestion(String title){
            return new PollQuestion.PollQuestionBuilder(this)
                    .withTitle(title)
                    .withMinAnswers(1)
                    .withMaxAnswers(1);
        }

        public PollBuilder yesNoPollQuestion(String title){
            new PollQuestion.PollQuestionBuilder(this)
                    .withTitle(title)
                    .withMinAnswers(1)
                    .withMaxAnswers(1)
                    .withAnswerVariant("Yes")
                    .withAnswerVariant("No")
                    .and();
            return this;
        }
        public Poll build(){
            return new Poll(this);
        }
    }
    @Override
    public String toString() {
        return "Poll{" +
                "pollName='" + pollName + '\'' +
                ", pollQuestionList=" + pollQuestionList +
                '}';
    }
}
