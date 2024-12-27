package board.Post.repository;

import board.Post.dto.PostResponseDto;
import board.Post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByBoardId(Long boardId);

    List<Post> findAllByTitle(String title);

    List<Post> findByIsVisible(boolean isvisible);
}
