package board.Post.entity;

import jakarta.persistence.*;

@Entity
public class PostHashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Post post;
    private Long HashTagId;

    public PostHashTag() {
    }

    public PostHashTag(Post post, Long hashTagId) {
        this.post = post;
        HashTagId = hashTagId;
    }

    public Long getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public Long getHashTagId() {
        return HashTagId;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setHashTagId(Long hashTagId) {
        HashTagId = hashTagId;
    }
}
