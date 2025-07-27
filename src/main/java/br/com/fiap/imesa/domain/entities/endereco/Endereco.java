package br.com.fiap.imesa.domain.entities.endereco;

import lombok.Builder;

@Builder
public class Endereco {

    private Long id;
    private Long usuarioId;
    private String logradouro;
    private String numero;
    private String cidade;
    private String estado;
    private String cep;
    private String complemento;
    private String bairro;

    public void atualizarCom(Endereco novoEndereco) {
        // Sobrescreve TODOS os campos, mesmo se forem null
        this.cep = novoEndereco.getCep();
        this.logradouro = novoEndereco.getLogradouro();
        this.numero = novoEndereco.getNumero();
        this.complemento = novoEndereco.getComplemento();
        this.bairro = novoEndereco.getBairro();
        this.cidade = novoEndereco.getCidade();
        this.estado = novoEndereco.getEstado();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}
