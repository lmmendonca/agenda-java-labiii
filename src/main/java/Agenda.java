import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<Contato> contatos = new ArrayList<Contato>();

    public Agenda() {
    }



    public String addContato(Contato contato){
        contatos.add(contato);
        return "Contato: " + contato.toString() + " Adicionado com sucesso!";
    }

    public String rmContato(final String nome){
        for (Contato c : contatos) {
            if (c.getNome().equals(nome)) {
                contatos.remove(c);
                return "Contato: " + c + " Removido com sucesso!";
            }
        }

        return "Contato de nome: " + nome + "não encontrado.";
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    @Override
    public String toString() {
        return "Agenda{" +
                "contatos=" + contatos +
                '}';
    }
}
