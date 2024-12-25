package board.Post;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PostRequestDto(
        Long id,
        @Size(min = 1,max = 20)
        String title,
        @Size(min = 1,max = 300)
        String content,
        @NotNull
        Long boardId
) {
}
