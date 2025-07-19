package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.TipoCozinhaNaoEncontradoException;
import br.com.fiap.imesa.application.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.application.exception.UsuarioNaoValidoParaRestauranteException;
import br.com.fiap.imesa.application.mapper.RestauranteCommandMapper;
import br.com.fiap.imesa.application.usecases.command.GravarRestauranteCommand;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.domain.gateway.IConsultaTipoCozinhaPorIdRepository;
import br.com.fiap.imesa.domain.gateway.IConsultaUsuarioPorIdRepository;
import br.com.fiap.imesa.domain.gateway.IRestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class SalvaRestauranteUsuarioUseCase {

    private final IConsultaUsuarioPorIdRepository consultaUsuarioPorIdRepository;
    private final IConsultaTipoCozinhaPorIdRepository consultaTipoCozinhaPorIdRepository;
    private final IRestauranteRepository restauranteRepository;

    public SalvaRestauranteUsuarioUseCase(
                                          IConsultaUsuarioPorIdRepository consultaUsuarioPorIdRepository,
                                          IConsultaTipoCozinhaPorIdRepository consultaTipoCozinhaPorIdRepository,
                                          IRestauranteRepository restauranteRepository) {
        this.consultaUsuarioPorIdRepository = consultaUsuarioPorIdRepository;
        this.consultaTipoCozinhaPorIdRepository = consultaTipoCozinhaPorIdRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public Restaurante run(GravarRestauranteCommand gravarRestauranteCommand) {

        //validar se usuario existe antes de cadastrar o restaurante
        var usuario = consultaUsuarioPorIdRepository.consultar(gravarRestauranteCommand.getUsuarioId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException(null, gravarRestauranteCommand.getUsuarioId()));

        //validar se usuario esta como dono de restaurante
        if(!usuario.getTipoUsuario().getId().equals(1)){
            throw new UsuarioNaoValidoParaRestauranteException("Usuario nao é do Tipo Dono de Restaurante");
        }

        //validar se cozinha selecionada existe
        var tipoCozinha = consultaTipoCozinhaPorIdRepository.consultar(gravarRestauranteCommand.getTipoCozinha())
                .orElseThrow(() -> new TipoCozinhaNaoEncontradoException(null, gravarRestauranteCommand.getTipoCozinha()));

        var restauranteDomain = RestauranteCommandMapper.commandToDomainCriar(gravarRestauranteCommand);

        restauranteDomain.setTipoCozinha(tipoCozinha);
        restauranteDomain.setUsuarioProprietario(usuario);

        return restauranteRepository.salvar(restauranteDomain);
    }

}
