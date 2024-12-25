package board.board;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardController {
    BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/boards")
    void createBoard(@Valid @RequestBody BoardRequestDto boardRequestDto){
        boardService.create(boardRequestDto);
    }

    @GetMapping("/boards")
    List<BoardResponseDto> readBoards(){
        return boardService.readAll();
    }
}
