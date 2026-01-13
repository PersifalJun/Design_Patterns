package poll.response;

import poll.question.PollQuestion;

import java.util.List;

public record PollQuestionResponse(
        PollQuestion pollQuestion,
        List<String> selectedVariants
) {
}
