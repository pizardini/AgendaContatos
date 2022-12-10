package model;

import enums.TipoContato;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.Telefone;

public class Contato {
    
    private String nome;
    private String sobrenome;
    private TipoContato tipoContato;

    private List<Endereco> enderecos;

    private List<Telefone> telefones;

    public Contato() {
        this.telefones = new ArrayList<>();
        this.enderecos = new ArrayList<>();
    }

    public Contato (String nome, String sobreNome) {
        this(nome, sobreNome, TipoContato.PESSOAL);
    }

    public Contato(String nome, Telefone telefone){
        this.nome = nome;
        this.telefones.add(telefone);
    }

    public Contato(String nome, String sobreNome, TipoContato tipoContato) {
        this.nome = nome;
        this.sobrenome = sobreNome;
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
        return sobrenome;
    }
    public String getNomeCompleto() {
        return nome +" "+ sobrenome;
    }
    public void setSobreNome(String sobreNome) {
        this.sobrenome = sobreNome;
    }
    public TipoContato getTipoContato() {
        return tipoContato;
    }
    public void setTipoContato(TipoContato tipoContato) {
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
    }


    public void addTelefone(Telefone telefone) {
        this.telefones.add(telefone);
    }

//    public String getDetalhado() {
//        String detalhado = nome + sobrenome + tipoContato +
//                "\n Telefones: \n" + telefones.getTelefoneDetalhado();
//                "\n Endereços: \n" + enderecos.getEnderecosDetalhado();
//
//    return detalhado;
//    }
    @Override
    public String toString() {
        return "Contato{" +
                "nome='" + nome + '\'' +
                ", sobreNome='" + sobrenome + '\'' +
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
        return Objects.equals(nome, contato.nome) && Objects.equals(sobrenome, contato.sobrenome) && tipoContato == contato.tipoContato && Objects.equals(enderecos, contato.enderecos) && Objects.equals(telefones, contato.telefones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, sobrenome, tipoContato, enderecos, telefones);
    }
}
