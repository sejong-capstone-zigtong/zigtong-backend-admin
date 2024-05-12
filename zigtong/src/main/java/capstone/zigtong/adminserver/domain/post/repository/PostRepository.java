package capstone.zigtong.adminserver.domain.post.repository;


import capstone.zigtong.adminserver.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {
}
