package board.Post.controller;

import board.Post.service.PostService;
import board.Post.dto.PostRequestDto;
import board.Post.dto.PostResponseDto;
import board.User.UserInfo;
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

    @PutMapping("/posts")
    void updatePost(@Valid@RequestBody PostRequestDto postRequestDto){
        postService.update(postRequestDto);
    }

    @PatchMapping("/posts/{postId}/changeisvisible")
    void changeIsVisible(@PathVariable Long postId){
        postService.changeIsVisible(postId);
    }

    @GetMapping("/posts/search")
    List<PostResponseDto> searchPost(@RequestParam String title){
        return postService.searchPost(title);
    }

    @PatchMapping("/posts/{postId}/recommend")
    void recommendPost(@RequestBody@Valid UserInfo userInfo, @PathVariable Long postId){
        postService.recommend(userInfo, postId);
    }

    @GetMapping("/posts/sorted")
    List<PostResponseDto> sortPosts(@RequestParam String orderby){
        postService.sort();
    }
}
