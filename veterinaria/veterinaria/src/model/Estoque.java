package model;

public class Estoque {

    private Produto produto;
    private int quantidade;
    private int quantidadeMinima;
    private boolean necessitaReposicao;

    public Estoque(Produto produto, int quantidade, int quantidadeMinima) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.quantidadeMinima = quantidadeMinima;
        this.necessitaReposicao = verificar();
    }
    
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        this.necessitaReposicao = verificar();
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(int quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
        this.necessitaReposicao = verificar();
    }

    public boolean isNecessitaReposicao() {
        return necessitaReposicao;
    }

    public boolean verificar() {
        if (this.quantidade < this.quantidadeMinima) {
            System.out.println("Reposição necessária para o produto: " + this.produto.getNome());
            return true;
        }
        System.out.println("Quantidade suficiente em estoque para o produto: " + this.produto.getNome());
        return false;
    }

    @Override
    public String toString() {
        return "Estoque{" +
                "produto=" + produto.getNome() +
                ", quantidade=" + quantidade +
                ", quantidadeMinima=" + quantidadeMinima +
                ", necessitaReposicao=" + (necessitaReposicao ? "Sim" : "Não") +
                '}';
    }
}
