package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.HorarioFuncionamentoException;
import br.com.fiap.imesa.application.exception.RestauranteNaoEncontradoException;
import br.com.fiap.imesa.application.mapper.SalvarHorarioFuncionamentoRestauranteCommandMapper;
import br.com.fiap.imesa.application.usecases.command.HorarioFuncionamentoCommand;
import br.com.fiap.imesa.domain.entities.horarioFuncionamento.HorarioFuncionamento;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.domain.gateway.IHorarioFuncionamentoRepository;
import br.com.fiap.imesa.domain.gateway.IRestauranteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SalvaHorarioRestauranteUseCase {

    private final IHorarioFuncionamentoRepository horarioFuncionamentoRepository;
    private final IRestauranteRepository restauranteRepository;
    public SalvaHorarioRestauranteUseCase(IHorarioFuncionamentoRepository horarioFuncionamentoRepository,
                                          IRestauranteRepository consultaRestauranteRepository) {
        this.horarioFuncionamentoRepository = horarioFuncionamentoRepository;
        this.restauranteRepository = consultaRestauranteRepository;
    }

    public List<HorarioFuncionamento> run(HorarioFuncionamentoCommand horarioFuncionamentoCommand) {

        var restaurante = restauranteRepository.consultaPorId(horarioFuncionamentoCommand.getRestauranteId())
                .orElseThrow(() -> new RestauranteNaoEncontradoException(null, horarioFuncionamentoCommand.getRestauranteId()));

        var horarioFuncionamentoAtualizaar =
                SalvarHorarioFuncionamentoRestauranteCommandMapper.commandToDomain(horarioFuncionamentoCommand);

        validaFuncionamento(horarioFuncionamentoAtualizaar);

        var horariosRestaurante = horarioFuncionamentoRepository.consultarPorIdDeRestaurante(horarioFuncionamentoCommand.getRestauranteId());

        aplicarRegras(horariosRestaurante, horarioFuncionamentoAtualizaar, restaurante);


        return horarioFuncionamentoRepository.salvar(horarioFuncionamentoAtualizaar);

    }

    private void validaFuncionamento(List<HorarioFuncionamento> horarioFuncionamento){

        Set<Integer> diasUnicos = horarioFuncionamento.stream()
                .map(HorarioFuncionamento::getDiaSemana)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        if(diasUnicos.size() != 7){
            throw new HorarioFuncionamentoException("Necessario informar os 7 dias da semana e sem repetir");
        }

        if(horarioFuncionamento.stream().anyMatch(item ->
                item.getHoraAbertura() == null
                        || item.getHoraFechamento() == null
                        || item.getHoraFechamento().isBefore(item.getHoraAbertura())
        )){
            throw new HorarioFuncionamentoException("Cada Dia deve ter hora inicial e final de abertura");
        }
    }

    private void aplicarRegras(List<HorarioFuncionamento> listHorariosExistentes, List<HorarioFuncionamento> listNovaAtualizar, Restaurante restaurante){


        for(HorarioFuncionamento base : listHorariosExistentes){
            for(HorarioFuncionamento novos : listNovaAtualizar){
                if(base.getDiaSemana().equals(novos.getDiaSemana())){
                    novos.setId(base.getId());
                }
            }
        }

        listNovaAtualizar.forEach(a -> a.setRestauranteId(restaurante));
    }

}
