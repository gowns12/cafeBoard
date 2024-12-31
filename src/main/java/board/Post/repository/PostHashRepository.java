package board.Post.repository;

import board.Post.entity.PostHashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostHashRepository extends JpaRepository<PostHashTag,Long> {
}
