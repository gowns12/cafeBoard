package board.Post.service;

import board.Post.dto.PostRequestDto;
import board.Post.dto.PostResponseDto;
import board.Post.entity.Post;
import board.Post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostResponseDto> readAll(Long boardId) {
        return postRepository.findByBoardId(boardId).stream()
                .map(o->new PostResponseDto(
                        o.getId(),
                        o.getTitle(),
                        o.getContent(),
                        o.getCreatedAt(),
                        o.getBoardId(),
                        o.getViewCount()
                ))
                .toList();
    }

    @Transactional
    public PostResponseDto read(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new IllegalArgumentException("Post not found for id: " + postId));
        post.ViewCountIncrease();
        return new PostResponseDto(postId,post.getTitle(), post.getContent(), post.getCreatedAt(),post.getBoardId(),post.getViewCount());
    }

    public void create(PostRequestDto postRequestDto) {
        postRepository.save(new Post(postRequestDto.title(),postRequestDto.content(), LocalDateTime.now(),postRequestDto.boardId()));
    }
}
