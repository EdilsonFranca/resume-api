package br.com.curriculopro.infra.persistence;

import br.com.curriculopro.domain.enums.PROVIDER;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "tb_user",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_user_email", columnNames = "email")
        }
)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;

    @Enumerated(EnumType.STRING)
    private PROVIDER provider; // LOCAL ou GOOGLE

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ResumeEntity> resumes = new HashSet<>();

    public void addResume(ResumeEntity resume) {
        resumes.add(resume);
        resume.setUser(this);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public PROVIDER getProvider() {
        return provider;
    }

    public Set<ResumeEntity> getResumes() {
        return resumes;
    }
}