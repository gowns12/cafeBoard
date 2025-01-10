package board.comment.controller;

import board.comment.Service.CommentService;
import board.comment.dto.CommentRequestDto;
import board.comment.dto.CommentResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comments")
    void createComments(@RequestBody CommentRequestDto commentRequestDto){
        commentService.create(commentRequestDto);
    }

    @GetMapping("/comments")
    List<CommentResponseDto> readComments(@RequestParam Long postId){
        return commentService.read(postId);
    }
}
