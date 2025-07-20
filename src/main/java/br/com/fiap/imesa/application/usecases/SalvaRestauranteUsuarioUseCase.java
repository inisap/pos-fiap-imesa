package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.TipoCozinhaNaoEncontradoException;
import br.com.fiap.imesa.application.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.application.exception.UsuarioNaoValidoParaRestauranteException;
import br.com.fiap.imesa.application.mapper.RestauranteCommandMapper;
import br.com.fiap.imesa.application.usecases.command.GravarRestauranteCommand;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.domain.gateway.IRestauranteRepository;
import br.com.fiap.imesa.domain.gateway.ITipoCozinhaRepository;
import br.com.fiap.imesa.domain.gateway.IUsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class SalvaRestauranteUsuarioUseCase {

    private final IUsuarioRepository usuarioRepository;
    private final ITipoCozinhaRepository tipoCozinhaRepository;
    private final IRestauranteRepository restauranteRepository;

    public SalvaRestauranteUsuarioUseCase(IUsuarioRepository usuarioRepository,
                                          ITipoCozinhaRepository tipoCozinhaRepository,
                                          IRestauranteRepository restauranteRepository) {
        this.usuarioRepository = usuarioRepository;
        this.tipoCozinhaRepository = tipoCozinhaRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public Restaurante run(GravarRestauranteCommand gravarRestauranteCommand) {

        //validar se usuario existe antes de cadastrar o restaurante
        var usuario = usuarioRepository.consultarPorIdUsuario(gravarRestauranteCommand.getUsuarioId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException(null, gravarRestauranteCommand.getUsuarioId()));

        //validar se usuario esta como dono de restaurante
        if(!usuario.getTipoUsuario().getId().equals(1)){
            throw new UsuarioNaoValidoParaRestauranteException("Usuario nao é do Tipo Dono de Restaurante");
        }

        //validar se cozinha selecionada existe
        var tipoCozinha = tipoCozinhaRepository.consultarPorIdTipoCozinha(gravarRestauranteCommand.getTipoCozinha())
                .orElseThrow(() -> new TipoCozinhaNaoEncontradoException(null, gravarRestauranteCommand.getTipoCozinha()));

        var restauranteDomain = RestauranteCommandMapper.commandToDomainCriar(gravarRestauranteCommand);

        restauranteDomain.setTipoCozinha(tipoCozinha);
        restauranteDomain.setUsuarioProprietario(usuario);

        return restauranteRepository.salvar(restauranteDomain);
    }

}
