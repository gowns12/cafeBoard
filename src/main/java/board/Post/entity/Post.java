package board.Post.entity;

import board.Post.dto.PostRequestDto;
import board.hashtag.HashTag;
import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private Long boardId;
    private int viewCount = 0;
    private boolean isVisible = true;
    private boolean recommend = false;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostHashTag> tags;

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

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getBoardId() {
        return boardId;
    }

    public int getViewCount() {
        return viewCount;
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
        this.tags = tags;

    }
}
