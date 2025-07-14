package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.ConflitoChaveUnicaException;
import br.com.fiap.imesa.application.exception.DuplicacaoEmailJaCadastradoException;
import br.com.fiap.imesa.application.exception.DuplicacaoLoginJaCadastradoException;
import br.com.fiap.imesa.application.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.imesa.application.mapper.CriarUsuarioCommandMapper;
import br.com.fiap.imesa.application.usecases.command.CriarUsuarioCommand;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import br.com.fiap.imesa.domain.gateway.IConsultaTipoUsuarioPorIdRepository;
import br.com.fiap.imesa.domain.gateway.IConsultaUsuarioPorEmailRepository;
import br.com.fiap.imesa.domain.gateway.IConsultaUsuarioPorLoginRepository;
import br.com.fiap.imesa.domain.gateway.ICriaUsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CriarUsuarioUseCase {

    private final ICriaUsuarioRepository criaUsuarioRepository;
    private final IConsultaUsuarioPorEmailRepository consultaUsuarioPorEmailRepository;
    private final IConsultaUsuarioPorLoginRepository consultaUsuarioPorLoginRepository;
    private final IConsultaTipoUsuarioPorIdRepository consultaTipoUsuarioPorIdRepository;

    public CriarUsuarioUseCase(ICriaUsuarioRepository criaUsuarioRepository,
                               IConsultaUsuarioPorEmailRepository consultaUsuarioPorEmailRepository,
                               IConsultaUsuarioPorLoginRepository consultaUsuarioPorLoginRepository,
                               IConsultaTipoUsuarioPorIdRepository consultaTipoUsuarioPorIdRepository) {
        this.criaUsuarioRepository = criaUsuarioRepository;
        this.consultaUsuarioPorEmailRepository = consultaUsuarioPorEmailRepository;
        this.consultaUsuarioPorLoginRepository = consultaUsuarioPorLoginRepository;
        this.consultaTipoUsuarioPorIdRepository = consultaTipoUsuarioPorIdRepository;
    }

    public Usuario run(CriarUsuarioCommand command){

        if (consultaUsuarioPorEmailRepository.consultar(command.getEmail()).isPresent()) {
            throw new DuplicacaoEmailJaCadastradoException(null, command.getEmail());
        }

        if (consultaUsuarioPorLoginRepository.consultar(command.getLogin()).isPresent()) {
            throw new DuplicacaoLoginJaCadastradoException(null, command.getLogin());
        }

        var tipoUsuario = consultaTipoUsuarioPorIdRepository
                .consultar(command.getCodigoTipoUsuario())
                .orElseThrow(() -> new TipoUsuarioNaoEncontradoException(null, command.getCodigoTipoUsuario()));

        var usuario = CriarUsuarioCommandMapper.commandToDomain(command);

        usuario.setTipoUsuario(tipoUsuario);

        usuario.setId(null);
        usuario.setDataAlteracao(LocalDateTime.now());

        return criaUsuarioRepository.criar(usuario);
    }
}
