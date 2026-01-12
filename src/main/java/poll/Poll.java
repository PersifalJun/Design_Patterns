package poll;

import poll.builder.PollBuilder;

import java.util.List;

public record Poll(
        String pollName,
        List<PollQuestion> pollQuestionList
) {

    public static PollBuilder builder() {
        return new PollBuilder();
    }

    @Override
    public String toString() {
        return STR."Poll{pollName='\{pollName}', pollQuestionList=\{pollQuestionList}}";
    }
}
