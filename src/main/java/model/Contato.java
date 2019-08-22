package model;

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

    public String removeTelefone(String telefone){
        String t = findTelefone(telefone);
        if (t != null) {
            telefones.remove(t);
            return "Telefone Removido com sucesso!";
        }

        return "Telefone não encontrato!";
    }

    public String editaTelefone(String oldTel, String newTel){
        String t = findTelefone(oldTel);
        if (t != null) {
            int id = telefones.indexOf(t);
            telefones.set(id, newTel);
            return "Telefone Editado com sucesso!";
        }

        return "Telefone não encontrato!";
    }

    private String findTelefone(String telefone){
        for (String t : telefones) {
            if (t.equals(telefone)) {
                return t;
            }
        }
        return null;
    }

    public boolean existTelefone(String telefone){
        for (String t : telefones) {
            if (t.equals(telefone)) {
                return true;
            }
        }
        return false;
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
