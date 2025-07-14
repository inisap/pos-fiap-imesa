package br.com.fiap.imesa.domain.gateway;

import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;

import java.util.List;
import java.util.Optional;

public interface IConsultaRestauranteRepository {

    List<Restaurante> consultarTodos();
    List<Restaurante> consultarComFiltros(Restaurante restaurante);
    Optional<Restaurante> consultaPorId(Long restauranteId);
}
