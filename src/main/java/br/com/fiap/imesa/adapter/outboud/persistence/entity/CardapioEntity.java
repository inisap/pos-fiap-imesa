package br.com.fiap.imesa.adapter.outboud.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cardapios")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CardapioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, name = "descricao_cardapio")
    private String descricaoCardapio;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurante_id", nullable = false)
    private RestauranteEntity restauranteEntity;

}
