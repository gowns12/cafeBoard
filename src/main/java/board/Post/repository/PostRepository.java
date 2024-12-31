package board.Post.repository;

import board.Post.controller.PostSortListResponse;
import board.Post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByBoardId(Long boardId);

    List<Post> findByTitle(String title);

    List<Post> findByIsVisible(boolean isvisible);

    List<Post> findAllByOrderByViewCountDesc();

    @Query("SELECT p FROM Post p ORDER BY SIZE(p.recommends) DESC")
    List<Post> findAllByOrderByRecommendsDesc();

    List<Post> findAllByOrderByCreatedAtDesc();
}
