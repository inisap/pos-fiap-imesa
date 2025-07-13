package br.com.fiap.imesa.infrastructure.repository.mapper;

import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.entities.restaurante.HorarioFuncionamento;
import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.infrastructure.repository.entity.HorarioFuncionamentoEntity;
import br.com.fiap.imesa.infrastructure.repository.entity.RestauranteEntity;
import br.com.fiap.imesa.infrastructure.repository.entity.TipoCozinhaEntity;
import br.com.fiap.imesa.infrastructure.repository.entity.UsuarioEntity;

import java.util.ArrayList;
import java.util.List;

public class RestauranteEntityMapper {

    public static Restaurante toDomain(RestauranteEntity entity) {

        var tipo = TipoCozinha.builder()
                .id(entity.getTipoCozinha().getId())
                .nome(entity.getTipoCozinha().getDescricaoTipoCozinha())
                .build();

        List<HorarioFuncionamento> horaFuncList = new ArrayList<>();

        for(HorarioFuncionamentoEntity h : entity.getHorariosFuncionamento()){
            var horaFunc = HorarioFuncionamento.builder()
                    .diaSemana(h.getDiaSemana())
                    .horaAbertura(h.getHoraAbertura())
                    .horaFechamento(h.getHoraFechamento())
                    .flagDiaAberto(h.getFlagAberto())
                    .build();
            horaFuncList.add(horaFunc);
        }

        var usuario = Usuario.builder()
                .id(entity.getUsuario().getId())
                .build();

        return Restaurante.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .tipoCozinha(tipo)
                .horarioFuncionamento(horaFuncList)
                .usuarioProprietario(usuario)
                .build();
    }

    public static RestauranteEntity toEntity(Restaurante restaurante) {

        var tipoCozinha =
        TipoCozinhaEntity.builder()
                .id(restaurante.getTipoCozinha().getId())
                .descricaoTipoCozinha(restaurante.getTipoCozinha().getNome())
                .build();

        return RestauranteEntity.builder()
                .id(restaurante.getId())
                .nome(restaurante.getNome())
                .tipoCozinha(tipoCozinha)
                .build();
    }
}
