package board.Post;

import java.time.LocalDateTime;

public record PostResponseDto(
        Long id,
        String title,
        String content,
        LocalDateTime createdAt,
        Long boardId
) {
}
