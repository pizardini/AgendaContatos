package ui;

import util.ConsoleUIHelper;

import java.util.Scanner;

public class AgendaUI {

    public static void menu () {
        int opcao = ConsoleUIHelper.askChooseOption("Digite a opção desejada:",
                "Adicionar um contato", "Editar um contato", "Listar contatos",
                "Buscar contato", "Remover contato", "Sair da agenda");

        switch (opcao) {
            case 0 -> System.out.println("Número 0");
            case 1 -> System.out.println("Número 1");
            case 2 -> System.out.println("Número 2");
            case 3 -> System.out.println("Número 3");
            case 4 -> System.out.println("Número 4");
        }

    }



}
