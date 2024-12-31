package board.Post.entity;

import board.Post.dto.PostRequestDto;
import board.Post.recommend.Recommend;
import board.Post.recommend.RecommendId;
import board.User.UserInfo;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private Long boardId;
    private Integer viewCount = 0;
    private boolean isVisible = true;
    @OneToMany(mappedBy = "id.post")
    private List<Recommend> recommends = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostHashTag> tags = new ArrayList<>();

    protected Post() {
    }

    public Post(String title, String content, LocalDateTime createdAt, Long boardId) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.boardId = boardId;
    }

    public Post(String title, String content, LocalDateTime createdAt, Long boardId, List<PostHashTag> tags) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.boardId = boardId;
        this.tags = tags;
    }


    public boolean isVisible() {
        return isVisible;
    }

    public void ViewCountIncrease() {
        this.viewCount++;
    }

    public void changeIsVisible() {
        this.isVisible = !isVisible;
    }


    public void update(@Valid PostRequestDto postRequestDto, List<PostHashTag> tags) {
        this.title = postRequestDto.title();
        this.content = postRequestDto.content();
        this.boardId = postRequestDto.boardId();
        this.tags.clear();
        this.tags.addAll(tags);

    }

    public void recommend(UserInfo userInfo) {
        recommends.add(new Recommend(new RecommendId(userInfo, this)));
    }

    public void addAllTags(List<PostHashTag> postHashTags) {
        tags.addAll(postHashTags);
    }
}

