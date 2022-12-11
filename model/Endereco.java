package model;

import enums.TipoEndereco;

public class Endereco {
    private String cep;
    private String logradouro;
    private String numero;
    private String cidade;
    private String estado;
    private TipoEndereco tipoEndereco;

    public Endereco() {
    }

    public Endereco(String cep, String logradouro, String numero, String cidade, String estado, TipoEndereco tipoEndereco) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.tipoEndereco = tipoEndereco;
    }
    

    public Endereco(String cidade, String estado) {
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public TipoEndereco getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(TipoEndereco tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public String getEnderecosDetalhado() {
        return (logradouro +" " +
                numero +" " +
                cidade +" " +
                estado +" "+
                cep +" "+
                tipoEndereco);
    }
    
    public String getEnderecosSimples() {
        return (estado +" " + cidade +" ");
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", numero='" + numero + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", tipoEndereco=" + tipoEndereco +
                '}';
    }
}
