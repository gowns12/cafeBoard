package board.board.dto;

import jakarta.validation.constraints.Size;

public record BoardRequestDto(
        Long id,
        @Size(min = 1,max = 10) String name
) {
}
