package helper;

import java.io.*;

public class FileHelper {

    public static void write(String text, String fileOut, Boolean append) throws IOException {
        FileWriter out = null;

        try {
            out = new FileWriter(fileOut, append);
            out.append(text).append(String.valueOf('\n'));

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (out != null) out.close();
        }
    }

    public static String buildStringFromFile(String path) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = "";
        StringBuilder builder = new StringBuilder();
        while (true) {
            if (linha != null) {
                builder.append(linha);

            } else
                break;
            linha = buffRead.readLine();
        }
        buffRead.close();
        return builder.toString();
    }

    public static void printFromFile(String path) throws IOException {
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

    public static void cleanFile(String path) throws IOException {
        FileWriter fwOb = new FileWriter(path, false);
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
        System.out.println("Limpeza concluida.");
    }

}
