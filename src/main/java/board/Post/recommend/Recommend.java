package board.Post.recommend;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@AllArgsConstructor
@Data
public class Recommend {
    @EmbeddedId
    private RecommendId id;
}
