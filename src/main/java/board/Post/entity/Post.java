package board.Post.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private Long boardId;
    private int viewCount=0;

    protected Post() {
    }

    public Post(String title, String content, LocalDateTime createdAt, Long boardId) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.boardId = boardId;
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

    public void ViewCountIncrease() {
        this.viewCount++;
    }
}
