import poll.*;
import poll.facade.PollLifecycleFacade;
import poll.question.PollQuestion;

public class Main {

    public static void main(String[] args) {

        PollLifecycleFacade pollLifecycleFacade = new PollLifecycleFacade();

        Poll poll = pollLifecycleFacade.createPoll();

        printPoll(poll);

        var userFillingDataList = pollLifecycleFacade.getUserResponses(poll);

        pollLifecycleFacade.makeAnalyzePoll(userFillingDataList);

    }

    private static void printPoll (Poll poll){
        System.out.println("Опрос: " + poll.pollName());
        int i = 1;
        for (PollQuestion question : poll.pollQuestionList()) {
            System.out.println(i++ + ". Вопрос: " + question.title());
            for (String answer : question.answers()) {
                System.out.println("   - " + answer);
            }
            System.out.println();
        }

    }
}

