package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.EnderecoNaoEncontradoParaUsuarioException;
import br.com.fiap.imesa.application.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.domain.entities.Endereco;
import br.com.fiap.imesa.domain.gateway.IEnderecoRepository;
import br.com.fiap.imesa.domain.gateway.IUsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsultaEnderecoUsuarioPorIdUsuarioUseCase {
    private final IUsuarioRepository usuarioRepository;
    private final IEnderecoRepository enderecoRepository;

    public ConsultaEnderecoUsuarioPorIdUsuarioUseCase(IUsuarioRepository usuarioRepository,
                                                      IEnderecoRepository enderecoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco run(Long id){

        usuarioRepository.consultarPorIdUsuario(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(null, id));

        return enderecoRepository.consultarPorIdDeUsuario( id)
                .orElseThrow(() -> new EnderecoNaoEncontradoParaUsuarioException(null, id));
    }
}
