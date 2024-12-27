package board.comment.Service;

import board.Post.entity.Post;
import board.Post.repository.PostRepository;
import board.comment.Entity.Comment;
import board.comment.Repository.CommentRepository;
import board.comment.dto.CommentRequestDto;
import board.comment.dto.CommentResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    CommentRepository commentRepository;
    PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Transactional
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
