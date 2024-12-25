package board.comment.dto;

import java.time.LocalDateTime;

public record CommentResponseDto(
        Long id,
        String content,
        String author,
        LocalDateTime createAt,
        Long postId
) {
}
