package entidade;

public class Cliente extends Pessoa {
    private String cnh;
    private Integer numeroDeDependentes;

    public String getCnh() { return cnh; }
    public void setCnh(String cnh) { this.cnh = cnh; }

    public Integer getNumeroDeDependentes() { return numeroDeDependentes; }
    public void setNumeroDeDependentes(Integer numeroDeDependentes) { this.numeroDeDependentes = numeroDeDependentes; }
}
