import java.io.*;
import java.util.*;


public class Terminal {

    public static void main(String[] args) throws IOException {
        Agenda agenda = new Agenda();

        Integer comando = 0;

        String line = "";
        BufferedReader input = null;
        Scanner ler = new Scanner(System.in);
        StringBuffer cmdOut = new StringBuffer();

        System.out.println("------------------");
        System.out.println("Bem vindo a agenda");
        System.out.println("------------------");
        System.out.println("Para adicionar um Contato na agenda insira 1");
        System.out.println("Para listar os Contatos da agenda insira 2");
        System.out.println("Para sair digite 9");

        do {
            try {
                comando = Integer.valueOf(ler.nextLine());

                if (comando == 1) {
                    Integer c = 0;
                    Set<String> fones = new HashSet<String>();

                    System.out.println("Nome do Contato:");
                    String nome = ler.nextLine();

                    System.out.println("Insira Um telefone para o contato");
                    fones.add(ler.nextLine());

                    System.out.println("Insira mais um telefone para o contato");


                    do {
                        line = ler.nextLine();
                        if (line.length() != 0){
                            fones.add(line);
                            System.out.println("Insira mais um telefone para o contato, caso contrario apenas clique Enter;");
                        }
                    }
                    while (line.length() != 0);

                    Contato contato = new Contato(nome, fones);
                    agenda.addContato(contato);
                    saveAgenda(contato);

                    System.out.println("Contato adicionado com sucesso");

                } else if (comando == 2){
                    System.out.println("Nao implementado");

                }else if (comando == 9){
                    System.out.println("Programa finalizado");
                    break;

                } else {
                    System.out.println("Comando não encontrado");
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        } while (comando != 9);

    }


    private static void saveAgenda(Contato contato) throws IOException {
        fileWrite(contato.toString(), "src/main/resources/agenda.txt");
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
}
