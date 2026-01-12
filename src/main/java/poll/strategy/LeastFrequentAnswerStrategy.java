package poll.strategy;

import poll.strategy.analyze.AnalyzeStrategy;
import poll.strategy.statistics.QuestionStatistics;

import java.util.List;

public class LeastFrequentAnswerStrategy implements AnalyzeStrategy {

    @Override
    public void makeAnalyze(List<QuestionStatistics> questionStatisticsList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("-----Least Frequent Analyze-----\n");

        for (var statistics : questionStatisticsList) {
            var mostFrequentQuestionVotes = -1;
            var mostFrequentQuestionAnswer = "";

            for (var questionVotes : statistics.selectedVariantsCount().entrySet()) {

                if (mostFrequentQuestionVotes == -1 || mostFrequentQuestionVotes > questionVotes.getValue()) {
                    mostFrequentQuestionVotes = questionVotes.getValue();
                    mostFrequentQuestionAnswer = questionVotes.getKey();

                }
            }
            stringBuilder.append("Question: ").append(statistics.questionTitle()).append("\n")
                    .append("The least frequent answer is: ").append(mostFrequentQuestionAnswer).append("\n")
                    .append(mostFrequentQuestionVotes).append(" choose this variant").append("\n\n");
        }
        System.out.println(stringBuilder);
    }
}
