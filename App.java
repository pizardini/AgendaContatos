import model.Contato;
import model.Telefone;
import ui.AgendaUI;
import util.ConsoleUIHelper;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        // AgendaUI agenda = new AgendaUI();
        // agenda.menu();
        System.out.println();
        
        do  {
            
            ConsoleUIHelper.drawHeader("AGENDA", 80);
            AgendaUI.menu();

        }
        while (true);



    }

}
