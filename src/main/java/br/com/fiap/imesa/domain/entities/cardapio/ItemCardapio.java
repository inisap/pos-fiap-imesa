package br.com.fiap.imesa.domain.entities.cardapio;

public class ItemCardapio {

    private String nome;
    private String descricao;
    private Double preco;
    private boolean diponivelApenasLocalmente;
    private String linkImagemPrato;
    private Long codigoCardapio;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public boolean isDiponivelApenasLocalmente() {
        return diponivelApenasLocalmente;
    }

    public void setDiponivelApenasLocalmente(boolean diponivelApenasLocalmente) {
        this.diponivelApenasLocalmente = diponivelApenasLocalmente;
    }

    public String getLinkImagemPrato() {
        return linkImagemPrato;
    }

    public void setLinkImagemPrato(String linkImagemPrato) {
        this.linkImagemPrato = linkImagemPrato;
    }

    public Long getCodigoCardapio() {
        return codigoCardapio;
    }

    public void setCodigoCardapio(Long codigoCardapio) {
        this.codigoCardapio = codigoCardapio;
    }
}
