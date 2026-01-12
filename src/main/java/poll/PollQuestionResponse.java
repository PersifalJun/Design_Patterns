package poll;

import java.util.List;

public record PollQuestionResponse(
        PollQuestion pollQuestion,
        List<String> selectedVariants
) {
}
