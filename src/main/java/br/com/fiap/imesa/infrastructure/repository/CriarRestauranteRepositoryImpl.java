package br.com.fiap.imesa.infrastructure.repository;

import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.ICriaRestauranteRepository;
import br.com.fiap.imesa.domain.gateway.ICriaUsuarioRepository;
import br.com.fiap.imesa.infrastructure.repository.mapper.RestauranteEntityMapper;
import br.com.fiap.imesa.infrastructure.repository.mapper.UsuarioEntityMapper;
import br.com.fiap.imesa.infrastructure.repository.springdata.RestauranteRepository;
import br.com.fiap.imesa.infrastructure.repository.springdata.UsuarioRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CriarRestauranteRepositoryImpl implements ICriaRestauranteRepository {

    private final RestauranteRepository restauranteRepository;

    public CriarRestauranteRepositoryImpl(RestauranteRepository restauranteRepository){
        this.restauranteRepository = restauranteRepository;
    }

    @Override
    public Restaurante criar(Restaurante restaurante) {

        var usuarioEntity = RestauranteEntityMapper.toEntity(restaurante);

        var retorno = restauranteRepository.save(usuarioEntity);

        return RestauranteEntityMapper.toDomain(retorno);

    }
}
