package br.com.fiap.imesa.adapter.outboud.persistence.springdata;


import br.com.fiap.imesa.adapter.outboud.persistence.entity.HorarioFuncionamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioFuncionamentoRepository extends JpaRepository<HorarioFuncionamentoEntity, Long> {

    List<HorarioFuncionamentoEntity> findByRestauranteId(Long restauranteId);
}
