package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.EnderecoNaoEncontradoParaUsuarioException;
import br.com.fiap.imesa.application.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.domain.entities.Endereco;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IConsultaEnderecoPorIdUsuarioRepository;
import br.com.fiap.imesa.domain.gateway.IConsultaUsuarioPorIdRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsultaEnderecoUsuarioPorIdUsuarioUseCase {
    private final IConsultaUsuarioPorIdRepository consultaUsuarioPorIdRepository;
    private final IConsultaEnderecoPorIdUsuarioRepository consultaEnderecoPorIdUsuarioRepository;

    public ConsultaEnderecoUsuarioPorIdUsuarioUseCase(IConsultaUsuarioPorIdRepository consultaUsuarioPorIdRepository,
                                                      IConsultaEnderecoPorIdUsuarioRepository consultaEnderecoPorIdUsuarioRepository) {
        this.consultaUsuarioPorIdRepository = consultaUsuarioPorIdRepository;
        this.consultaEnderecoPorIdUsuarioRepository = consultaEnderecoPorIdUsuarioRepository;
    }

    public Endereco run(Long id){

        consultaUsuarioPorIdRepository.consultar(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(null, id));

        return consultaEnderecoPorIdUsuarioRepository.consultar(id)
                .orElseThrow(() -> new EnderecoNaoEncontradoParaUsuarioException(null, id));
    }
}
