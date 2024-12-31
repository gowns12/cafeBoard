package board.Post.dto;

import board.hashtag.HashTag;

import java.time.LocalDateTime;
import java.util.List;

public record PostResponseDto(
        Long id,
        String title,
        String content,
        List<HashTag> hashTags,
        LocalDateTime createdAt,
        Long boardId,
        Integer viewCount,
        Integer commentCount,
        Integer recommendCount
) {
}
