package br.com.fiap.imesa.infrastructure.repository.springdata;


import br.com.fiap.imesa.infrastructure.repository.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByLogin(String login);
    Optional<UsuarioEntity> findByEmail(String email);
}
