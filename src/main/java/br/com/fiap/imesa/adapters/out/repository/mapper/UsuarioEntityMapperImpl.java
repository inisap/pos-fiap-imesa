package br.com.fiap.imesa.adapters.out.repository.mapper;

import br.com.fiap.imesa.adapters.out.repository.entity.EnderecoEntity;
import br.com.fiap.imesa.adapters.out.repository.entity.UsuarioEntity;
import br.com.fiap.imesa.application.core.domain.EnderecoDomain;
import br.com.fiap.imesa.application.core.domain.UsuarioDomain;
import org.springframework.stereotype.Component;

@Component
public class UsuarioEntityMapperImpl implements UsuarioEntityMapper {

    @Override
    public UsuarioDomain toUsuarioDomain(UsuarioEntity entity){
        UsuarioDomain usuarioDomain = new UsuarioDomain();

        usuarioDomain.setId(entity.getId());
        usuarioDomain.setNome(entity.getNome());
        usuarioDomain.setEmail(entity.getEmail());
        usuarioDomain.setLogin(entity.getLogin());
        usuarioDomain.setPassword(entity.getSenhaHash());
        usuarioDomain.setTipoUsuario(entity.getTipoUsuario());
        usuarioDomain.setDataAlteracao(entity.getDataAlteracao());

        EnderecoDomain enderecoDomain = new EnderecoDomain();

        enderecoDomain.setId(entity.getEndereco().getId());
        enderecoDomain.setLogradouro(entity.getEndereco().getLogradouro());
        enderecoDomain.setNumero(entity.getEndereco().getNumero());
        enderecoDomain.setCidade(entity.getEndereco().getCidade());
        enderecoDomain.setEstado(entity.getEndereco().getEstado());
        enderecoDomain.setCep(entity.getEndereco().getCep());
        enderecoDomain.setComplemento(entity.getEndereco().getComplemento());
        enderecoDomain.setBairro(entity.getEndereco().getBairro());


        usuarioDomain.setEndereco(enderecoDomain);

        return usuarioDomain;
    }

    @Override
    public UsuarioEntity toUsuarioEntity(UsuarioDomain domain){

        UsuarioEntity usuarioEntity =  new UsuarioEntity();

        EnderecoEntity enderecoEntity = new EnderecoEntity();

        usuarioEntity.setId(domain.getId());
        usuarioEntity.setNome(domain.getNome());
        usuarioEntity.setEmail(domain.getEmail());
        usuarioEntity.setLogin(domain.getLogin());
        usuarioEntity.setSenhaHash(domain.getPassword());
        usuarioEntity.setTipoUsuario(domain.getTipoUsuario());

        enderecoEntity.setId(domain.getEndereco().getId() == 0 ? null : domain.getEndereco().getId());
        enderecoEntity.setLogradouro(domain.getEndereco().getLogradouro());
        enderecoEntity.setNumero(domain.getEndereco().getNumero());
        enderecoEntity.setCidade(domain.getEndereco().getCidade());
        enderecoEntity.setEstado(domain.getEndereco().getEstado());
        enderecoEntity.setCep(domain.getEndereco().getCep());
        enderecoEntity.setComplemento(domain.getEndereco().getComplemento());
        enderecoEntity.setBairro(domain.getEndereco().getBairro());

        usuarioEntity.setEndereco(enderecoEntity);

        return usuarioEntity;
    }
}
