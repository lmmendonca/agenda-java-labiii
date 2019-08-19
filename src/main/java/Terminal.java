import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class Terminal {
    private static final String AGENDA_PATH = "src/main/resources/agenda.txt";

    public static void main(String[] args) throws IOException {
        Agenda agenda = new Agenda();

        Integer comando = 0;

        String line = "";
        BufferedReader input = null;
        Scanner ler = new Scanner(System.in);
        StringBuffer cmdOut = new StringBuffer();

        printMenu();

        do {
            try {
                comando = Integer.valueOf(ler.nextLine());

                switch (comando) {
                    case 1:
                        leitor(AGENDA_PATH);
                        break;
                    case 2:

                        Integer c = 0;
                        Set<String> fones = new HashSet<String>();

                        System.out.println("Nome do Contato:");
                        String nome = ler.nextLine();

                        System.out.println("Insira Um telefone para o contato");
                        fones.add(ler.nextLine());

                        System.out.println("Insira mais um telefone para o contato, caso contrario apenas clique Enter;\"");

                        do {
                            line = ler.nextLine();
                            if (line.length() != 0) {
                                fones.add(line);
                                System.out.println("Insira mais um telefone para o contato, caso contrario apenas clique Enter;");
                            }
                        }
                        while (line.length() != 0);

                        Contato contato = new Contato(nome, fones);
                        agenda.addContato(contato);
                        saveAgenda(contato);

                        System.out.println("Contato adicionado com sucesso");
                        break;
                    case 3:
                        System.out.println("Comando não implementado");
                        break;
                    case 4:
                        System.out.println("Comando não implementado");
                        break;
                    case 5:
                        clearTheFile(AGENDA_PATH);
                        break;
                    case 6:
                        System.out.println("Comando não implementado");
                        break;
                    case 7:
                        System.out.println("Comando não implementado");
                        break;
                    case 8:
                        printMenu();
                        break;
                    case 9:
                        System.out.println("Programa finalizado");
                        break;
                    default:
                        System.out.println("Comando não encontrado");
                        break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } while (comando != 9);

    }


    private static void saveAgenda(Contato contato) throws IOException {
        fileWrite(contato.toString(), AGENDA_PATH);
    }


    private static void fileWrite(String text, String fileOut) throws IOException {
        FileWriter out = null;

        try {
            out = new FileWriter(fileOut, true);
            out.append(text + '\n');



        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (out != null) out.close();
        }
    }

    private void file(String text, String fileOut) throws IOException {
        FileReader in = null;
        FileWriter out = null;

        try {
            in = new FileReader(text);
            out = new FileWriter(fileOut);
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (in != null) in.close();
            if (out != null) out.close();
        }
    }

    private static void leitor(String path) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = "";
        while (true) {
            if (linha != null) {
                System.out.println(linha);

            } else
                break;
            linha = buffRead.readLine();
        }
        buffRead.close();
    }

    private static void printMenu() throws IOException {
        leitor("src/main/resources/menu.txt");
    }

    private static void clearTheFile(String path) throws IOException {
        FileWriter fwOb = new FileWriter(path, false);
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
        System.out.println("Limpeza concluida.");
    }

    private static void removeContatoFromAgenda(){

    }




}
