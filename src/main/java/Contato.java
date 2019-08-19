import java.util.Set;

public class Contato {
    private Integer id = 0;
    private String nome;
    private Set<String> telefone;

    public Contato(String nome, Set<String> telefone) {
        this.nome = nome;
        this.telefone = telefone;
        this.id += 1;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<String> getTelefone() {
        return telefone;
    }

    public void setTelefone(Set<String> telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Telefones: " + telefone;
    }
}
