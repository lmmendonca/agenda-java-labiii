import com.google.gson.Gson;
import helper.FileHelper;
import model.Agenda;
import model.Contato;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AgendaTerminalApp {
    private static final String AGENDA_JSON_PATH = "src/main/resources/agenda.json";
    private static final String MENU_PATH = "src/main/resources/templates/menu.txt";

    public static void main(String[] args) throws IOException {
        Agenda agenda = new Agenda(restauraAgenda());
        Scanner scanner = new Scanner(System.in);
        String comando = "";

        FileHelper.printFromFile(MENU_PATH);

        do {
            try {
                comando = scanner.nextLine();

                switch (comando) {
                    case "1":
                        agenda.printAgenda();
                        break;
                    case "2":
                        addContato(agenda, scanner);
                        comando = "";
                        break;
                    case "3":
                        System.out.println("Comando não implementado");
                        break;
                    case "4":
                        removeContato(agenda, scanner);
                        break;
                    case "5":
                        System.out.println(agenda.limparAgenda());
                        break;
                    case "6":
                        System.out.println("Comando não implementado");
                        break;
                    case "7":
                        System.out.println("Comando não implementado");
                        break;
                    case "8":
                        FileHelper.printFromFile(MENU_PATH);
                        break;
                    case "9":
                        System.out.println("Programa finalizado");
                        break;
                    default:
                        System.out.println("Comando não encontrado.");
                        break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } while (!comando.equals("9"));

    }

    private static void removeContato(Agenda agenda, Scanner scanner) {
        System.out.println("Informe o Nome:");
        String nome = scanner.nextLine();

        System.out.println(agenda.deleteContato(nome));
    }


    private static void addContato(Agenda agenda, Scanner scanner) {
        List<String> fones = new ArrayList<>();
        String l;

        System.out.println("Informe o Nome:");
        String nome = scanner.nextLine();

        System.out.println("Informe um Telefone:");
        fones.add(scanner.nextLine());

        do {
            System.out.println("Infome mais um Telefone, caso não deseje, apenas clique ENTER.");
            l = scanner.nextLine();
            if (l.length() != 0) {
                fones.add(l);
            }
        } while (l.length() != 0);

        System.out.println(agenda.addContato(new Contato(agenda.nextId(), nome, fones)));
    }

    private static List<Contato> restauraAgenda() {
        Gson gson = new Gson();
        String json = null;

        try {
            json = FileHelper.buildStringFromFile(AGENDA_JSON_PATH);
        } catch (IOException e) {
            System.out.println("Erro ao restaurar agenda");
        }

        assert json != null;
        if (json.equals("")) return new ArrayList<>();

        Agenda ag = gson.fromJson(json, Agenda.class);
        return ag.getContatos();
    }


}
