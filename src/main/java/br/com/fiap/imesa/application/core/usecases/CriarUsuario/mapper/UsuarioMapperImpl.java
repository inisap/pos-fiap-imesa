package br.com.fiap.imesa.application.core.usecases.CriarUsuario.mapper;

import br.com.fiap.imesa.application.core.domain.EnderecoDomain;
import br.com.fiap.imesa.application.core.domain.TipoUsuarioDomain;
import br.com.fiap.imesa.application.core.domain.UsuarioDomain;
import br.com.fiap.imesa.application.core.usecases.CriarUsuario.dto.CriarUsuarioEnderecoResponse;
import br.com.fiap.imesa.application.core.usecases.CriarUsuario.dto.CriarUsuarioRequest;
import br.com.fiap.imesa.application.core.usecases.CriarUsuario.dto.CriarUsuarioResponse;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapperImpl implements UsuarioMapper{


    @Override
    public UsuarioDomain toDomain(CriarUsuarioRequest request){
        UsuarioDomain usuarioDomain = new UsuarioDomain();
        usuarioDomain.setNome(request.getNome());
        usuarioDomain.setEmail(request.getEmail());
        usuarioDomain.setLogin(request.getLogin());
        usuarioDomain.setPassword(request.getPassword());
        usuarioDomain.setTipoUsuario(TipoUsuarioDomain.valueOf(request.getTipoUsuario()));

        EnderecoDomain enderecoDomain = new EnderecoDomain();

        enderecoDomain.setLogradouro(request.getEndereco().getLogradouro());
        enderecoDomain.setNumero(request.getEndereco().getNumero());
        enderecoDomain.setComplemento(request.getEndereco().getComplemento());
        enderecoDomain.setCep(request.getEndereco().getCep());
        enderecoDomain.setBairro(request.getEndereco().getBairro());
        enderecoDomain.setCidade(request.getEndereco().getCidade());
        enderecoDomain.setEstado(request.getEndereco().getEstado());

        usuarioDomain.setEndereco(enderecoDomain);

        return usuarioDomain;
    }

    @Override
    public CriarUsuarioResponse toResponse(UsuarioDomain domain){
        CriarUsuarioResponse criarUsuarioResponse = new CriarUsuarioResponse();
        criarUsuarioResponse.setId(domain.getId());
        criarUsuarioResponse.setNome(domain.getNome());
        criarUsuarioResponse.setEmail(domain.getEmail());
        criarUsuarioResponse.setLogin(domain.getLogin());
        criarUsuarioResponse.setTipoUsuario(String.valueOf(domain.getTipoUsuario()));

        CriarUsuarioEnderecoResponse criarUsuarioEnderecoResponse = new CriarUsuarioEnderecoResponse();

        criarUsuarioEnderecoResponse.setCep(domain.getEndereco().getCep());
        criarUsuarioEnderecoResponse.setLogradouro(domain.getEndereco().getLogradouro());
        criarUsuarioEnderecoResponse.setNumero(domain.getEndereco().getNumero());
        criarUsuarioEnderecoResponse.setComplemento(domain.getEndereco().getComplemento());
        criarUsuarioEnderecoResponse.setBairro(domain.getEndereco().getBairro());
        criarUsuarioEnderecoResponse.setCidade(domain.getEndereco().getCidade());
        criarUsuarioEnderecoResponse.setEstado(domain.getEndereco().getEstado());

        criarUsuarioResponse.setEndereco(criarUsuarioEnderecoResponse);

        return criarUsuarioResponse;
    }
}
