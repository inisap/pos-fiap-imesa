package br.com.fiap.imesa.application.core.usecases.AtualizarUsuario;

import br.com.fiap.imesa.application.core.domain.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.application.core.usecases.AtualizarUsuario.dto.AtualizarUsuarioRequest;
import br.com.fiap.imesa.application.core.usecases.AtualizarUsuario.dto.AtualizarUsuarioResponse;
import br.com.fiap.imesa.application.core.usecases.AtualizarUsuario.mapper.AtualizarUsuarioMapper;
import br.com.fiap.imesa.application.ports.in.AtualizarUsuarioPortIn;
import br.com.fiap.imesa.application.ports.out.AtualizarUsuarioOutport;
import br.com.fiap.imesa.application.ports.out.ConsultaUsuarioPorIdOutport;
import org.springframework.stereotype.Service;

@Service
public class AtualizarUsuarioUseCase implements AtualizarUsuarioPortIn {

    private final ConsultaUsuarioPorIdOutport consultaUsuarioPorIdOutport;
    private final AtualizarUsuarioOutport atualizarUsuarioOutport;
    private final AtualizarUsuarioMapper atualizarUsuarioMapper;

    public AtualizarUsuarioUseCase(ConsultaUsuarioPorIdOutport consultaUsuarioPorIdOutport,
                                   AtualizarUsuarioOutport atualizarUsuarioOutport,
                                   AtualizarUsuarioMapper atualizarUsuarioMapper){
        this.consultaUsuarioPorIdOutport = consultaUsuarioPorIdOutport;
        this.atualizarUsuarioOutport = atualizarUsuarioOutport;
        this.atualizarUsuarioMapper = atualizarUsuarioMapper;

    }

    @Override
    public AtualizarUsuarioResponse atualizar(Long id, AtualizarUsuarioRequest atualizarUsuarioRequest){

        var usuarioDomainOptional = consultaUsuarioPorIdOutport.consultarPorId(id);

       usuarioDomainOptional
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Id de usuario não encontrado"));

        var domainAlter = usuarioDomainOptional.get();

        //alteracao dados basicos
        if(atualizarUsuarioRequest.getNome() != null)
            domainAlter.setNome(atualizarUsuarioRequest.getNome());
        if(atualizarUsuarioRequest.getEmail() != null)
            domainAlter.setEmail(atualizarUsuarioRequest.getEmail());
        if(atualizarUsuarioRequest.getLogin() != null)
            domainAlter.setLogin(atualizarUsuarioRequest.getLogin());

        //alteracao endereco
        var enderecoAlter = domainAlter.getEndereco();

        if(atualizarUsuarioRequest.getEndereco().getCep() != null)
            enderecoAlter.setCep(atualizarUsuarioRequest.getEndereco().getCep());
        if(atualizarUsuarioRequest.getEndereco().getLogradouro() != null)
            enderecoAlter.setLogradouro(atualizarUsuarioRequest.getEndereco().getLogradouro());
        if(atualizarUsuarioRequest.getEndereco().getNumero() != null)
            enderecoAlter.setNumero(atualizarUsuarioRequest.getEndereco().getNumero());
        if(atualizarUsuarioRequest.getEndereco().getComplemento() != null)
            enderecoAlter.setComplemento(atualizarUsuarioRequest.getEndereco().getComplemento());
        if(atualizarUsuarioRequest.getEndereco().getBairro() != null)
            enderecoAlter.setBairro(atualizarUsuarioRequest.getEndereco().getBairro());
        if(atualizarUsuarioRequest.getEndereco().getCidade() != null)
            enderecoAlter.setCidade(atualizarUsuarioRequest.getEndereco().getCidade());
        if(atualizarUsuarioRequest.getEndereco().getEstado() != null)
            enderecoAlter.setEstado(atualizarUsuarioRequest.getEndereco().getEstado());

        domainAlter.setEndereco(enderecoAlter);

        var retornoDomain = atualizarUsuarioOutport.atualizar(domainAlter);

        return atualizarUsuarioMapper.toResponse(retornoDomain);
    }
}
