package poll.strategy.analyze;

import poll.poll_data.PollFillingData;
import poll.strategy.statistics.QuestionStatistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PollAnalyzer {
    private AnalyzeStrategy analyzerStrategy;

    public PollAnalyzer(AnalyzeStrategy strategy) {
        this.analyzerStrategy = strategy;
    }

    public void changeAnalyzeStrategy(AnalyzeStrategy strategy) {
        this.analyzerStrategy = strategy;
    }

    public void analyzePoll(List<PollFillingData> pollFillingDataList) {
        Map<String, QuestionStatistics> questionStatisticsMap = new HashMap<>();

        for (var pollFillingData : pollFillingDataList) {

            var currentUser = pollFillingData.userLogin();

            for (var questionResponse : pollFillingData.pollQuestionResponseList()) {
                var questionTitle = questionResponse.pollQuestion().title();
                questionStatisticsMap.putIfAbsent(questionTitle, new QuestionStatistics(
                        questionTitle,
                        new HashMap<>(),
                        new HashMap<>()
                ));
                var statistics = questionStatisticsMap.get(questionTitle);

                var selectedVariantsCount = statistics.selectedVariantsCount();
                questionResponse.selectedVariants().forEach(variant -> {
                    selectedVariantsCount.putIfAbsent(variant, 0);
                    selectedVariantsCount.put(variant, selectedVariantsCount.get(variant) + 1);
                });
                statistics.userSelectedVariantsCount().put(
                        currentUser,
                        questionResponse.selectedVariants().size()
                );
            }
        }
        analyzerStrategy.makeAnalyze(questionStatisticsMap.values().stream().toList());
    }
}
