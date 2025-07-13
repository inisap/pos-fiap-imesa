package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.HorarioFuncionamentoException;
import br.com.fiap.imesa.application.exception.TipoCozinhaNaoEncontradoException;
import br.com.fiap.imesa.application.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.application.exception.UsuarioNaoValidoParaRestauranteException;
import br.com.fiap.imesa.application.mapper.CriarRestauranteCommandMapper;
import br.com.fiap.imesa.application.usecases.command.GravarRestauranteCommand;
import br.com.fiap.imesa.application.usecases.command.HorarioFuncionamentoCommand;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.domain.gateway.IConsultaTipoCozinhaPorIdRepository;
import br.com.fiap.imesa.domain.gateway.IConsultaUsuarioPorIdRepository;
import br.com.fiap.imesa.domain.gateway.ICriaRestauranteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SalvaRestauranteUsuarioUseCase {

    private final IConsultaUsuarioPorIdRepository consultaUsuarioPorIdRepository;
    private final IConsultaTipoCozinhaPorIdRepository consultaTipoCozinhaPorIdRepository;
    private final ICriaRestauranteRepository criaRestauranteRepository;

    public SalvaRestauranteUsuarioUseCase(
                                          IConsultaUsuarioPorIdRepository consultaUsuarioPorIdRepository,
                                          IConsultaTipoCozinhaPorIdRepository consultaTipoCozinhaPorIdRepository,
                                          ICriaRestauranteRepository criaRestauranteRepository) {
        this.consultaUsuarioPorIdRepository = consultaUsuarioPorIdRepository;
        this.consultaTipoCozinhaPorIdRepository = consultaTipoCozinhaPorIdRepository;
        this.criaRestauranteRepository = criaRestauranteRepository;
    }

    public Restaurante run(GravarRestauranteCommand gravarRestauranteCommand) {

        //regras de negocio
        //validar se usuario existe antes de cadastrar o restaurante
        //validar se usuario esta como dono de restaurante
        //validar se cozinha selecionada existe
        //validar horarios de funcionamento e dias

        var usuario = consultaUsuarioPorIdRepository.consultar(gravarRestauranteCommand.getUsuarioId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario não existe"));

        if(!usuario.getTipoUsuario().getId().equals(1)){
            throw new UsuarioNaoValidoParaRestauranteException("Usuario nao é do Tipo Dono de Restaurante");
        }

        var tipoCozinha = consultaTipoCozinhaPorIdRepository.consultar(gravarRestauranteCommand.getTipoCozinha())
                .orElseThrow(() -> new TipoCozinhaNaoEncontradoException("Tipo de Cozinha Inexistente"));

        validaFuncionamento(gravarRestauranteCommand.getHorarioFuncionamento());

        var restauranteDomain = CriarRestauranteCommandMapper.commandToDomain(gravarRestauranteCommand);

        restauranteDomain.setTipoCozinha(tipoCozinha);
        restauranteDomain.setUsuarioProprietario(usuario);

        return criaRestauranteRepository.criar(restauranteDomain);
    }

    private void validaFuncionamento(List<HorarioFuncionamentoCommand> horarioFuncionamento){

        Set<Integer> diasUnicos = horarioFuncionamento.stream()
                .map(HorarioFuncionamentoCommand::getDiaSemana)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        if(diasUnicos.size() != 7){
            throw new HorarioFuncionamentoException("Necessario informar os 7 dias da semana e sem repetir");
        }

        if(horarioFuncionamento.stream().anyMatch(item ->
                item.getHoraAbertura() == null
                        || item.getHoraFechamento() == null
                        || !item.getHoraFechamento().isBefore(item.getHoraAbertura())
        )){
            throw new HorarioFuncionamentoException("Cada Dia deve ter hora inicial e final de abertura");
        }
    }
}
