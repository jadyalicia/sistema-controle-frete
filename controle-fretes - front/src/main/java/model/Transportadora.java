package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Transportadora {
    private final IntegerProperty id;
    private final StringProperty nome;
    private final IntegerProperty qtdFretes;

    public Transportadora(int id, String nome, int qtdFretes) {
        this.id = new SimpleIntegerProperty(id);
        this.nome = new SimpleStringProperty(nome);
        this.qtdFretes = new SimpleIntegerProperty(qtdFretes);
    }

    public IntegerProperty idProperty() { return id; }
    public StringProperty nomeProperty() { return nome; }
    public IntegerProperty qtdFretesProperty() { return qtdFretes; }

    public int getId() { return id.get(); }
    public String getNome() { return nome.get(); }
    public int getQtdFretes() { return qtdFretes.get(); }
}