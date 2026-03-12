package br.com.curriculopro.infra.persistence;

import br.com.curriculopro.domain.enums.State;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "experience")
public class ExperienceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameCompany;
    private LocalDate start;
    private LocalDate end;
    private String position;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    private String city;
    private String phone;

    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private ResumeEntity resume;
}
