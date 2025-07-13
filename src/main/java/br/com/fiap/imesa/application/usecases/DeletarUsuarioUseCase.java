package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.domain.gateway.IConsultaEnderecoPorIdUsuarioRepository;
import br.com.fiap.imesa.domain.gateway.IConsultaUsuarioPorIdRepository;
import br.com.fiap.imesa.domain.gateway.IDeletaEnderecoUsuarioRepository;
import br.com.fiap.imesa.domain.gateway.IDeletaUsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletarUsuarioUseCase {
    private final IConsultaUsuarioPorIdRepository consultaUsuarioPorIdRepository;

    private final IConsultaEnderecoPorIdUsuarioRepository consultaEnderecoPorIdUsuarioRepository;
    private final IDeletaUsuarioRepository deletaUsuarioRepository;
    private final IDeletaEnderecoUsuarioRepository deletaEnderecoUsuarioRepository;

    public DeletarUsuarioUseCase(IConsultaUsuarioPorIdRepository consultaUsuarioPorIdRepository,
                                 IConsultaEnderecoPorIdUsuarioRepository consultaEnderecoPorIdUsuarioRepository,
                                 IDeletaUsuarioRepository deletaUsuarioRepository,
                                 IDeletaEnderecoUsuarioRepository deletaEnderecoUsuarioRepository) {
        this.consultaUsuarioPorIdRepository = consultaUsuarioPorIdRepository;
        this.consultaEnderecoPorIdUsuarioRepository = consultaEnderecoPorIdUsuarioRepository;
        this.deletaUsuarioRepository = deletaUsuarioRepository;
        this.deletaEnderecoUsuarioRepository = deletaEnderecoUsuarioRepository;
    }

    public void run(Long idUsuario){

        var usuarioDeletar = consultaUsuarioPorIdRepository.consultar(idUsuario)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));

        var enderecoDeletar = consultaEnderecoPorIdUsuarioRepository.consultar(idUsuario);

        enderecoDeletar.ifPresent(deletaEnderecoUsuarioRepository::deletar);

        deletaUsuarioRepository.deletar(usuarioDeletar);
    }
}
