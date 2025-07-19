package br.com.fiap.imesa.domain.entities.cardapio;

import br.com.fiap.imesa.domain.entities.restaurante.Restaurante;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cardapio {

    private Long codigoCardapio;
    private String descricaoCardapio;
    private Restaurante idRestaurante;
}
