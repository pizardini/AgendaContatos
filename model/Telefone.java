package model;

import enums.TipoTelefone;

import java.util.Objects;

public class Telefone {
    private String ddd;
    private String numero;
    private TipoTelefone tipoTelefone;

    public Telefone() {
    }

    public Telefone (String numero) {
        this ("", numero, TipoTelefone.CELULAR);
    }

    public Telefone(String ddd, String numero, TipoTelefone tipoTelefone) {
        this.ddd = ddd;
        this.numero = numero;
        this.tipoTelefone = tipoTelefone;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoTelefone getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(TipoTelefone tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    @Override
    public String toString() {
        return "Telefone{" +
                "ddd='" + ddd + '\'' +
                ", numero='" + numero + '\'' +
                ", tipoTelefone=" + tipoTelefone +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefone telefone = (Telefone) o;
        return Objects.equals(ddd, telefone.ddd) && Objects.equals(numero, telefone.numero) && tipoTelefone == telefone.tipoTelefone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ddd, numero, tipoTelefone);
    }
}
