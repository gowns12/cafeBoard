package board.comment.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CommentRequestDto(
        @Size(min = 1,max = 100)
        String content,
        @Size(min = 1,max = 10)
        String author,
        @NotNull
        Long postId
) {
}
