package br.com.fiap.imesa.adapter.outboud.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipos_cozinhas")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TipoCozinhaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100, name = "descricao_tipo_cozinha")
    private String descricaoTipoCozinha;
}
