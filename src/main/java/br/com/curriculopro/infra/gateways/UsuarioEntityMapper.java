package br.com.curriculopro.infra.gateways;
import br.com.curriculopro.domain.entities.User;
import br.com.curriculopro.infra.persistence.UserEntity;

public class UsuarioEntityMapper {
        public UserEntity toEntity(User usuario) {
            return UserEntity.builder()
                    .id(usuario.getId())
                    .role(usuario.getRole())
                    .name(usuario.getName())
                    .email(usuario.getEmail())
                    .password(usuario.getPassword())
                    .provider(usuario.getProvider())
                    .build();
        }

        public User toDomain(UserEntity entity) {
            return new User(
                    entity.getId(),
                    entity.getName(),
                    entity.getRole(),
                    entity.getEmail(),
                    entity.getPassword(),
                    entity.getProvider()
            );
        }
}

