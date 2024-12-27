package board.Post.controller;

import board.Post.entity.Post;
import board.Post.service.PostService;
import board.Post.dto.PostRequestDto;
import board.Post.dto.PostResponseDto;
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

    @PatchMapping("/post/{postId}/changeisvisible")
    void changeIsVisible(@PathVariable Long postId){
        postService.changeIsVisible(postId);
    }

    @GetMapping("/post/search")
    List<PostResponseDto> searchPost(@RequestParam String title){
        return postService.searchPost(title);
    }
}
