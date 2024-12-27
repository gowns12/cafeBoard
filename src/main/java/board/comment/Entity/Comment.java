package board.comment.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    private String content;
    private String author;
    private LocalDateTime createAt;
    private Long postId;

    public Comment() {
    }

    public Comment(String content, String author, LocalDateTime createAt, Long postId) {
        this.content = content;
        this.author = author;
        this.createAt = createAt;
        this.postId = postId;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public Long getPostId() {
        return postId;
    }
}
