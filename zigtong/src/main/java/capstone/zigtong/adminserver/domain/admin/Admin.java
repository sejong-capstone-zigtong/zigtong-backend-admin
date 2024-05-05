package capstone.zigtong.adminserver.domain.admin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Admin {
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", columnDefinition = "char(36)")
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String businessNumber;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private Role role;


}
