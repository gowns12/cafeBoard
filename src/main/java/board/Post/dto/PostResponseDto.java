package board.Post.dto;

import java.time.LocalDateTime;

public record PostResponseDto(
        Long id,
        String title,
        String content,
        LocalDateTime createdAt,
        Long boardId,
        int viewCount,
        int commentCount
) {
}
