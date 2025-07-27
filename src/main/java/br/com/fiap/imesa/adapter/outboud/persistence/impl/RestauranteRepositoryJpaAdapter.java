package br.com.fiap.imesa.adapter.outboud.persistence.impl;

import br.com.fiap.imesa.adapter.outboud.persistence.mapper.RestauranteEntityMapper;
import br.com.fiap.imesa.adapter.outboud.persistence.springdata.RestauranteRepository;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.domain.gateway.IRestauranteRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RestauranteRepositoryJpaAdapter implements IRestauranteRepository {

    private final RestauranteRepository restauranteRepository;

    public RestauranteRepositoryJpaAdapter(RestauranteRepository restauranteRepository){
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

    @Override
    public Restaurante salvar(Restaurante restaurante) {

        var usuarioEntity = RestauranteEntityMapper.toEntity(restaurante);

        var retorno = restauranteRepository.save(usuarioEntity);

        return RestauranteEntityMapper.toDomain(retorno);

    }

    @Override
    public Restaurante atualizar(Restaurante restaurante) {

        var usuarioEntity = RestauranteEntityMapper.toEntity(restaurante);

        var retorno = restauranteRepository.save(usuarioEntity);

        return RestauranteEntityMapper.toDomain(retorno);

    }

    @Override
    public void deletar(Restaurante restaurante){
        var restauranteDeletarEntity = RestauranteEntityMapper.toEntity(restaurante);

        restauranteRepository.delete(restauranteDeletarEntity);
    }


}
