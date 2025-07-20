package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.application.mapper.CriarAtualizarEnderecoUsuarioCommandMapper;
import br.com.fiap.imesa.application.usecases.command.GravarEnderecoCommand;
import br.com.fiap.imesa.domain.entities.Endereco;
import br.com.fiap.imesa.domain.gateway.IEnderecoRepository;
import br.com.fiap.imesa.domain.gateway.IUsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class SalvaEnderecoUsuarioUseCase {

    private final IUsuarioRepository usuarioRepository;
    private final IEnderecoRepository enderecoRepository;

    public SalvaEnderecoUsuarioUseCase(IUsuarioRepository usuarioRepository,
                                       IEnderecoRepository enderecoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco run(GravarEnderecoCommand enderecoCommand) {

        usuarioRepository.consultarPorIdUsuario(enderecoCommand.getUsuarioId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException(null, enderecoCommand.getUsuarioId()));

        var end = CriarAtualizarEnderecoUsuarioCommandMapper.commandToDomain(enderecoCommand);

        var consultaEndOptional = enderecoRepository.consultarPorIdDeUsuario(enderecoCommand.getUsuarioId());

        consultaEndOptional.ifPresent(endereco -> end.setId(endereco.getId()));

        return enderecoRepository.salvar(end);
    }
}
