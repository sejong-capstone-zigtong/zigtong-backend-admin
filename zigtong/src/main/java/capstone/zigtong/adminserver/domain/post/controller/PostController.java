package capstone.zigtong.adminserver.domain.post.controller;

import capstone.zigtong.adminserver.domain.post.dto.PostCreateDto;
import capstone.zigtong.adminserver.domain.post.dto.PostDto;
import capstone.zigtong.adminserver.domain.post.dto.PostStatusUpdateDto;
import capstone.zigtong.adminserver.domain.post.dto.PostUpdateDto;
import capstone.zigtong.adminserver.domain.post.service.PostService;
import capstone.zigtong.adminserver.global.exception.CommonResponse;
import capstone.zigtong.adminserver.global.security.util.SecurityContextUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static capstone.zigtong.adminserver.global.security.constant.EndpointConstant.ENDPOINT_PREFIX;

@RestController
@RequiredArgsConstructor
@RequestMapping(ENDPOINT_PREFIX+"/admins")
public class PostController {
    private final PostService postService;
    @Operation(summary = "게시글 생성", description = "구인 게시글을 생성합니다")
    @PostMapping("/posts", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CommonResponse> createPost(@RequestPart("post") PostCreateDto postCreateDto,
                                                     @RequestPart("images") List<MultipartFile> images){
        String adminId = SecurityContextUtil.extractAdminId();
        PostDto postDto = postService.createPost(adminId,postCreateDto.toDto(), images);

        return ResponseEntity.ok()
                .body(new CommonResponse(postDto.getId().toString(), "successfully created"));
    }
    @Operation(summary = "게시글 수정", description = "구인 게시글을 수정합니다")
    @PutMapping("/posts/{postId}")
    public ResponseEntity<CommonResponse> updatePost(
            @PathVariable String postId,
            @RequestBody @Valid PostUpdateDto postUpdateDto
    ){
        String adminId = SecurityContextUtil.extractAdminId();
        PostDto postDto =
                postService.updatePost(adminId, Integer.valueOf(postId), postUpdateDto.toDto());
        return ResponseEntity.ok()
                .body(new CommonResponse(postDto.getId().toString(), "successfully updated"));
    }
    @Operation(summary = "전체 게시글 조회", description = "전체 구인 게시글을 조회합니다")
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts(){
        String adminId = SecurityContextUtil.extractAdminId();
        List<PostDto> postDtoList = postService.getAllPosts(adminId);
        return ResponseEntity.ok()
                .body(postDtoList);
    }
    @Operation(summary = "게시글 조회", description = "게시글id에 해당하는 구인 게시글을 조회합니다")
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPost( @PathVariable String postId){
        PostDto postDto = postService.getPost(Integer.valueOf(postId));
        return ResponseEntity.ok()
                .body(postDto);
    }
    @Operation(summary = "게시글 삭제", description = "게시글id에 해당하는 구인 게시글을 삭제합니다")
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> deletePost( @PathVariable String postId){
        String adminId = SecurityContextUtil.extractAdminId();
        postService.deletePost(adminId, Integer.valueOf(postId));
        return ResponseEntity.ok()
                .body("successfully deleted");
    }

    @Operation(summary = "게시글 상태 수정", description = "구인 게시글의 상태를 수정합니다")
    @PutMapping("/posts/{postId}/postStatus")
    public ResponseEntity<CommonResponse> updatePostStatus(
            @PathVariable String postId,
            @RequestBody @Valid PostStatusUpdateDto postStatusUpdateDto
    ){
        String adminId = SecurityContextUtil.extractAdminId();
        PostDto postDto =
                postService.updatePostStatus(adminId, Integer.valueOf(postId), postStatusUpdateDto);
        return ResponseEntity.ok()
                .body(new CommonResponse(postDto.getId().toString(), "successfully updated"));
    }

}
