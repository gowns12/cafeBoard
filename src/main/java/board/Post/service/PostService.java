package board.Post.service;

import board.Post.dto.PostSortListResponse;
import board.Post.dto.PostRequestDto;
import board.Post.dto.PostResponseDto;
import board.Post.entity.Post;
import board.Post.entity.PostHashTag;
import board.Post.recommend.Recommend;
import board.Post.recommend.RecommendId;
import board.Post.recommend.RecommendRepository;
import board.Post.repository.PostRepository;
import board.User.UserInfo;
import board.User.UserInfoRepository;
import board.User.UserRequest;
import board.comment.Repository.CommentRepository;
import board.hashtag.HashTag;
import board.hashtag.HashTagRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostService {
    PostRepository postRepository;
    CommentRepository commentRepository;
    HashTagRepository hashTagRepository;
    RecommendRepository recommendRepository;
    UserInfoRepository userInfoRepository;


    public List<PostResponseDto> readAll(Long boardId) {
        return postRepository.findByBoardId(boardId).stream()
                .filter(Post::isVisible)
                .map(o -> new PostResponseDto(
                        o.getId(),
                        o.getTitle(),
                        o.getContent(),
                        o.getTags().stream()
                                .map(t -> hashTagRepository.findById(t.getHashTagId())
                                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 해쉬태그입니다."))
                                )
                                .toList(),
                        o.getCreatedAt(),
                        o.getBoardId(),
                        o.getViewCount(),
                        commentRepository.countByPostId(o.getId()),
                        o.getRecommends().size()
                ))
                .toList();
    }

    @Transactional//조회 수 증가 데이터베이스 반영을 위해 트랜잭셔널 추가
    public PostResponseDto read(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found for id: " + postId));
        post.ViewCountIncrease();
        return new PostResponseDto(
                postId,
                post.getTitle(),
                post.getContent(),
                post.getTags().stream()
                        .map(t -> hashTagRepository.findById(t.getHashTagId())
                                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 해쉬태그입니다."))
                        )
                        .toList(),
                post.getCreatedAt(),
                post.getBoardId(),
                post.getViewCount(),
                commentRepository.countByPostId(postId),
                post.getRecommends().size());
    }

    @Transactional
    public void create(PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto.title(), postRequestDto.content(), LocalDateTime.now(), postRequestDto.boardId());
        List<String> tags = postRequestDto.hasTags() != null ? postRequestDto.hasTags() : Collections.emptyList();
        List<PostHashTag> postHashTags = tags.stream()
                .map(str -> {
                    HashTag hashTag = hashTagRepository.findByName(str).orElseGet(() -> hashTagRepository.save(new HashTag(str)));
                    return new PostHashTag(post, hashTag.getId());
                })
                .toList();
        post.addAllTags(postHashTags);
        postRepository.save(post);
    }

    @Transactional
    public void changeIsVisible(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다"));
        post.changeIsVisible();
    }

    public List<PostResponseDto> searchPost(String title) {
        return postRepository.findByTitle(title).stream()
                .filter(Post::isVisible)
                .map(o -> new PostResponseDto(
                        o.getId(),
                        o.getTitle(),
                        o.getContent(),
                        o.getTags().stream()
                                .map(t -> hashTagRepository.findById(t.getHashTagId())
                                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 해쉬태그입니다."))
                                )
                                .toList(),
                        o.getCreatedAt(),
                        o.getBoardId(),
                        o.getViewCount(),
                        commentRepository.countByPostId(o.getId()),
                        o.getRecommends().size()
                ))
                .toList();

    }

    @Transactional
    public void update(@Valid PostRequestDto postRequestDto, Long postid) {
        Post post = postRepository.findById(postid).orElseThrow();
        List<String> tags = postRequestDto.hasTags() != null ? postRequestDto.hasTags() : Collections.emptyList();
        List<PostHashTag> postHashTags = tags.stream()
                .map(str -> {
                    HashTag hashTag = hashTagRepository.findByName(str).orElseGet(() -> new HashTag(str));
                    return new PostHashTag(post, hashTag.getId());
                })
                .toList();
        post.update(postRequestDto, postHashTags);
    }

    @Transactional
    public void recommend(UserRequest userRequest, Long postId) {
        UserInfo userInfo = userInfoRepository.findById(userRequest.id()).orElseThrow(()->new IllegalArgumentException("유저가 존재하지 않습니다."));
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        if (recommendRepository.findById(new RecommendId(userInfo, post)).isPresent()) {
            Recommend recommend = recommendRepository.findById(new RecommendId(userInfo, post)).orElseThrow();
            recommendRepository.delete(recommend);
            post.getRecommends().remove(recommend);
        } else {
            Recommend r = new Recommend();
            post.recommend(r);
        }
    }

    public List<PostSortListResponse> sort(String orderby) {
        if (orderby.equals("viewcount")) {
            return postRepository.findAllByOrderByViewCountDesc().stream()
                    .map(o -> new PostSortListResponse(
                            o.getId(),
                            o.getTitle(),
                            o.getViewCount(),
                            o.getRecommends().size()
                    ))
                    .toList();
        }
        if (orderby.equals("recommendcount")) {
            return postRepository.findAllByOrderByRecommendsDesc().stream()
                    .map(o -> new PostSortListResponse(
                            o.getId(),
                            o.getTitle(),
                            o.getViewCount(),
                            o.getRecommends().size()
                    ))
                    .toList();
        }
        return postRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(o -> new PostSortListResponse(
                                o.getId(),
                                o.getTitle(),
                                o.getViewCount(),
                                o.getRecommends().size()
                        )

                )
                .toList();
    }
}
