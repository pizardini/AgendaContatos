package model;

import enums.TipoContato;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Contato {
    
    private String nome;
    private String sobreNome;
    private TipoContato tipoContato;

    private List<Endereco> enderecos;

    private List<Telefone> telefones;

    public Contato() {
    }

    public Contato (String nome, String sobreNome) {
        this(nome, sobreNome, TipoContato.PESSOAL);
    }

    public Contato(String nome, String sobreNome, TipoContato tipoContato) {
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.tipoContato = tipoContato;
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
    public TipoContato getTipoNome() {
        return tipoContato;
    }
    public void setTipoNome(TipoContato tipoContato) {
        this.tipoContato = tipoContato;
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
                ", tipoNome=" + tipoContato +
                ", enderecos=" + enderecos +
                ", telefones=" + telefones +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contato contato = (Contato) o;
        return Objects.equals(nome, contato.nome) && Objects.equals(sobreNome, contato.sobreNome) && tipoContato == contato.tipoContato && Objects.equals(enderecos, contato.enderecos) && Objects.equals(telefones, contato.telefones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, sobreNome, tipoContato, enderecos, telefones);
    }
}
