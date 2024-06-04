package capstone.zigtong.adminserver.domain.post.service;

import capstone.zigtong.adminserver.domain.admin.Admin;
import capstone.zigtong.adminserver.domain.admin.repository.AdminRepository;
import capstone.zigtong.adminserver.domain.post.Post;
import capstone.zigtong.adminserver.domain.post.dto.PostDto;
import capstone.zigtong.adminserver.domain.post.repository.PostRepository;
import capstone.zigtong.adminserver.domain.workspacePost.WorkspacePost;
import capstone.zigtong.adminserver.domain.workspacePost.repository.WorkspacePostRepository;
import capstone.zigtong.adminserver.global.codes.ErrorCode;
import capstone.zigtong.adminserver.global.exception.CustomException;
import capstone.zigtong.adminserver.infra.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static capstone.zigtong.adminserver.global.codes.ErrorCode.ACCOUNT_NOT_FOUND;
import static capstone.zigtong.adminserver.global.codes.ErrorCode.POST_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PostService {
    private final S3Service s3Service;
    private final PostRepository postRepository;
    private final AdminRepository adminRepository;
    private final WorkspacePostRepository workspacePostRepository;
    @Transactional
    public PostDto createPost(String adminId, PostDto postDto, List<MultipartFile> images){
        Admin admin = getAdminById(adminId);
        Post post = new Post(admin, postDto);
        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile image : images) {
            String imageUrl = s3Service.uploadImage(adminId, image);
            imageUrls.add(imageUrl);
        }
        for (String imageUrl : imageUrls) {
            WorkspacePost workspacePost = new WorkspacePost(imageUrl);
            workspacePostRepository.save(workspacePost);
            post.addWorkspacePost(workspacePost);
        }
        postRepository.save(post);
        return PostDto.fromEntity(post);
    }
@Transactional
    public PostDto updatePost(String adminId, Integer postId, PostDto postDto) {
    Admin admin = getAdminById(adminId);
    Post post = postRepository.findById(postId).orElseThrow(
            () -> new CustomException(POST_NOT_FOUND)
    );
    if(!post.isAdmin(admin)){
        throw new IllegalArgumentException("수정 권한이 없습니다.");
    }
    post.updateByDto(postDto);
    return PostDto.fromEntity(post);
    }

    public List<PostDto> getAllPosts(String adminId) {
        Admin admin = getAdminById(adminId);
        return admin.getPostList().stream()
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .map(PostDto::fromEntity)
                .toList();
    }
    public PostDto getPost(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new CustomException(POST_NOT_FOUND)
        );
        return PostDto.fromEntity(post);
    }
    private Admin getAdminById(String adminId){
        Admin admin = adminRepository.findById(adminId).orElseThrow(
                () -> new CustomException(ACCOUNT_NOT_FOUND)
        );
        return admin;
    }

    public void deletePost(String adminId, Integer postId) {
        Admin admin = getAdminById(adminId);
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new CustomException(POST_NOT_FOUND)
        );
        if(!post.isAdmin(admin)){
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }
        postRepository.delete(post);
    }
}
