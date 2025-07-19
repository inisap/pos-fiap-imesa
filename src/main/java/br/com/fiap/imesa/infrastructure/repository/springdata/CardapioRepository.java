package br.com.fiap.imesa.infrastructure.repository.springdata;


import br.com.fiap.imesa.infrastructure.repository.entity.CardapioEntity;
import br.com.fiap.imesa.infrastructure.repository.entity.HorarioFuncionamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardapioRepository extends JpaRepository<CardapioEntity, Long> {

    Optional<CardapioEntity> findByIdAndRestauranteEntity_Id(Long id, Long restauranteId);

}
