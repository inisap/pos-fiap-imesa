package br.com.fiap.imesa.application.core.usecases.AtualizarUsuario.mapper;

import br.com.fiap.imesa.application.core.domain.UsuarioDomain;
import br.com.fiap.imesa.application.core.usecases.AtualizarUsuario.dto.AtualizarUsuarioEnderecoResponse;
import br.com.fiap.imesa.application.core.usecases.AtualizarUsuario.dto.AtualizarUsuarioResponse;
import org.springframework.stereotype.Component;

@Component
public class AtualizarUsuarioMapperImpl implements AtualizarUsuarioMapper {

    @Override
    public AtualizarUsuarioResponse toResponse(UsuarioDomain domain){
        AtualizarUsuarioResponse atualizarUsuarioResponse = new AtualizarUsuarioResponse();
        atualizarUsuarioResponse.setNome(domain.getNome());
        atualizarUsuarioResponse.setEmail(domain.getEmail());
        atualizarUsuarioResponse.setLogin(domain.getLogin());

        AtualizarUsuarioEnderecoResponse atualizarUsuarioEnderecoResponse = new AtualizarUsuarioEnderecoResponse();

        atualizarUsuarioEnderecoResponse.setCep(domain.getEndereco().getCep());
        atualizarUsuarioEnderecoResponse.setLogradouro(domain.getEndereco().getLogradouro());
        atualizarUsuarioEnderecoResponse.setNumero(domain.getEndereco().getNumero());
        atualizarUsuarioEnderecoResponse.setComplemento(domain.getEndereco().getComplemento());
        atualizarUsuarioEnderecoResponse.setBairro(domain.getEndereco().getBairro());
        atualizarUsuarioEnderecoResponse.setCidade(domain.getEndereco().getCidade());
        atualizarUsuarioEnderecoResponse.setEstado(domain.getEndereco().getEstado());

        atualizarUsuarioResponse.setEndereco(atualizarUsuarioEnderecoResponse);

        return atualizarUsuarioResponse;
    }
}
