package poll.poll_data;

import poll.response.PollQuestionResponse;

import java.util.List;

public record PollFillingData(
        String userLogin,
        List<PollQuestionResponse> pollQuestionResponseList
) {}

