package board.comment.Service;

import board.comment.Comment;
import board.comment.Repository.CommentRepository;
import board.comment.dto.CommentRequestDto;
import board.comment.dto.CommentResponseDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void create(CommentRequestDto commentRequestDto) {
        commentRepository.save(new Comment(commentRequestDto.content(), commentRequestDto.author(), LocalDateTime.now(),commentRequestDto.postId()));
    }

    public List<CommentResponseDto> read(Long postId) {
        return commentRepository.findByPostId(postId).stream()
                .map(o->new CommentResponseDto(
                        o.getId(),
                        o.getContent(),
                        o.getAuthor(),
                        o.getCreateAt(),
                        o.getPostId()
                ))
                .toList();
    }
}
