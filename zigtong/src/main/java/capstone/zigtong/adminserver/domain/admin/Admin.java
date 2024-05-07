package capstone.zigtong.adminserver.domain.admin;

import capstone.zigtong.adminserver.domain.post.Post;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", columnDefinition = "char(36)")
    private String id;
    @Column(nullable = false)
    private String accountId;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String businessNumber;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private Role role;
    @OneToMany(mappedBy = "admin")
    private List<Post> postList=new ArrayList<>();

    public Admin(String accountId, String password, String name,
                 String businessNumber, String category, Role role) {
        this.accountId = accountId;
        this.password = password;
        this.name = name;
        this.businessNumber = businessNumber;
        this.category = category;
        this.role = role;
    }
}
