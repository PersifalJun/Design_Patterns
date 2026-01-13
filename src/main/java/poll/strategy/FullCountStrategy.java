package poll.strategy;

import poll.strategy.analyze.AnalyzeStrategy;
import poll.strategy.statistics.QuestionStatistics;

import java.util.List;

public class FullCountStrategy implements AnalyzeStrategy {

    @Override
    public void makeAnalyze(List<QuestionStatistics> questionStatisticsList) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("-----Full Count Analyze-----\n");

        for (var questionStats : questionStatisticsList) {

            stringBuilder.append("Question: ")
                    .append(questionStats.questionTitle())
                    .append("\n");

            var totalUserPolledCount = questionStats.userSelectedVariantsCount().size();

            for (var entry : questionStats.selectedVariantsCount().entrySet()) {
                stringBuilder.append(entry.getValue())
                        .append(" out of ")
                        .append(totalUserPolledCount)
                        .append(" chose: ")
                        .append(entry.getKey())
                        .append("\n");
            }
            stringBuilder.append("\n");
        }
        System.out.print(stringBuilder);
    }
}
