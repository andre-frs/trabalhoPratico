package entidade;

import java.util.Date;

public class Locacao {
    private Integer id;
    private Date dataDeLocacao;
    private Date dataDeDevolucao;
    private Float quilometragem;

    private Integer idCliente;
    private Integer idCarro;

    public void encerrar(Date dataDeDevolucao, float quilometragem) {
        this.dataDeDevolucao = dataDeDevolucao;
        this.quilometragem = quilometragem;
    }
    public void manter() {

    }
    public void selecionar() {

    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Date getDataDeLocacao() { return dataDeLocacao; }
    public void setDataDeLocacao(Date dataDeLocacao) { this.dataDeLocacao = dataDeLocacao; }

    public Date getDataDeDevolucao() { return dataDeDevolucao; }
    public void setDataDeDevolucao(Date dataDeDevolucao) { this.dataDeDevolucao = dataDeDevolucao; }

    public Float getQuilometragem() { return quilometragem; }
    public void setQuilometragem(Float quilometragem) { this.quilometragem = quilometragem; }

    public Integer getIdCliente() { return idCliente; }
    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; }

    public Integer getIdCarro() { return idCarro; }
    public void setIdCarro(Integer idLocacao) { this.idCarro = idCarro; }
}
