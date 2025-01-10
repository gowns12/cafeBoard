package board.Post.controller;

import board.Post.dto.PostSortListResponse;
import board.Post.service.PostService;
import board.Post.dto.PostRequestDto;
import board.Post.dto.PostResponseDto;
import board.User.UserRequest;
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

    @PutMapping("/posts/{postid}")
    void updatePost(@Valid@RequestBody PostRequestDto postRequestDto,@PathVariable Long postid){
        postService.update(postRequestDto, postid);
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
    void recommendPost(@RequestBody@Valid UserRequest userRequest, @PathVariable Long postId){
        postService.recommend(userRequest, postId);
    }

    @GetMapping("/posts/sorted")
    List<PostSortListResponse> sortPosts(@RequestParam(required = false) String orderby){
        return postService.sort(orderby);
    }
}
