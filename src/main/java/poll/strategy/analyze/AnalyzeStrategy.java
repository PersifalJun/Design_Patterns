package poll.strategy.analyze;

import poll.strategy.statistics.QuestionStatistics;

import java.util.List;

public interface AnalyzeStrategy {
    void makeAnalyze(List<QuestionStatistics> questionStatisticsList);
}
