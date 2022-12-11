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
        "Adicionar um novo contato", "Listar contatos","Buscar contato","Editar contato",
        "Remover contato", "Remover todos os contatos",
        "Sair da agenda");
        
        switch (opcao) {
            case 0 -> {//add contato
                    Contato contato = new Contato();
                    contato = adicionarContato(contato);
                    if (ConsoleUIHelper.askConfirm("Você deseja adicionar um telefone?", "sim", "não")) {
                        adicionarTelefone(contato);
                    }
                    if (ConsoleUIHelper.askConfirm("Você deseja adicionar um endereço?", "sim", "não")) {
                        adicionarEndereco(contato);
                    }
                    System.out.println("Contato adicionado");
                    contato.exibirContato();
                }
            case 1 -> {//listar contatos por nome completo
                if(agendaNaoVazia()){
                    listarContato();
                    boolean detalhar = ConsoleUIHelper.askConfirm("Deseja detalhar algum contato", "sim", "não");
                    if (detalhar){
                        Integer idContato = ConsoleUIHelper.askInt("Digite o ID do contato a ser detalhado") -1;
                        exibirContato(agenda.get(idContato));
                    }
                }
            }
            case 2 -> {//buscar contato
                if(agendaNaoVazia()){
                    String palavraAProcurar = ConsoleUIHelper.askNoEmptyInput("Digite o nome do contato a ser buscado", 3);
                    buscarContato(palavraAProcurar);
                    Integer idContato = ConsoleUIHelper.askInt("Digite o ID do contato a ser detalhado") -1;
                    exibirContato(agenda.get(idContato));
                }
            }
            
            case 3 -> {//editar contato
                listarContato();
                Integer idContato = ConsoleUIHelper.askInt("Digite o ID do contato a ser editado") -1;
                exibirContato(agenda.get(idContato));
                int opcaoEditar = ConsoleUIHelper.askChooseOption("Digite a opção desejada:"
                ,"adicionar um telefone do contato","remover um telefone do contato",
                "adicionar um endereço do contato","remover um endereço do contato");
                switch (opcaoEditar){
                    case 0 ->{//adicionar um telefone a um contato;
                        if(agendaNaoVazia()){
                            // listarContato();
                            Contato contato = agenda.get(idContato);
                            adicionarTelefone(contato);
                            exibirContato(agenda.get(idContato));
                        }
                    }
                    case 1 -> {//Remover um telefone de um contato da agenda
                        if(agendaNaoVazia()){
                            // listarContato();
                            removerTelefone(agenda.get(idContato));
                            exibirContato(agenda.get(idContato));
                        }
                    }
                    case 6 -> {//Adicionar um endereço a um contato
                        if(agendaNaoVazia()){
                            listarContato();
                            Contato contato = agenda.get(idContato);
                            adicionarEndereco(contato);
                            exibirContato(agenda.get(idContato));
                        }
                    }
                    case 8 -> {//Remover um endereço de um contato da agenda
                        if(agendaNaoVazia()){
                            listarContato();
                            removerEndereco(agenda.get(idContato));
                            exibirContato(agenda.get(idContato));
                        }
                    }
                    
                }

            }
            case 4 -> { //remover 1 contato
                ConsoleUIHelper.fillVSpace(0, 80);
                if (agendaNaoVazia()) {
                    listarContato();
                    Integer idContato = ConsoleUIHelper.askInt("Digite o ID do contato a ser removido") -1;
                    removerContato(idContato);
                    System.out.println("Contato removido com sucesso!");
                }
            } 

            case 5 -> { //remover todos os contatos
                ConsoleUIHelper.fillVSpace(0, 80);
                if(ConsoleUIHelper.askConfirm("Deseja realmente remover todos os contatos???", "sim", "não")){
                    agenda=new ArrayList<>();
                }
            }
            case 6 -> System.exit(0);

            
            // case 9 -> {//Exibir informações de um contato da agenda
            //     if(agendaNaoVazia()){
            //         listarContato();
            //         Integer idContato = ConsoleUIHelper.askInt("Digite o ID do contato") -1;
            //         exibirContato(agenda.get(idContato));
            //     }
            // }
            // case 10 ->{//Listar todos os telefones de um contato
            //     if(agendaNaoVazia()){
            //         listarContato();
            //         Integer idContato = ConsoleUIHelper.askInt("Digite o ID do contato") -1;
            //         System.out.println(agenda.get(idContato).listarTelefones());
            //     }
                
            // }
            // case 11 ->{//Listar todos os endereços de um contato
            //     if(agendaNaoVazia()){
            //         listarContato();
            //         Integer idContato = ConsoleUIHelper.askInt("Digite o ID do contato") -1;
            //         System.out.println(agenda.get(idContato).listarEnderecos());
            //     }
            // }
            // case 12 ->{//Exibir todas as informações de um telefone de um contato
            //     if(agendaNaoVazia()){
            //         listarContato();
            //         Integer idContato = ConsoleUIHelper.askInt("Digite o ID do contato") -1;
            //         System.out.println(agenda.get(idContato).exibirTelefones());
            //     }
            // }
            // case 13 ->{//Exibir todas as informações de um endereço de um contato
            //     if(agendaNaoVazia()){
            //         listarContato();
            //         Integer idContato = ConsoleUIHelper.askInt("Digite o ID do contato") -1;
            //         System.out.println(agenda.get(idContato).exibirEnderecos());
            //     }
            // }
        }

    }

    public static void removerEndereco(Contato contato) {
        // ********** testa funcao
        if(contato.getEnderecos().size() > 0){
            List<Endereco> enderecosTemp = new ArrayList<>();
            System.out.println(contato.exibirEnderecos());

            Integer idEndereco = ConsoleUIHelper.askInt("Digite o ID do endereço a ser removido") ;
            contato.getEnderecos().set(idEndereco,null);
            for (Endereco item : contato.getEnderecos()) {
                if(item != null){
                    enderecosTemp.add(item);
                }
            }
            contato.setEnderecos(enderecosTemp);

        } else {
            System.out.println("Esse contato não possui endereços cadastrados");
        }
    }

    public static void removerTelefone(Contato contato) {
        if(contato.getTelefones().size()>0){
            List<Telefone> telefonestemp= new ArrayList<>();
            System.out.println(contato.exibirTelefones());
            Integer idTelefone = ConsoleUIHelper.askInt("Digite o ID do telefone a ser removido") ;
            contato.getTelefones().set(idTelefone,null);
            for (Telefone item : contato.getTelefones()) {
                if(item != null){
                    telefonestemp.add(item);
                }
            }
            contato.setTelefones(telefonestemp);
        } else {
            System.out.println("Esse contato não possui telefones cadastrados");
        }

    }

    public static boolean agendaNaoVazia() {
        if(agenda.size()>0){
            return true;
        }
        else {
            System.out.println("Não há contatos na agenda");
            return false;
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
            int posicao = contatoPosition(contatosEncontrados.get(0));
            System.out.println(posicao + " - "+ contatosEncontrados.get(0).getNomeCompleto());
        } else{
            System.out.printf("Foram encontrados %d contatos\n",contatosEncontrados.size());
            for (int i = 0; i < contatosEncontrados.size(); i++) {
                int posicao = contatoPosition(contatosEncontrados.get(i));
                System.out.println(posicao + " - " +contatosEncontrados.get(i).getNomeCompleto());
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
            ConsoleUIHelper.drawWithRightPadding("", 80, '#');
            ConsoleUIHelper.fillVSpace(0, 80);
            System.out.println(i+1 + " - " + agenda.get(i).getNomeCompleto()+"\n");
        }
    }

    public static void exibirContato(Contato contato) {
        String exibir = contato.exibirContato();
        System.out.println(exibir);
        // ConsoleUIHelper.drawWithPadding(exibir, 80);
        
    }
}
