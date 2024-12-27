package board.recommend;

import board.Post.entity.Post;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Recommend {

    // key: id
    // key: {postId, userId}

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private Post post;

}
