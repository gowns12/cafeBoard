package board.Post;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

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
}
