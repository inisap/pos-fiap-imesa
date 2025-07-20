package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.domain.gateway.IEnderecoRepository;
import br.com.fiap.imesa.domain.gateway.IUsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletarUsuarioUseCase {
    private final IUsuarioRepository usuarioRepository;
    private final IEnderecoRepository enderecoRepository;

    public DeletarUsuarioUseCase(IUsuarioRepository usuarioRepository,
                                 IEnderecoRepository enderecoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public void run(Long idUsuario){

        var usuarioDeletar = usuarioRepository.consultarPorIdUsuario(idUsuario)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(null, idUsuario));

        var enderecoDeletar = enderecoRepository.consultarPorIdDeUsuario(idUsuario);

        enderecoDeletar.ifPresent(enderecoRepository::deletar);

        usuarioRepository.deletar(usuarioDeletar);
    }
}
