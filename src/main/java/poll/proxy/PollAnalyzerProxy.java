package poll.proxy;

import poll.poll_data.PollFillingData;
import poll.strategy.analyze.AnalyzeStrategy;
import poll.strategy.analyze.PollAnalyzer;

import java.util.List;


public class PollAnalyzerProxy extends PollAnalyzer {

    private final PollAnalyzer delegate;

    private String currentStrategy;

    public PollAnalyzerProxy(PollAnalyzer delegate) {

        super(null);
        this.delegate = delegate;
        this.currentStrategy = extractCurrentStrategyName();
    }

    @Override
    public void analyzePoll(List<PollFillingData> pollFillingDataList){
        System.out.println(STR."*****Starting analyze poll by strategy: \{currentStrategy}");
        System.out.println();
        System.out.println("... HERE ANALYZE RESULTS ...");
        System.out.println();
        var startTime = System.currentTimeMillis();
        delegate.analyzePoll(pollFillingDataList);
        var endTime = System.currentTimeMillis();
        var totalAnalyzeTimeMS = endTime - startTime;
        System.out.println(STR."*****Ending analyze poll by strategy: \{currentStrategy},totalAnalyzeTimeMS: \{totalAnalyzeTimeMS}");
        System.out.println();
    }

    @Override
    public void changeAnalyzeStrategy(AnalyzeStrategy analyzerStrategy) {
        delegate.changeAnalyzeStrategy(analyzerStrategy);
        currentStrategy = analyzerStrategy.getClass().getSimpleName();
    }

    private String extractCurrentStrategyName() {
       try{
           var field = delegate.getClass().getDeclaredField("analyzerStrategy");
           field.setAccessible(true);
           var currentStrategy = field.get(delegate);
           return currentStrategy.getClass().getSimpleName();
       } catch (NoSuchFieldException | IllegalAccessException e) {
           e.printStackTrace();
           return "Unknown strategy";
       }
    }
}
