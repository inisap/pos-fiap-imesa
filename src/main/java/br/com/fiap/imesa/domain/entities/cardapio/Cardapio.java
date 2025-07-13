package br.com.fiap.imesa.domain.entities.cardapio;

import java.util.List;

public class Cardapio {

    private Long codigoCardapio;
    private List<ItemCardapio> itemCardapioList;

    public Cardapio(Long codigoCardapio, List<ItemCardapio> itemCardapioList) {
        this.codigoCardapio = codigoCardapio;
        this.itemCardapioList = itemCardapioList;
    }

    public Long getCodigoCardapio() {
        return codigoCardapio;
    }

    public void setCodigoCardapio(Long codigoCardapio) {
        this.codigoCardapio = codigoCardapio;
    }

    public List<ItemCardapio> getItemCardapioList() {
        return itemCardapioList;
    }

    public void setItemCardapioList(List<ItemCardapio> itemCardapioList) {
        this.itemCardapioList = itemCardapioList;
    }
}
