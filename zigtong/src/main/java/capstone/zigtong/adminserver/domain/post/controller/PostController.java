package capstone.zigtong.adminserver.domain.post.controller;

import capstone.zigtong.adminserver.domain.post.dto.PostCreateDto;
import capstone.zigtong.adminserver.domain.post.dto.PostDto;
import capstone.zigtong.adminserver.domain.post.dto.PostUpdateDto;
import capstone.zigtong.adminserver.domain.post.service.PostService;
import capstone.zigtong.adminserver.global.exception.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static capstone.zigtong.adminserver.global.security.constant.EndpointConstant.ENDPOINT_PREFIX;

@RestController
@RequiredArgsConstructor
@RequestMapping(ENDPOINT_PREFIX+"/admins")
public class PostController {
    private final PostService postService;
    @Operation(summary = "게시글 생성", description = "구인 게시글을 생성합니다")
    @PostMapping("/{adminId}/posts")
    public ResponseEntity<CommonResponse> createPost(@PathVariable String adminId, @RequestBody PostCreateDto postCreateDto){
       /* User principal = getPrincipal();
        String accountId = principal.getUsername();*/
        String accountId = "1";
        PostDto postDto = postService.createPost(adminId, accountId, postCreateDto.toDto());

        return ResponseEntity.ok()
                .body(new CommonResponse(postDto.getId().toString(), "successfully created"));
    }
    @Operation(summary = "게시글 수정", description = "구인 게시글을 수정합니다")
    @PutMapping("/{adminId}/posts/{postId}")
    public ResponseEntity<CommonResponse> updatePost(
            @PathVariable String adminId,
            @PathVariable String postId,
            @RequestBody @Valid PostUpdateDto postUpdateDto
    ){
        /*org.springframework.security.core.userdetails.User principal =
                getPrincipal();
        String accountId = principal.getUsername();*/
        String accountId = "1";
        PostDto postDto =
                postService.updatePost(adminId, accountId, Integer.valueOf(postId), postUpdateDto.toDto());
        return ResponseEntity.ok()
                .body(new CommonResponse(postDto.getId().toString(), "successfully updated"));
    }
    @Operation(summary = "전체 게시글 조회", description = "전체 구인 게시글을 조회합니다")
    @GetMapping("/{adminId}/posts")
    public ResponseEntity<List<PostDto>> getAllPosts(@PathVariable String adminId){
        List<PostDto> postDtoList = postService.getAllPosts(adminId);
        return ResponseEntity.ok()
                .body(postDtoList);
    }
    @Operation(summary = "게시글 조회", description = "게시글id에 해당하는 구인 게시글을 조회합니다")
    @GetMapping("/{adminId}/posts/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable String adminId, @PathVariable String postId){
        PostDto postDto = postService.getPost(adminId, Integer.valueOf(postId));
        return ResponseEntity.ok()
                .body(postDto);
    }
    @Operation(summary = "게시글 삭제", description = "게시글id에 해당하는 구인 게시글을 삭제합니다")
    @DeleteMapping("/{adminId}/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable String adminId, @PathVariable String postId){
        postService.deletePost(adminId, Integer.valueOf(postId));
        return ResponseEntity.ok()
                .body("successfully deleted");
    }
    /*private User getPrincipal() {
        return (User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }*/
}
