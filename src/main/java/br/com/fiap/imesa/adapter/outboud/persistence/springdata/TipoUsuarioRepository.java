package br.com.fiap.imesa.adapter.outboud.persistence.springdata;


import br.com.fiap.imesa.adapter.outboud.persistence.entity.TipoUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuarioEntity, Integer> {

    Optional<TipoUsuarioEntity> findByDescricaoTipoUsuario(String descricaoTipoUsuario);

}
