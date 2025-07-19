package br.com.fiap.imesa.domain.gateway;

import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;

import java.util.List;
import java.util.Optional;

public interface IRestauranteRepository {

    Restaurante salvar(Restaurante restaurante);
    Restaurante atualizar(Restaurante restaurante);
    List<Restaurante> consultarTodos();
    List<Restaurante> consultarComFiltros(Restaurante restaurante);
    Optional<Restaurante> consultaPorId(Long restauranteId);
}
