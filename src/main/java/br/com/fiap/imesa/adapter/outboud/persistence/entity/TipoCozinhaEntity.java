package br.com.fiap.imesa.adapter.outboud.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipos_cozinhas")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoCozinhaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100, name = "descricao_tipo_cozinha")
    private String descricaoTipoCozinha;

    public Integer getId() {
        return id;
    }

    public String getDescricaoTipoCozinha() {
        return descricaoTipoCozinha;
    }
}
