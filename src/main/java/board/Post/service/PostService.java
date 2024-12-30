package board.Post.service;

import board.Post.dto.PostRequestDto;
import board.Post.dto.PostResponseDto;
import board.Post.entity.Post;
import board.Post.entity.PostHashTag;
import board.Post.recommend.Recommend;
import board.Post.recommend.RecommendId;
import board.Post.recommend.RecommendRepository;
import board.Post.repository.PostRepository;
import board.User.UserInfo;
import board.comment.Repository.CommentRepository;
import board.hashtag.HashTag;
import board.hashtag.HashTagRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class PostService {
    PostRepository postRepository;
    CommentRepository commentRepository;
    HashTagRepository hashTagRepository;
    RecommendRepository recommendRepository;



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
                        commentRepository.countByPostId(o.getId()),
                        o.getRecommends().size()
                ))
                .toList();
    }

    @Transactional
    public PostResponseDto read(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new IllegalArgumentException("Post not found for id: " + postId));
        post.ViewCountIncrease();
        return new PostResponseDto(postId,post.getTitle(), post.getContent(), post.getCreatedAt(),post.getBoardId(),post.getViewCount(),commentRepository.countByPostId(postId),post.getRecommends().size());
    }

    public void create(PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto.title(),postRequestDto.content(), LocalDateTime.now(),postRequestDto.boardId());
        List<PostHashTag> tags = postRequestDto.hasTags().stream()
                        .map(str -> {
                                    HashTag hashTag = hashTagRepository.findByName(str).orElseGet(() -> new HashTag(str));
                                    return new PostHashTag(post,hashTag.getId());
                                })
                                .toList();
        postRepository.save(new Post(postRequestDto.title(),postRequestDto.content(), LocalDateTime.now(),postRequestDto.boardId(),tags));
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
                            commentRepository.countByPostId(o.getId()),
                            o.getRecommends().size()
                    ))
                    .toList();

    }

    @Transactional
    public void update(@Valid PostRequestDto postRequestDto) {
         Post post = postRepository.findById(postRequestDto.id()).orElseThrow();
        List<PostHashTag> tags = postRequestDto.hasTags().stream()
                .map(str -> {
                    HashTag hashTag = hashTagRepository.findByName(str).orElseGet(() -> new HashTag(str));
                    return new PostHashTag(post,hashTag.getId());
                })
                .toList();
         post.update(postRequestDto,tags);
    }

    @Transactional
    public void recommend(UserInfo userInfo, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("게시글이 존재하지 않습니다."));
        if (recommendRepository.findById(new RecommendId(userInfo,post)).isPresent()){
            post.getRecommends().remove(new Recommend(new RecommendId(userInfo,post)));
        }
        else {
            post.recommend(userInfo);
        }
    }

    public void sort() {
        postRepository.orderByViewCount;
    }
}
