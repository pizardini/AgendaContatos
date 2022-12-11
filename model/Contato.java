package model;

import enums.TipoContato;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.Telefone;
import ui.AgendaUI;

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
        String nomeCompleto = nome +" "+ sobrenome ;
        nomeCompleto = nomeCompleto.trim();
        return nomeCompleto;
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

    public void addEndereco(Endereco endereco) {
        this.enderecos.add(endereco);
    }

    public String exibirContato() {
            String exibirNomes = "Nome Completo \n" + getNomeCompleto() +" "+ tipoContato ;
            String exibirTelefones="Telefones \n" + exibirTelefones();
            String exibirEnderecos="Endereços \n" + exibirEnderecos();
            String contatoDetalhado= exibirNomes +"\n" + exibirTelefones + "\n" + exibirEnderecos;
    return contatoDetalhado;
    }

    public String exibirTelefones() {
        String exibirTelefones="";
        for (int i = 0; i < telefones.size(); i++) {
            exibirTelefones += i+" - "+ telefones.get(i).getTelefoneDetalhado() +"\n";
        }
        return exibirTelefones;
   }
   
   public String listarTelefones() {
       String exibirTelefones="";
       for (int i = 0; i < telefones.size(); i++) {
           exibirTelefones += i+" - "+ telefones.get(i).getTelefoneSimples() +"\n";
       }
       return exibirTelefones;
  }

    public String exibirEnderecos(){
        String exibirEnderecos="";
        for (int i = 0; i < enderecos.size(); i++) {
            exibirEnderecos += i +" - "+ enderecos.get(i).getEnderecosDetalhado() +"\n";
        }
        return exibirEnderecos;
   }
   
   public String listarEnderecos(){
       String exibirEnderecos="";
       for (int i = 0; i < enderecos.size(); i++) {
           exibirEnderecos += i +" - "+ enderecos.get(i).getEnderecosSimples() +"\n";
       }
       return exibirEnderecos;
  }


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
