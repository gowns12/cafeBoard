package board.board.serice;

import board.board.dto.BoardRequestDto;
import board.board.dto.BoardResponseDto;
import board.board.entity.Board;
import board.board.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<BoardResponseDto> readAll() {
        return boardRepository.findAll().stream()
                .map(o -> new BoardResponseDto(
                        o.getId(),
                        o.getName()
                ))
                .toList();
    }

    public void create(BoardRequestDto boardRequestDto) {
        boardRepository.save(new Board(boardRequestDto.name()));
    }
}
