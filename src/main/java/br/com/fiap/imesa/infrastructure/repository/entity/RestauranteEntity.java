package br.com.fiap.imesa.infrastructure.repository.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "restaurantes")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestauranteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @Column(nullable = false)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipos_cozinha_id", nullable = false)
    private TipoCozinhaEntity tipoCozinha;

}
