package br.com.fiap.imesa.adapter.inbound.rest.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EnderecoDtoRequest {
    private String logradouro;
    private String numero;
    private String cidade;
    private String estado;
    private String cep;
    private String complemento;
    private String bairro;

}
