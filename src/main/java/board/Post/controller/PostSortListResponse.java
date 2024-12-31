package board.Post.controller;

public record PostSortListResponse(
        Long id,
        String title,
        Integer viewCount,
        Integer recommendCount
) {
}
