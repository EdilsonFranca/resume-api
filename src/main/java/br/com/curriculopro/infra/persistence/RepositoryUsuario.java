package br.com.curriculopro.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryUsuario extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}



