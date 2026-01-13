package poll.facade;

import poll.Poll;
import poll.poll_data.PollFillingData;
import poll.proxy.PollAnalyzerProxy;
import poll.response.PollQuestionResponse;
import poll.strategy.FullCountStrategy;
import poll.strategy.LeastFrequentAnswerStrategy;
import poll.strategy.MostFrequentAnswerStrategy;
import poll.strategy.analyze.AnalyzeStrategy;
import poll.strategy.analyze.PollAnalyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PollLifecycleFacade {

    public Poll createPoll() {

        return Poll.builder()
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

                .notMandatoryQuestion("Do you like your job?")
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
    }

    public List<PollFillingData> getUserResponses(Poll poll) {

        return IntStream.range(0, 20)
                .mapToObj(i -> generateRandomFillingData(poll))
                .collect(Collectors.toList());
    }

    public void makeAnalyzePoll(List<PollFillingData> pollFillingDataList) {
        AnalyzeStrategy strategy = new FullCountStrategy();
        PollAnalyzer analyzer = new PollAnalyzerProxy(new PollAnalyzer(strategy));
        analyzer.analyzePoll(pollFillingDataList);

        analyzer.changeAnalyzeStrategy(new LeastFrequentAnswerStrategy());
        analyzer.analyzePoll(pollFillingDataList);

        analyzer.changeAnalyzeStrategy(new MostFrequentAnswerStrategy());
        analyzer.analyzePoll(pollFillingDataList);
    }

    private PollFillingData generateRandomFillingData(Poll poll) {

        Random random = new Random();
        List<PollQuestionResponse> responses = new ArrayList<>();

        for (var question : poll.pollQuestionList()) {

            int minAnswers = question.minAnswers();
            int maxAnswers = question.maxAnswers();
            List<String> answerVariants = question.answers();

            int numberOfAnswers = minAnswers + random.nextInt(maxAnswers - minAnswers + 1);

            List<String> selectedVariants = new ArrayList<>();

            for (int i = 0; i < numberOfAnswers; i++) {
                String randomVariant = answerVariants.get(random.nextInt(answerVariants.size()));
                if (!selectedVariants.contains(randomVariant)) {
                    selectedVariants.add(randomVariant);
                }
            }
            responses.add(new PollQuestionResponse(question, selectedVariants));
        }
        return new PollFillingData(STR."User №\{random.nextInt()}", responses);
    }
}
