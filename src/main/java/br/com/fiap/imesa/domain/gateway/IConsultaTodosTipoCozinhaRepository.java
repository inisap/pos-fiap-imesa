package br.com.fiap.imesa.domain.gateway;

import br.com.fiap.imesa.domain.entities.cozinha.TipoCozinha;
import br.com.fiap.imesa.domain.entities.usuario.TipoUsuario;

import java.util.List;

public interface IConsultaTodosTipoCozinhaRepository {

    List<TipoCozinha> consultar();
}
