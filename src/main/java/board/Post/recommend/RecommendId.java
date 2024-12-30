package board.Post.recommend;

import board.Post.entity.Post;
import board.User.UserInfo;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecommendId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserInfo userInfo;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
