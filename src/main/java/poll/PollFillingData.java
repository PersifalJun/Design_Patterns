package poll;

import java.util.List;

public record PollFillingData(
        String userLogin,
        List<PollQuestionResponse> pollQuestionResponseList
) {}

