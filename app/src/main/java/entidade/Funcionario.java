package entidade;

import java.util.Date;

public class Funcionario extends Pessoa {
    private Date dataDeAdimissao;
    private Date dataDeDemissao;
    private Boolean supervisor;

    public void demitir(Date dataDeDemissao) {
        this.dataDeDemissao = dataDeDemissao;
    }

    public Date getDataDeAdimissao() { return dataDeAdimissao; }
    public void setDataDeAdimissao(Date dataDeAdimissao) { this.dataDeAdimissao = dataDeAdimissao; }

    public Date getDataDeDemissao() { return dataDeDemissao; }
    public void setDataDeDemissao(Date dataDeDemissao) { this.dataDeDemissao = dataDeDemissao; }

    public Boolean getSupervisor() { return supervisor; }
    public void setSupervisor(Boolean supervisor) { this.supervisor = supervisor; }
}
