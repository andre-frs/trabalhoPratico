package entidade;

public class Carro {
    private Integer id;
    private String placa;
    private String nome;
    private String marca;
    private String modelo;
    private Float valorDoSeguro;
    private Float valorDaLocacao;
    private String cor;
    private Boolean ativo;

    public void atualizarValorLocacao(float valorDaLocacao) {
        this.valorDaLocacao = valorDaLocacao;
    }
    public void atualizarValorDoSeguro(float valorDoSeguro) {
        this.valorDoSeguro = valorDoSeguro;
    }
    public void manter() {

    }
    public void selecionar() {

    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public Float getValorDoSeguro() { return valorDoSeguro; }
    public void setValorDoSeguro(Float valorDoSeguro) { this.valorDoSeguro = valorDoSeguro; }

    public Float getValorDaLocacao() { return valorDaLocacao; }
    public void setValorDaLocacao(Float valorDaLocacao) { this.valorDaLocacao = valorDaLocacao; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}
