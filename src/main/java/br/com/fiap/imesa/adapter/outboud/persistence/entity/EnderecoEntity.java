package br.com.fiap.imesa.adapter.outboud.persistence.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "enderecos")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuario;

    @Column(nullable = false, length = 100)
    private String logradouro;

    @Column(nullable = false, length = 10)
    private String numero;

    @Column(nullable = false, length = 50)
    private String cidade;

    @Column(nullable = false, length = 50)
    private String estado;

    @Column(name = "cep", nullable = false, length = 20)
    private String cep;

    @Column(length = 100)
    private String complemento;

    @Column(length = 40)
    private String bairro;
}
