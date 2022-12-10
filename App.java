import model.Contato;
import model.Telefone;
import ui.AgendaUI;
import util.ConsoleUIHelper;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        ConsoleUIHelper.drawHeader("AGENDA", 80);

        do  {
            AgendaUI.menu();

        }
        while (true);





    }

}
