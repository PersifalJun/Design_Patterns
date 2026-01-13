package poll.question;

import java.util.List;

public record PollQuestion(
        String title,
        int minAnswers,
        int maxAnswers,
        List<String> answers
) {
    public PollQuestion{
        if (minAnswers < 0
                || maxAnswers <= 0
                || minAnswers > maxAnswers
                || minAnswers >= answers.size()
        ) {
            throw new IllegalArgumentException("Illegal max or min answers count");
        }
    }
}
