package model;

import enums.TipoNome;

import java.util.List;

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

    public void setTelefones(Telefone telefone) {
        for (Telefone item : telefones) {
            if (!item.equals(telefone)) {
                this.telefones.add(telefone);
            } else {
                System.out.println("Telefone já cadastrado.");
                break;
            }
        }
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
}
