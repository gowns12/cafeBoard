package board.board;

import jakarta.validation.constraints.Size;

public record BoardResponseDto(
        Long id,
        String name
) {
}
