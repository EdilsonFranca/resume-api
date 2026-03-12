package br.com.curriculopro.infra.persistence;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "education")
public class EducationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String institution;
    private String course;
    private LocalDate start;
    private LocalDate end;
    @ManyToOne
    @JoinColumn(name = "resume_id")
    private ResumeEntity resume;
}
