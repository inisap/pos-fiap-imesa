package br.com.fiap.imesa.domain.entities.restaurante;

import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Restaurante {

    private Long id;
    private String nome;
    private TipoCozinha tipoCozinha;
    private List<HorarioFuncionamento> horarioFuncionamento;
    private Usuario usuarioProprietario;

}
