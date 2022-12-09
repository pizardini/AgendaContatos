package ui;

import enums.TipoContato;
import enums.TipoEndereco;
import enums.TipoTelefone;
import model.Contato;
import util.ConsoleUIHelper;

public class AgendaUI {

    public static void menu () {
        int opcao = ConsoleUIHelper.askChooseOption("Digite a opção desejada:",
                "Adicionar um novo contato", "Editar um contato", "Listar contatos",
                "Buscar contato", "Remover contato", "Sair da agenda");

        switch (opcao) {
            case 0 -> {
                adicionarContato();
                adicionarTelefone();
                adicionarEndereco();
            }
            case 1 -> System.out.println("Número 1");
            case 2 -> System.out.println("Número 2");
            case 3 -> System.out.println("Número 3");
            case 4 -> System.out.println("Número 4");
        }

    }



    public static void adicionarContato() {
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

        Contato contato = new Contato(nome, sobrenome, tipoContato);
    }

    public static void adicionarTelefone() {
        String ddd = ConsoleUIHelper.askSimpleInput("Digite o DDD");
        String numero = ConsoleUIHelper.askNoEmptyInput("Digite o número.", 3);
        int tipo = ConsoleUIHelper.askChooseOption("Digite o tipo do número", "Celular", "Residencial", "Comercial");
        TipoTelefone tipoTelefone;
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
    }

    private static void adicionarEndereco() {
        String logradouro = ConsoleUIHelper.askSimpleInput("Digite o logradouro");
        String numero = ConsoleUIHelper.askSimpleInput("Digite o número da casa");
        String cidade = ConsoleUIHelper.askSimpleInput("Digite a cidade");
        String estado = ConsoleUIHelper.askSimpleInput("Digite o estado");
        String cep = ConsoleUIHelper.askSimpleInput("Digite o CEP");
        int tipo = ConsoleUIHelper.askChooseOption("Digite o tipo do endereço", "Residencial","Comercial","Deixar em branco");
        TipoEndereco tipoEndereco;
        switch (tipo) {
            case 0 -> {
                tipoEndereco = TipoEndereco.RESIDENCIAL;
            }
            case 1 -> {
                tipoEndereco = TipoEndereco.COMERCIAL;
            }
            case 2 -> System.out.println("endereço em branco");
        }
    }


}
