package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.ConflitoChaveUnicaException;
import br.com.fiap.imesa.application.mapper.CriarUsuarioCommandMapper;
import br.com.fiap.imesa.application.usecases.command.CriarUsuarioCommand;
import br.com.fiap.imesa.domain.entities.usuario.Usuario;
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

    public CriarUsuarioUseCase(ICriaUsuarioRepository criaUsuarioRepository,
                               IConsultaUsuarioPorEmailRepository consultaUsuarioPorEmailRepository,
                               IConsultaUsuarioPorLoginRepository consultaUsuarioPorLoginRepository) {
        this.criaUsuarioRepository = criaUsuarioRepository;
        this.consultaUsuarioPorEmailRepository = consultaUsuarioPorEmailRepository;
        this.consultaUsuarioPorLoginRepository = consultaUsuarioPorLoginRepository;
    }

    public Usuario run(CriarUsuarioCommand command){

        if (consultaUsuarioPorEmailRepository.consultar(command.getEmail()).isPresent()) {
            throw new ConflitoChaveUnicaException("Já existe um usuário com este e-mail.");
        }

        if (consultaUsuarioPorLoginRepository.consultar(command.getLogin()).isPresent()) {
            throw new ConflitoChaveUnicaException("Já existe um usuário com este login.");
        }

        var usuario = CriarUsuarioCommandMapper.commandToDomain(command);

        usuario.setId(null);
        usuario.setDataAlteracao(LocalDateTime.now());

        return criaUsuarioRepository.criar(usuario);
    }
}
