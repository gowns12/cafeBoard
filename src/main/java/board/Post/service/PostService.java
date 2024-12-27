package board.Post.service;

import board.Post.dto.PostRequestDto;
import board.Post.dto.PostResponseDto;
import board.Post.entity.Post;
import board.Post.repository.PostRepository;
import board.comment.Repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    PostRepository postRepository;
    CommentRepository commentRepository;

    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public List<PostResponseDto> readAll(Long boardId) {
        return postRepository.findByBoardId(boardId).stream()
                .filter(Post::isVisible)
                .map(o->new PostResponseDto(
                        o.getId(),
                        o.getTitle(),
                        o.getContent(),
                        o.getCreatedAt(),
                        o.getBoardId(),
                        o.getViewCount(),
                        commentRepository.countByPostId(o.getId())
                ))
                .toList();
    }

    @Transactional
    public PostResponseDto read(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new IllegalArgumentException("Post not found for id: " + postId));
        post.ViewCountIncrease();
        return new PostResponseDto(postId,post.getTitle(), post.getContent(), post.getCreatedAt(),post.getBoardId(),post.getViewCount(),commentRepository.countByPostId(postId));
    }

    public void create(PostRequestDto postRequestDto) {
        postRepository.save(new Post(postRequestDto.title(),postRequestDto.content(), LocalDateTime.now(),postRequestDto.boardId()));
    }

    public void changeIsVisible(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시글입니다"));
        post.changeIsVisible();
    }

    public List<PostResponseDto> searchPost(String title) {
            return postRepository.findAllByTitle(title).stream()
                    .filter(Post::isVisible)
                    .map(o->new PostResponseDto(
                            o.getId(),
                            o.getTitle(),
                            o.getContent(),
                            o.getCreatedAt(),
                            o.getBoardId(),
                            o.getViewCount(),
                            commentRepository.countByPostId(o.getId())
                    ))
                    .toList();

    }
}
