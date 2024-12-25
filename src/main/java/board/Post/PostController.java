package board.Post;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    List<PostResponseDto> readPosts(@RequestParam Long boardId){
        return postService.readAll(boardId);
    }

    @GetMapping("/posts/{postId}")
    PostResponseDto readPost(@PathVariable Long postId){
        return postService.read(postId);
    }

    @PostMapping("/posts")
    void createPost(@Valid@RequestBody PostRequestDto postRequestDto){
        postService.create(postRequestDto);
    }
}
