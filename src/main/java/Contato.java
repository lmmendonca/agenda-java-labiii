import java.util.List;

public class Contato {
    private Integer id;
    private String nome;
    private List<String> telefones;

    public Contato(Integer id, String nome, List<String> telefones) {
        this.id = id;
        this.nome = nome;
        this.telefones = telefones;
    }

    public Contato addTelefone(String telefone){
        telefones.add(telefone);
        return this;
    }

    public Contato rmTelefone(String telefone){
        telefones.remove(telefone);
        return this;
    }

    public Contato putTelefone(String telefone){
        for (String t : telefones) {
            if (t.equals(telefone)) {
                t = telefone;
                System.out.println("Telefone alterado com sucesso!");
                return this;
            }
        }
        System.out.println("Telefone não encontrato!");
        return this;
    }

    public Contato putNome(String nome){
        this.nome = nome;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getTelefone() {
        return telefones;
    }

    public void setTelefone(List<String> telefones) {
        this.telefones = telefones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Telefones: " + telefones;
    }
}
