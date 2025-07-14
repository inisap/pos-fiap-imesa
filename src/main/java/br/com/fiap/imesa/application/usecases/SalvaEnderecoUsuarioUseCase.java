package br.com.fiap.imesa.application.usecases;

import br.com.fiap.imesa.application.exception.UsuarioNaoEncontradoException;
import br.com.fiap.imesa.application.mapper.CriarAtualizarEnderecoUsuarioCommandMapper;
import br.com.fiap.imesa.application.usecases.command.GravarEnderecoCommand;
import br.com.fiap.imesa.domain.entities.Endereco;
import br.com.fiap.imesa.domain.gateway.IConsultaEnderecoPorIdUsuarioRepository;
import br.com.fiap.imesa.domain.gateway.IConsultaUsuarioPorIdRepository;
import br.com.fiap.imesa.domain.gateway.ISalvaEnderecoRepository;
import org.springframework.stereotype.Service;

@Service
public class SalvaEnderecoUsuarioUseCase {

    private final ISalvaEnderecoRepository salvaEnderecoRepository;
    private final IConsultaUsuarioPorIdRepository consultaUsuarioPorIdRepository;
    private final IConsultaEnderecoPorIdUsuarioRepository consultaEnderecoPorIdUsuarioRepository;

    public SalvaEnderecoUsuarioUseCase(ISalvaEnderecoRepository salvaEnderecoRepository,
                                       IConsultaUsuarioPorIdRepository consultaUsuarioPorIdRepository,
                                       IConsultaEnderecoPorIdUsuarioRepository consultaEnderecoPorIdUsuarioRepository) {
        this.salvaEnderecoRepository = salvaEnderecoRepository;
        this.consultaUsuarioPorIdRepository = consultaUsuarioPorIdRepository;
        this.consultaEnderecoPorIdUsuarioRepository = consultaEnderecoPorIdUsuarioRepository;
    }

    public Endereco run(GravarEnderecoCommand enderecoCommand) {

        consultaUsuarioPorIdRepository.consultar(enderecoCommand.getUsuarioId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException(null, enderecoCommand.getUsuarioId()));

        var end = CriarAtualizarEnderecoUsuarioCommandMapper.commandToDomain(enderecoCommand);

        var consultaEndOptional = consultaEnderecoPorIdUsuarioRepository.consultar(enderecoCommand.getUsuarioId());

        consultaEndOptional.ifPresent(endereco -> end.setId(endereco.getId()));

        return salvaEnderecoRepository.salvar(end);
    }
}
