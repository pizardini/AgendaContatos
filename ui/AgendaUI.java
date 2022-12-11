package ui;

import enums.TipoContato;
import enums.TipoEndereco;
import enums.TipoTelefone;
import model.Contato;
import model.Endereco;
import model.Telefone;
import util.ConsoleUIHelper;


import java.util.ArrayList;
import java.util.List;

public class AgendaUI {
    // private static List<Contato> agenda;

    // public AgendaUI(){
    //     this.agenda = new ArrayList<>();
    // }
    static List<Contato> agenda = new ArrayList<>();
    
    

    public static void menu () {
        int opcao = ConsoleUIHelper.askChooseOption("Digite a opção desejada:",
        "Adicionar um novo contato", "Listar contatos","Buscar contato","Remover contato", "Remover todos os contatos",
        "Exibir informações de um contato da agenda;","Sair da agenda");
        
        switch (opcao) {
            case 0 -> {
                    Contato contato = new Contato();
                    contato = adicionarContato(contato);
                    if (ConsoleUIHelper.askConfirm("Você deseja adicionar um telefone?", "sim", "não")) {
                        adicionarTelefone(contato);
                    }
                    if (ConsoleUIHelper.askConfirm("Você deseja adicionar um endereço?", "sim", "não")) {
                        adicionarEndereco(contato);
                    }
                    System.out.println("Contato adicionado");
                }
            case 1 -> {
                listarContato();
            }
            case 2 -> {
                String palavraAProcurar = ConsoleUIHelper.askNoEmptyInput("Digite o nome do contato a ser buscado", 3);
                buscarContato(palavraAProcurar);
            }
            case 3 -> { //remover 1 contato
                ConsoleUIHelper.fillVSpace(0, 80);
                if (agenda.size() > 0) {
                    listarContato();
                    Integer idContato = ConsoleUIHelper.askInt("Digite o ID do contato a ser removido") -1;
                    removerContato(idContato);
                } else{
                    System.out.println("Não há contatos na agenda");
                }
            } 
            
            case 4 -> { //remover todos os contatos
                ConsoleUIHelper.fillVSpace(0, 80);
                if(ConsoleUIHelper.askConfirm("Deseja realmente remover todos os contatos???", "sim", "não")){
                    agenda=new ArrayList<>();
                }

            }
            case 5 -> {
                listarContato();
                Integer idContato = ConsoleUIHelper.askInt("Digite o ID do contato") -1;
                exibirContato(agenda.get(idContato));
                
            }
            case 6 -> System.exit(0);
        }

    }

    public static void removerContato(Integer idARemover) {
        List<Contato> agendaTemp = new ArrayList<>();
        agenda.set(idARemover, null);
        for (Contato item : agenda) {
            if(item != null){
                agendaTemp.add(item);
            }
        }
        agenda = agendaTemp;
    }

    public static void buscarContato(String palavra) {
        List<Contato> contatosEncontrados = new ArrayList<>();

        for (int i = 0; i < agenda.size(); i++) {
            if(agenda.get(i).getNomeCompleto().contains(palavra)){
                contatosEncontrados.add(agenda.get(i));
            }
        }
        if(contatosEncontrados.size() == 0){
            System.out.println("Nenhum contato foi encontrado");
        } else if (contatosEncontrados.size() == 1){
            System.out.println("Foi encontrado 1 contato");
            System.out.println(contatosEncontrados.get(0).getNomeCompleto());
        } else{
            System.out.printf("Foram encontrados %d contatos\n",contatosEncontrados.size());
            for (int i = 0; i < contatosEncontrados.size(); i++) {
                System.out.println(i + " - " +contatosEncontrados.get(i).getNomeCompleto());
            }

        }
        
    }

    public static Contato adicionarContato(Contato contato) {
        String nome = ConsoleUIHelper.askNoEmptyInput("Digite o nome do seu contato.", 3);
        String sobrenome = ConsoleUIHelper.askNoEmptyInput("Digite o sobrenome do seu contato.", 3);
        int tipo = ConsoleUIHelper.askChooseOption("Digite o tipo do seu contato", "Pessoal", "Profissional");
        TipoContato tipoContato;
        if (tipo == 0) {
            tipoContato = TipoContato.PESSOAL;
        }
        else {
            tipoContato = TipoContato.PROFISSIONAL;
        }
        contato = new Contato(nome, sobrenome, tipoContato);
        
        agenda.add(contato);
        return contato;
    }
//    public static void gravarContato(Contato contato, List<Contato> agenda) {
//        agenda.add(contato);
//    }

    public static void adicionarTelefone(Contato contato) {
        TipoTelefone tipoTelefone = TipoTelefone.CELULAR;
        String ddd = ConsoleUIHelper.askSimpleInput("Digite o DDD");
        String numero = ConsoleUIHelper.askNoEmptyInput("Digite o número.", 3);
        int tipo = ConsoleUIHelper.askChooseOption("Digite o tipo do número", "Celular", "Residencial", "Comercial");
        switch (tipo) {
            case 0 -> {
                tipoTelefone = TipoTelefone.CELULAR;
            }
            case 1 -> {
                tipoTelefone = TipoTelefone.RESIDENCIAL;
            }
            case 2 -> {
                tipoTelefone = TipoTelefone.COMERCIAL;
            }
        }
        Telefone telefone = new Telefone(ddd, numero, tipoTelefone);

    // Checar duplicidade do telefone neste contato

        agenda.get(contatoPosition(contato)).addTelefone(telefone);
    }

    public static int contatoPosition(Contato contato) {
        String nomeCompletoString = contato.getNomeCompleto();
        for (int index = 0; index < agenda.size(); index++) {
            if(agenda.get(index).getNomeCompleto().equals(nomeCompletoString)){
                return index;
            }
        }
        return -2;
    }


    public static void adicionarEndereco(Contato contato) {
        // String logradouro = ConsoleUIHelper.askSimpleInput("Digite o logradouro");
        // String numero = ConsoleUIHelper.askSimpleInput("Digite o número da casa");
        String cidade = ConsoleUIHelper.askSimpleInput("Digite a cidade");
        String estado = ConsoleUIHelper.askSimpleInput("Digite o estado");
        // String cep = ConsoleUIHelper.askSimpleInput("Digite o CEP");
        // int tipo = ConsoleUIHelper.askChooseOption("Digite o tipo do endereço", "Residencial","Comercial","Deixar em branco");
        // TipoEndereco tipoEndereco;
        // switch (tipo) {
        //     case 0 -> {
        //         tipoEndereco = TipoEndereco.RESIDENCIAL;
        //     }
        //     case 1 -> {
        //         tipoEndereco = TipoEndereco.COMERCIAL;
        //     }
        //     case 2 -> System.out.println("endereço em branco");
        // }

        Endereco endereco = new Endereco(cidade, estado);
        agenda.get(contatoPosition(contato)).addEndereco(endereco);
    }

    public static void listarContato() {
        for (int i = 0; i < agenda.size(); i++) {
            System.out.println(i+1 + " - " + agenda.get(i).getNomeCompleto());
        }
    }

    public static void exibirContato(Contato contato) {
        System.out.println(contato.exibirContato());
        
    }
//    public static void exibirContato(List<Contato> agenda) {
//
//        String contatoDetalhado = agenda.get().getDetalhado();
//        ConsoleUIHelper.drawWithPadding(contatoDetalhado, 80);
//    }
}
