package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.HorarioFuncionamentoException;
import br.com.fiap.imesa.application.exception.RestauranteNaoEncontradoException;
import br.com.fiap.imesa.application.mapper.SalvarHorarioFuncionamentoRestauranteCommandMapper;
import br.com.fiap.imesa.application.usecases.command.HorarioFuncionamentoCommand;
import br.com.fiap.imesa.domain.entities.horarioFuncionamento.HorarioFuncionamento;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.domain.gateway.IConsultaHorarioRestaurantePorIdRepository;
import br.com.fiap.imesa.domain.gateway.IConsultaRestauranteRepository;
import br.com.fiap.imesa.domain.gateway.ISalvaHorarioFuncionamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SalvaHorarioRestauranteUseCase {

    private final ISalvaHorarioFuncionamentoRepository salvaHorarioFuncionamentoRepository;
    private final IConsultaRestauranteRepository consultaRestauranteRepository;
    private final IConsultaHorarioRestaurantePorIdRepository consultaHorarioRestaurantePorIdRepository;

    public SalvaHorarioRestauranteUseCase(ISalvaHorarioFuncionamentoRepository salvaHorarioFuncionamentoRepository,
                                          IConsultaRestauranteRepository consultaRestauranteRepository,
                                          IConsultaHorarioRestaurantePorIdRepository consultaHorarioRestaurantePorIdRepository) {
        this.salvaHorarioFuncionamentoRepository = salvaHorarioFuncionamentoRepository;
        this.consultaRestauranteRepository = consultaRestauranteRepository;
        this.consultaHorarioRestaurantePorIdRepository = consultaHorarioRestaurantePorIdRepository;
    }

    public List<HorarioFuncionamento> run(HorarioFuncionamentoCommand horarioFuncionamentoCommand) {

        var restaurante = consultaRestauranteRepository.consultaPorId(horarioFuncionamentoCommand.getRestauranteId())
                .orElseThrow(() -> new RestauranteNaoEncontradoException(null, horarioFuncionamentoCommand.getRestauranteId()));

        var horarioFuncionamentoAtualizaar =
                SalvarHorarioFuncionamentoRestauranteCommandMapper.commandToDomain(horarioFuncionamentoCommand);

        validaFuncionamento(horarioFuncionamentoAtualizaar);

        var horariosRestaurante = consultaHorarioRestaurantePorIdRepository.consultar(horarioFuncionamentoCommand.getRestauranteId());

        aplicarRegras(horariosRestaurante, horarioFuncionamentoAtualizaar, restaurante);



        return salvaHorarioFuncionamentoRepository.salvar(horarioFuncionamentoAtualizaar);

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




//
//
//
//
//        // Indexa os existentes por diaSemana
//        Map<Integer, HorarioFuncionamento> mapExistentesPorDia = listHorariosExistentes.stream()
//                .collect(Collectors.toMap(HorarioFuncionamento::getDiaSemana, Function.identity()));
//
//        // Percorre a lista nova
//        for (HorarioFuncionamento novo : listNovaAtualizar) {
//            HorarioFuncionamento existente = mapExistentesPorDia.get(novo.getDiaSemana());
//
//            // Se houver um existente com o mesmo dia, pega o ID
//            if (existente != null) {
//                novo.setId(existente.getId());
//            }
//
//            // Sempre seta o restaurante
//            novo.setRestauranteId(restaurante);
//        }
    }

}
