package br.com.fiap.imesa.infrastructure.repository;

import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.domain.gateway.IConsultaRestauranteRepository;
import br.com.fiap.imesa.infrastructure.repository.mapper.RestauranteEntityMapper;
import br.com.fiap.imesa.infrastructure.repository.springdata.RestauranteRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ConsultaRestauranteRepositoryImpl implements IConsultaRestauranteRepository {

    private final RestauranteRepository restauranteRepository;

    public ConsultaRestauranteRepositoryImpl(RestauranteRepository restauranteRepository){
        this.restauranteRepository = restauranteRepository;
    }

    @Override
    public List<Restaurante> consultarTodos() {

        var listRestaurantesEntity = restauranteRepository.findAll();

        return RestauranteEntityMapper.toListDomain(listRestaurantesEntity);
    }

    @Override
    public List<Restaurante> consultarComFiltros(Restaurante restaurante) {

        var listRestaurantes = restauranteRepository.buscarPorFiltros(restaurante.getNome(), restaurante.getTipoCozinha().getId());

        return RestauranteEntityMapper.toListDomain(listRestaurantes);

    }

    @Override
    public Optional<Restaurante> consultaPorId(Long restauranteId) {

        var restaurante = restauranteRepository.findById(restauranteId);

        return restaurante.map(RestauranteEntityMapper::toDomain);
    }


}
