package br.com.fiap.imesa.infrastructure.repository.springdata;


import br.com.fiap.imesa.infrastructure.repository.entity.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<RestauranteEntity, Long> {

}
