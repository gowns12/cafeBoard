package board.Post.dto;

public record PostSortListResponse(
        Long id,
        String title,
        Integer viewCount,
        Integer recommendCount
) {
}
