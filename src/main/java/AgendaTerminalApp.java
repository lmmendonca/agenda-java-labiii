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
                        editaContato(agenda, scanner);
                        break;
                    case "4":
                        removeContato(agenda, scanner);
                        break;
                    case "5":
                        System.out.println(agenda.limparAgenda());
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


    private static void editaContato(Agenda agenda, Scanner scanner) {
        System.out.println("Informe o Nome do contato que deseja editar:");
        String line = scanner.nextLine();

        Contato contato = agenda.findContato(line);

        if (contato != null) {
            System.out.println("Informe o que deseja editar");
            System.out.println("1 - Alterar Nome");
            System.out.println("2 - Adicionar Telefone");
            System.out.println("3 - Remover Telefone");
            System.out.println("4 - Editar Telefone");
            line = scanner.nextLine();

            switch (line) {
                case "1":
                    System.out.println("Informe o novo nome do contato: ");
                    line = scanner.nextLine();
                    contato.setNome(line);
                    agenda.save();
                    System.out.println("Contato Editado com sucesso");
                    break;
                case "2":
                    System.out.println("Informe o telefone que deseja adicionar: ");
                    line = scanner.nextLine();
                    contato.addTelefone(line);
                    agenda.save();
                    System.out.println("Contato Editado com sucesso");
                    break;
                case "3":
                    System.out.println("Informe o telefone que deseja remover: ");
                    line = scanner.nextLine();
                    System.out.println(contato.removeTelefone(line));
                    agenda.save();
                    break;
                case "4":
                    System.out.println("Informe o telefone que deseja editar: ");
                    String tel = scanner.nextLine();
                    if (contato.existTelefone(tel)) {
                        System.out.println("Informe o novo numero para o telefone: ");
                        line = scanner.nextLine();
                        System.out.println(contato.editaTelefone(tel, line));
                        agenda.save();
                        break;
                    } else {
                        System.out.println("Telefone não encontrado.");
                    }
                default:
                    System.out.println("Comando não encontrado.");
                    break;

            }
        } else {
            System.out.println("Contato não encontrado");
        }


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
