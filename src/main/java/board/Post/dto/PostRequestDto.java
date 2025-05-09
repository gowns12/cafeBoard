package board.Post.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record PostRequestDto(
        @Size(min = 1,max = 20)
        String title,
        @Size(min = 1,max = 300)
        String content,
        @NotNull
        Long boardId,
        @Size(min = 0,max = 10)
        List<String> hasTags
) {
}
