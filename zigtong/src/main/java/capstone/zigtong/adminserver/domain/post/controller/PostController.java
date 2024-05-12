package capstone.zigtong.adminserver.domain.post.controller;

import capstone.zigtong.adminserver.domain.post.dto.PostCreateDto;
import capstone.zigtong.adminserver.domain.post.dto.PostDto;
import capstone.zigtong.adminserver.domain.post.dto.PostUpdateDto;
import capstone.zigtong.adminserver.domain.post.service.PostService;
import capstone.zigtong.adminserver.global.exception.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @PostMapping("/api/admins/{adminId}/posts")
    public ResponseEntity<CommonResponse> createPost(@PathVariable String adminId, @RequestBody PostCreateDto postCreateDto){
        User principal = getPrincipal();
        String accountId = principal.getUsername();
        PostDto postDto = postService.createPost(adminId, accountId, postCreateDto.toDto());

        return ResponseEntity.ok()
                .body(new CommonResponse(postDto.getId(), "successfully created"));
    }
    @PutMapping("/api/admins/{adminId}/posts/{postId}")
    public ResponseEntity<CommonResponse> updatePost(
            @PathVariable String adminId,
            @PathVariable String postId,
            @RequestBody @Valid PostUpdateDto postUpdateDto
    ){
        org.springframework.security.core.userdetails.User principal =
                getPrincipal();
        String accountId = principal.getUsername();
        PostDto postDto =
                postService.updatePost(adminId, accountId, postId, postUpdateDto.toDto());
        return ResponseEntity.ok()
                .body(new CommonResponse(postDto.getId(), "successfully updated"));
    }
    @GetMapping("/api/admins{adminId}/posts")
    public ResponseEntity<List<PostDto>> getAllPosts(@PathVariable String adminId){
        List<PostDto> postDtoList = postService.getAllPosts(adminId);
        return ResponseEntity.ok()
                .body(postDtoList);
    }
    @GetMapping("/api/admins{adminId}/posts/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable String adminId, @PathVariable String postId){
        PostDto postDto = postService.getPost(adminId, postId);
        return ResponseEntity.ok()
                .body(postDto);
    }
    private User getPrincipal() {
        return (User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
