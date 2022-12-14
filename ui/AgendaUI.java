package ui;

import enums.TipoContato;
import enums.TipoEndereco;
import enums.TipoTelefone;
import model.Contato;
import model.Endereco;
import model.Telefone;
import util.ConsoleUIHelper;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AgendaUI {
    private List<Contato> agenda;

    public AgendaUI(){
        this.agenda = new ArrayList<>();
    }



    public void menu () throws IOException, ClassNotFoundException {
        int opcao = ConsoleUIHelper.askChooseOption("Digite a opção desejada:",
        "Adicionar um novo contato", "Listar contatos","Buscar contato", "Remover todos os contatos",
        "Exportar lista de contatos", "Importar lista de contatos", "Salvar e sair da agenda");

        switch (opcao) {
            case 0 -> {//add contato
                    Contato contato = new Contato();
                    contato = adicionarContato(contato);
                    if(agenda.contains(contato)){
                        if (ConsoleUIHelper.askConfirm("Você deseja adicionar um telefone?", "sim", "não")) {
                            adicionarTelefone(contato);
                        }
                        if (ConsoleUIHelper.askConfirm("Você deseja adicionar um endereço?", "sim", "não")) {
                            adicionarEndereco(contato);
                        }
                        System.out.println();

                        contato.exibirContato();

                    }
                }

            case 1 -> {//listar contatos por nome completo
                if(agendaNaoVazia()){
                    listarContato();
                    ConsoleUIHelper.drawLine(80);

                }

            }
            case 2 -> {//buscar contato
                if(agendaNaoVazia()){
                    String palavraAProcurar = ConsoleUIHelper.askNoEmptyInput("Digite o nome do contato a ser buscado:", 3);
                    buscarContato(palavraAProcurar);
                    Integer idContato = ConsoleUIHelper.askInt("Digite o ID do contato a ser exibido:") -1;
                    editarContato(idContato);
                }
            }

            case 3 -> { //remover todos os contatos
                if(!agendaNaoVazia()) break;
                if(ConsoleUIHelper.askConfirm("Deseja realmente remover todos os contatos?", "sim", "não")){
                    agenda = new ArrayList<>();
                    ConsoleUIHelper.drawHeader("Todos os contatos foram removidos da agenda com sucesso!", 80);
                }
            }

            case 4 -> { //Exportação de arquivo em texto
                exportarArquivoTXT();
            }



            case 5 -> { //Importação de arquivo em texto
                importarArquivoTXT();
            }

            case 6 -> {
                exportarArquivoTXT();
                System.exit(0);
            }
        }

    }

    public void editarContato(Integer idContato) {
        boolean continuar = true;
        while(continuar){
            exibirContato(agenda.get(idContato));
            if(agendaNaoVazia()){
                int opcaoEditar = ConsoleUIHelper.askChooseOption("Digite a opção desejada:"
                ,"Adicionar um telefone ao contato","Remover um telefone do contato",
                "Adicionar um endereço do contato","Remover um endereço do contato","Remover este contato","Retornar ao menu inicial");
                switch (opcaoEditar){
                    case 0 ->{//adicionar um telefone a um contato;
                        if(agendaNaoVazia()){
                            Contato contato = agenda.get(idContato);
                            adicionarTelefone(contato);
                            exibirContato(agenda.get(idContato));
                        }
                    }
                    case 1 -> {//Remover um telefone de um contato da agenda
                        if(agendaNaoVazia()){
                            removerTelefone(agenda.get(idContato));
                        }
                    }
                    case 2 -> {//Adicionar um endereço a um contato
                        if(agendaNaoVazia()){
                            Contato contato = agenda.get(idContato);
                            adicionarEndereco(contato);
                        }
                    }
                    case 3 -> {//Remover um endereço de um contato da agenda
                        if(agendaNaoVazia()){
                            removerEndereco(agenda.get(idContato));
                        }
                    }
                    case 4 -> {//remover esse contato
                        if (agendaNaoVazia()) {
                            removerContato(idContato);
                            ConsoleUIHelper.drawHeader("Contato removido com sucesso!", 80);
                            continuar = false;
                        }
                    }
                    case 5 ->{//retornar ao menu inicial
                        continuar = false;
                        break;
                    }

                }
            }

        }
    }

    public Contato adicionarContato(Contato contato) {
        String nome = ConsoleUIHelper.askNoEmptyInput("Digite o nome do contato:", 3);
        String sobrenome = ConsoleUIHelper.askNoEmptyInput("Digite o sobrenome do contato:", 3);
        int tipo = ConsoleUIHelper.askChooseOption("Digite o tipo de contato: ", "Pessoal", "Profissional");
        TipoContato tipoContato;
        if (tipo == 0) {
            tipoContato = TipoContato.PESSOAL;
        }
        else {
            tipoContato = TipoContato.PROFISSIONAL;
        }
        contato = new Contato(nome, sobrenome, tipoContato);

        if (checarContato(contato)) {
            agenda.add(contato);
            return contato;
        }else {
            contato = new Contato();
            return contato;
        }
    }

    public void adicionarTelefone(Contato contato) {
        TipoTelefone tipoTelefone = TipoTelefone.CELULAR;
        String ddd = ConsoleUIHelper.askSimpleInput("Digite o DDD: ");
        String numero = ConsoleUIHelper.askNoEmptyInput("Digite o número: ", 3);
        int tipo = ConsoleUIHelper.askChooseOption("Digite o tipo de Telefone: ", "Celular", "Residencial", "Comercial");
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
        if (contato.checarTelefone(telefone)) {
            agenda.get(contatoPosition(contato)).addTelefone(telefone);
        }
    }

    public void adicionarEndereco(Contato contato) {
        String logradouro = ConsoleUIHelper.askSimpleInput("Digite o logradouro: ");
        String numero = ConsoleUIHelper.askSimpleInput("Digite o número da edificação: ");
        String cidade = ConsoleUIHelper.askSimpleInput("Digite a cidade: ");
        String estado = ConsoleUIHelper.askSimpleInput("Digite o estado: ");
        String cep = ConsoleUIHelper.askSimpleInput("Digite o CEP: ");
        int tipo = ConsoleUIHelper.askChooseOption("Digite o tipo do endereço: ", "Residencial","Comercial");
        TipoEndereco tipoEndereco = TipoEndereco.RESIDENCIAL;
        switch (tipo) {
            case 0 -> {
                tipoEndereco = TipoEndereco.RESIDENCIAL;
            }
            case 1 -> {
                tipoEndereco = TipoEndereco.COMERCIAL;
            }
            case 2 -> System.out.println("Endereço em branco.");
        }

        Endereco endereco = new Endereco(cep, logradouro, numero, cidade, estado, tipoEndereco);
        if (contato.checarEndereco(endereco)) {
            agenda.get(contatoPosition(contato)).addEndereco(endereco);
        }
    }

    public void removerContato(Integer idARemover) {
        List<Contato> agendaTemp = new ArrayList<>();
        agenda.set(idARemover, null);
        for (Contato item : agenda) {
            if(item != null){
                agendaTemp.add(item);
            }
        }
        agenda = agendaTemp;
    }

    public void removerTelefone(Contato contato) {
        if(contato.getTelefones().size()>0){
            List<Telefone> telefonestemp= new ArrayList<>();
            System.out.println(contato.exibirTelefones());
            Integer idTelefone = ConsoleUIHelper.askInt("Digite o ID do telefone a ser removido:") ;
            contato.getTelefones().set(idTelefone,null);
            for (Telefone item : contato.getTelefones()) {
                if(item != null){
                    telefonestemp.add(item);
                }
            }
            contato.setTelefones(telefonestemp);
        } else {
            ConsoleUIHelper.drawHeader("Esse contato não possui telefones cadastrados", 80);
        }

    }

    public void removerEndereco(Contato contato) {
        if(contato.getEnderecos().size() > 0){
            List<Endereco> enderecosTemp = new ArrayList<>();
            System.out.println(contato.exibirEnderecos());

            Integer idEndereco = ConsoleUIHelper.askInt("Digite o ID do endereço a ser removido:") ;
            contato.getEnderecos().set(idEndereco,null);
            for (Endereco item : contato.getEnderecos()) {
                if(item != null){
                    enderecosTemp.add(item);
                }
            }
            contato.setEnderecos(enderecosTemp);

        } else {
            ConsoleUIHelper.drawHeader("Esse contato não possui endereços cadastrados", 80);
        }
    }

    public void listarContato() {

        ConsoleUIHelper.drawHeader("Lista de contatos", 80);
        //Definir paginação padrão
        int tamanhoPagina = 5;
        int posicaoAtual = 0;
        System.out.println();
        listarPaginado(posicaoAtual,tamanhoPagina).forEach( contato -> {
            System.out.println(contatoPosition(contato) +" - "+ contato.getNomeCompleto());
        });
        ConsoleUIHelper.drawLine(80);
        System.out.println();
        Integer opcaoPagina;
        do {
            opcaoPagina = ConsoleUIHelper.askChooseOption("Selecione a opção desejada", "Exibir contato", "Próxima página", "Voltar ao menu inicial");
            switch (opcaoPagina) {
                case 0 -> {
                    ConsoleUIHelper.drawLine(80);
                    System.out.println();
                    Integer idContato = ConsoleUIHelper.askInt("Digite o ID do contato a ser exibido:");
                    editarContato(idContato);
                    break;
                }
                case 1 -> {
                    ConsoleUIHelper.drawHeader("Lista de contatos", 80);
                    posicaoAtual += tamanhoPagina;
                    listarPaginado(posicaoAtual, tamanhoPagina).forEach(contato -> {
                        System.out.println(contatoPosition(contato) + " - " + contato.getNomeCompleto());
                    });
                    ConsoleUIHelper.drawLine(80);
                    System.out.println();
                }
                case 2 -> {
                    opcaoPagina = 2;
                    break;
                }
            }
        } while (opcaoPagina == 1);


    }

    public void buscarContato(String palavra) {
        List<Contato> contatosEncontrados = new ArrayList<>();

        for (int i = 0; i < agenda.size(); i++) {
            if(agenda.get(i).getNomeCompleto().contains(palavra)){
                contatosEncontrados.add(agenda.get(i));
            }
        }
        if(contatosEncontrados.size() == 0){
            System.out.println("Nenhum contato foi encontrado.");
        } else if (contatosEncontrados.size() == 1){
            System.out.println("Foi encontrado 1 contato.");
            int posicao = contatoPosition(contatosEncontrados.get(0))+1;
            System.out.println(posicao  + " - "+ contatosEncontrados.get(0).getNomeCompleto());
        } else{
            System.out.printf("Foram encontrados %d contatos.\n",contatosEncontrados.size());
            for (int i = 0; i < contatosEncontrados.size(); i++) {
                int posicao = contatoPosition(contatosEncontrados.get(i))+1;
                System.out.println(posicao + " - " +contatosEncontrados.get(i).getNomeCompleto());
            }

        }

    }

    public void exportarArquivoTXT() throws FileNotFoundException {
        //Exportação do arquivo em bytes
//                File path = new File("agenda.bin");
//                FileOutputStream fos = new FileOutputStream(path);
//                ObjectOutputStream oos = new ObjectOutputStream(fos);
//                oos.writeObject(agenda);
//                oos.flush();
//                System.out.println("Lista salva em: " + path.getPath());
        File path = new File("agenda.txt");
        PrintWriter escrever = new PrintWriter(new FileOutputStream(path, false));
        for (int i = 0; i < agenda.size(); i++) {
            String stringArq = agenda.get(i).toFile();

            escrever.println(stringArq);

        }
        System.out.println("Lista salva em: " + System.getProperty("user.dir"));
        escrever.close();
    }

    public void importarArquivoTXT() throws IOException {
        //Importação de arquivo em ‘bytes’
//                File path = new File("agenda.bin");
//                FileInputStream fis = new FileInputStream(path);
//                ObjectInputStream ois = new ObjectInputStream(fis);
//                agenda = (List<Contato>) ois.readObject();

        File path = new File("agenda.txt");
        FileReader fr = new FileReader(path);
        BufferedReader leitor = new BufferedReader(fr);
        String linha;
        while ((linha = leitor.readLine()) != null) {
            String lido = linha;
            String dados[] = lido.split(":");

            String nome = dados[0];
            String sobrenome = dados[1];
            TipoContato tipoContato = TipoContato.valueOf(dados[2]);
            String logradouro = dados[3];
            String n = dados[4];
            String cidade = dados[5];
            String estado = dados[6];
            String cep = dados[7];
            TipoEndereco tipoendereco = TipoEndereco.valueOf(dados[8]);
            String ddd = dados[9];
            String numero = dados[10];
            TipoTelefone tipoTelefone = TipoTelefone.valueOf(dados[11]);

            Contato contato = new Contato(nome, sobrenome, tipoContato);
            agenda.add(contato);
            Telefone telefone = new Telefone(ddd, numero, tipoTelefone);
            agenda.get(contatoPosition(contato)).addTelefone(telefone);
            Endereco endereco = new Endereco(logradouro, n, cidade, estado, cep, tipoendereco);
            agenda.get(contatoPosition(contato)).addEndereco(endereco);
        }
        System.out.println("Lista carregada com sucesso");
        leitor.close();
        fr.close();
    }

   public void exibirContato(Contato contato) {
        ConsoleUIHelper.drawLine(80);
        contato.exibirContato();
        ConsoleUIHelper.drawLine(80);

    }

    public boolean checarContato(Contato contato) {
        for (Contato item: agenda) {
            if (item.equals(contato)) {
                ConsoleUIHelper.drawHeader("Contato já cadastrado nesta agenda",80);
                return false;
            }
        }
        return true;
    }

    public boolean agendaNaoVazia() {
        if(agenda.size()>0){
            return true;
        }
        else {
            ConsoleUIHelper.drawHeader("Não há contatos na agenda", 80);
            return false;
        }
    }

    public int contatoPosition(Contato contato) {
        String nomeCompletoString = contato.getNomeCompleto();
        for (int index = 0; index < agenda.size(); index++) {
            if(agenda.get(index).getNomeCompleto().equals(nomeCompletoString)){
                return index;
            }
        }
        return -2;
    }

    public List<Contato> listarPaginado (int start, int quantidade) {
        List<Contato> contatosEncontrados = new ArrayList<>();
        if (start < 0 || start >= agenda.size()) {
            start = 0;
        }
        if (quantidade < 0) {
            quantidade = 0;
        }
        if (quantidade > agenda.size()) {
            quantidade = agenda.size();
        }
        if (start+quantidade >= agenda.size()) {
            quantidade = (agenda.size() - start);
        }
        for (int i = start; i < start + quantidade; i++) {
            if(i == agenda.size()) {
                break;
            }
            contatosEncontrados.add(agenda.get(i));
        }
        return contatosEncontrados;
    }

}
