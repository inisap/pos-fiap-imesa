package br.com.fiap.imesa.adapter.outboud.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "itens_cardapios")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemCardapioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cardapio_id", nullable = false)
    private CardapioEntity cardapioEntity;

    @Column(nullable = false, length = 100, name = "nome_prato")
    private String nomePrato;

    @Column(nullable = false, length = 100, name = "descricao_prato")
    private String descricaoPrato;

    @Column(nullable = false, name = "preco")
    private Double preco;

    @Column(nullable = false, name = "disponivel_apenas_restaurante")
    private Boolean disponivelApenasRestaurante;

    @Column(nullable = false, name = "link_imagem_prato")
    private String linkImagePrato;
}
