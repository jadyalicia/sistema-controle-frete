package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Frete {
    private final IntegerProperty id;
    private final StringProperty transportadora;
    private final DoubleProperty valor;

    public Frete(int id, String transportadora, double valor) {
        this.id = new SimpleIntegerProperty(id);
        this.transportadora = new SimpleStringProperty(transportadora);
        this.valor = new SimpleDoubleProperty(valor);
    }

    public IntegerProperty idProperty() { return id; }
    public StringProperty transportadoraProperty() { return transportadora; }
    public DoubleProperty valorProperty() { return valor; }

    public int getId() { return id.get(); }
    public String getTransportadora() { return transportadora.get(); }
    public double getValor() { return valor.get(); }
}
