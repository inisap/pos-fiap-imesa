package br.com.fiap.imesa.domain.gateway;

import br.com.fiap.imesa.domain.entities.Endereco;

import java.util.Optional;

public interface IConsultaEnderecoPorIdUsuarioRepository {
    Optional<Endereco> consultar(Long idUsuario);
}
