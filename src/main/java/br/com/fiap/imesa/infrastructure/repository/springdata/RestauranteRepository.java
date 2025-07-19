package br.com.fiap.imesa.infrastructure.repository.springdata;


import br.com.fiap.imesa.infrastructure.repository.entity.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<RestauranteEntity, Long> {

    @Query("""
        SELECT r
        FROM RestauranteEntity r
        WHERE (:nome IS NULL OR r.nome = :nome)
          AND (:tipoCozinhaId IS NULL OR r.tipoCozinha.id = :tipoCozinhaId)
    """)
    List<RestauranteEntity> buscarPorFiltros(
            @Param("nome") String nome,
            @Param("tipoCozinhaId") Integer tipoCozinhaId
    );
}
