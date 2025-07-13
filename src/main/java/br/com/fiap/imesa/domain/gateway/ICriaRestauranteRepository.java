package br.com.fiap.imesa.domain.gateway;

import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;

public interface ICriaRestauranteRepository {

    Restaurante criar(Restaurante restaurante);
}
