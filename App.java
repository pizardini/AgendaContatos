import model.Contato;
import model.Telefone;

public class App {
    public static void main(String[] args) {

        Contato ana = new Contato("Ana", "Muratori");

        ana.setTelefones(new Telefone("996555742"));
        ana.setTelefones(new Telefone("996555742"));

        System.out.println(ana.getTelefones());
    }
}
