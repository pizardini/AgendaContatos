import ui.AgendaUI;
import util.ConsoleUIHelper;


public class App {

    public static void main(String[] args) {
        AgendaUI agenda = new AgendaUI();
        System.out.println();
        
        do  {
            ConsoleUIHelper.drawHeader("AGENDA", 80);
            agenda.menu();
        }
        while (true);

    }

}
