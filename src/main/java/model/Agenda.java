package model;

import com.google.gson.Gson;
import helper.FileHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Agenda {
    private static final String AGENDA_JSON_PATH = "src/main/resources/agenda.json";
    private List<Contato> contatos;

    public Agenda(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public String addContato(Contato contato){
        contatos.add(contato);
        sort();
        save();
        return "Contato adicionado com Sucesso!";
    }

    private void sort(){
        contatos.sort(Comparator.comparing(Contato::getNome));
    }

    public String deleteContato(final String nome){
        for (Contato c : contatos) {
            if (c.getNome().equals(nome)) {
                contatos.remove(c);
                save();
                return "Contato Removido com sucesso!";
            }
        }

        return "Contato de nome: " + nome + "não encontrado.";
    }

    public String limparAgenda(){
        setContatos(new ArrayList<>());
        save();
        return "Limpeza concluida com sucesso!";
    }

    private int size(){
        return contatos.size();
    }

    public int nextId() {
        int maiorId = 1;
        for (int i = 0; i < size(); i++) {
            if (maiorId < contatos.get(i).getId()) maiorId = contatos.get(i).getId();
        }
        return maiorId + 1;
    }

    private void save() {
        try {
            FileHelper.write(new Gson().toJson(this), AGENDA_JSON_PATH, false);
        } catch (IOException e) {
            System.out.println("Erro ao salvar Agenda");
        }
    }

    public void printAgenda(){
        if (contatos.size() == 0) {
            System.out.println("Nenhum contato cadastrado na Agenda.");
        } else {
            contatos.forEach(c -> {
                System.out.println(c.toString().replace("[", "").replace("]", ""));
            });
        }
    }

    @Override
    public String toString() {
        return "Agenda" +
                "contatos" + contatos +
                '}';
    }
}
