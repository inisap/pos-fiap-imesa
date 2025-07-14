package br.com.fiap.imesa.infrastructure.repository.springdata;


import br.com.fiap.imesa.infrastructure.repository.entity.HorarioFuncionamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioFuncionamentoRepository extends JpaRepository<HorarioFuncionamentoEntity, Long> {

    List<HorarioFuncionamentoEntity> findByRestauranteId(Long restauranteId);
}
