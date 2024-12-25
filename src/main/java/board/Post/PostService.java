package board.Post;

import org.springframework.stereotype.Service;

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
                        o.getBoardId()
                ))
                .toList();
    }

    public PostResponseDto read(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        assert post != null;
        return new PostResponseDto(postId,post.getTitle(), post.getContent(), post.getCreatedAt(),post.getBoardId());
    }

    public void create(PostRequestDto postRequestDto) {
        postRepository.save(new Post(postRequestDto.title(),postRequestDto.content(), LocalDateTime.now(),postRequestDto.boardId()));
    }
}
