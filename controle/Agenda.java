package controle;

import model.Contato;

import java.util.ArrayList;
import java.util.List;

public class Agenda {

    private List<Contato> contatos;

    public Agenda() {
        this.contatos = new ArrayList<>();
    }

    public void adicionar(Contato contato) {
        contatos.add(contato);

    }

    public void editar (Contato contato) {
        contatos.remove(contato);
        contatos.add(contato);

    }

    public List<Contato> pesquisarNome (String nome) {
        List<Contato> contatosFiltrados = new ArrayList<>();
        for (Contato contato : contatos) {
            if (contato.getNome().contains(nome)) {
                contatosFiltrados.add(contato);
            }
        }
        return contatosFiltrados;
    }
    public List<Contato> pesquisarEmail (String email) {
        List<Contato> emailsFiltrados = new ArrayList<>();
        for (Contato contato : contatos) {
            if (contato.getNome().contains(email)) {
                emailsFiltrados.add(contato);
            }
        }
        return emailsFiltrados;
    }

    public List<Contato> listar (int start, int quantidade) {
        List<Contato> contatosEncontrados = new ArrayList<>();
        if (start < 0 || start >= contatos.size()) {
            start = 0;
        }
        if (quantidade < 0) {
            quantidade = 0;
        }
        if (quantidade > contatos.size()) {
            quantidade = contatos.size();
        }
        if (start+quantidade >= contatos.size()) {
            quantidade = (contatos.size() - start);
        }
        for (int i = start; i < start + quantidade; i++) {
            if(i == contatos.size()) {
                break;
            }
            contatosEncontrados.add(contatos.get(i));
        }
        return contatosEncontrados;
    }


}
