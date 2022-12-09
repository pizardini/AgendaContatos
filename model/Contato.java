package model;

import enums.TipoNome;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Contato {
    
    private String nome;
    private String sobreNome;
    private TipoNome tipoNome;

    private List<Endereco> enderecos;

    private List<Telefone> telefones;

    public Contato() {
    }

    public Contato (String nome, String sobreNome) {
        this(nome, sobreNome, TipoNome.PESSOAL);
    }

    public Contato(String nome, String sobreNome, TipoNome tipoNome) {
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.tipoNome = tipoNome;
        this.telefones = new ArrayList<>();
        this.enderecos = new ArrayList<>();
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSobreNome() {
        return sobreNome;
    }
    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }
    public TipoNome getTipoNome() {
        return tipoNome;
    }
    public void setTipoNome(TipoNome tipoNome) {
        this.tipoNome = tipoNome;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;

  /*      for (Telefone item : telefones) {

            if ((telefones.size() == 0) || (!item.equals(telefone))) {
                this.telefones.add(telefone);
            } else {
                System.out.println("Telefone já cadastrado.");
                break;
            }
        }*/
    }

    @Override
    public String toString() {
        return "Contato{" +
                "nome='" + nome + '\'' +
                ", sobreNome='" + sobreNome + '\'' +
                ", tipoNome=" + tipoNome +
                ", enderecos=" + enderecos +
                ", telefones=" + telefones +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contato contato = (Contato) o;
        return Objects.equals(nome, contato.nome) && Objects.equals(sobreNome, contato.sobreNome) && tipoNome == contato.tipoNome && Objects.equals(enderecos, contato.enderecos) && Objects.equals(telefones, contato.telefones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, sobreNome, tipoNome, enderecos, telefones);
    }
}
