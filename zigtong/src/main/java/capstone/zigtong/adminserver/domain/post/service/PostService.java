package capstone.zigtong.adminserver.domain.post.service;

import capstone.zigtong.adminserver.domain.admin.Admin;
import capstone.zigtong.adminserver.domain.admin.repository.AdminRepository;
import capstone.zigtong.adminserver.domain.post.Post;
import capstone.zigtong.adminserver.domain.post.dto.PostDto;
import capstone.zigtong.adminserver.domain.post.repository.PostRepository;
import capstone.zigtong.adminserver.global.codes.ErrorCode;
import capstone.zigtong.adminserver.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static capstone.zigtong.adminserver.global.codes.ErrorCode.ACCOUNT_NOT_FOUND;
import static capstone.zigtong.adminserver.global.codes.ErrorCode.POST_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final AdminRepository adminRepository;
    @Transactional
    public PostDto createPost(String adminId, String accountId, PostDto postDto){
        Admin admin = getAdminById(adminId);
        Post post = new Post(admin, postDto);
        postRepository.save(post);
        return PostDto.fromEntity(post);
    }
@Transactional
    public PostDto updatePost(String adminId, String accountId, Integer postId, PostDto postDto) {
    Admin admin = getAdminById(adminId);    //accountId와 동일한 admin 인지 검증?
    Post post = postRepository.findById(postId).orElseThrow(
            () -> new CustomException(POST_NOT_FOUND)
    );
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
    public PostDto getPost(String adminId, Integer postId) {
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
        Admin admin = adminRepository.findById(adminId).orElseThrow(
                () -> new CustomException(ACCOUNT_NOT_FOUND)
        );
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new CustomException(POST_NOT_FOUND)
        );
        postRepository.delete(post);
    }
}
