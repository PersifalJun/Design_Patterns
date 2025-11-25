import model.*;

public class Main {

    public static void main(String[] args) {
        Poll poll = Poll.builder()
                .withPollName("Programming Survey")

                .yesNoPollQuestion("Are you programmer?")

                .oneVariantPollQuestion("How many years of programming experience do you have?")
                .withAnswerVariant("0-1 years")
                .withAnswerVariant("1-3 years")
                .withAnswerVariant("3-5 years")
                .withAnswerVariant("5+ years")
                .and()

                .oneVariantPollQuestion("What is your favorite programming language?")
                .withAnswerVariant("Java")
                .withAnswerVariant("Python")
                .withAnswerVariant("C++")
                .and()

                .pollQuestion("Do you like your job?")
                .withMinAnswers(0)
                .withMaxAnswers(1)
                .withAnswerVariant("Who knows..")
                .withAnswerVariant("Of course")
                .and()

                .pollQuestion("What are your strong qualities?")
                .withMinAnswers(0)
                .withMaxAnswers(5)
                .withAnswerVariant("Leadership")
                .withAnswerVariant("Teamwork")
                .withAnswerVariant("Problem-solving")
                .withAnswerVariant("Communication")
                .withAnswerVariant("Creativity")
                .and()

                .build();
        printPoll(poll);

    }
    private static void printPoll(Poll poll) {
        System.out.println("Опрос: " + poll.getPollName());
        int i = 1;
        for (PollQuestion question : poll.getPollQuestionList()) {
            System.out.println(i++ + ". Вопрос: " + question.getTitle());
            for (String answer : question.getAnswers()) {
                System.out.println("   - " + answer);
            }
            System.out.println();
        }
    }
}
