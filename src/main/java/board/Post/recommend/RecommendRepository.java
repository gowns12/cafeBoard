package board.Post.recommend;

import board.Post.entity.Post;
import board.User.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendRepository extends JpaRepository<Recommend,RecommendId> {
}
