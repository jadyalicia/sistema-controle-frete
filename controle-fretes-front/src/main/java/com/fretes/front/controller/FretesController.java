package com.fretes.front.controller;

import com.fretes.front.model.Fretes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class FretesController {

    // ===== TABELA =====
    @FXML private TableView<Fretes> tblFretes;
    @FXML private TableColumn<Fretes, Integer> colPedido;
    @FXML private TableColumn<Fretes, String> colCliente;
    @FXML private TableColumn<Fretes, String> colCidade;
    @FXML private TableColumn<Fretes, String> colEstado;
    @FXML private TableColumn<Fretes, Double> colPeso;
    @FXML private TableColumn<Fretes, Double> colCubagem;
    @FXML private TableColumn<Fretes, String> colTransportadora;
    @FXML private TableColumn<Fretes, Double> colValor;
    @FXML private TableColumn<Fretes, String> colNF;
    @FXML private TableColumn<Fretes, String> colCTe;
    @FXML private TableColumn<Fretes, String> colStatus;

    // ===== FORMULÁRIO =====
    @FXML private TextField txtPedido;
    @FXML private TextField txtCliente;
    @FXML private TextField txtCidade;
    @FXML private TextField txtEstado;
    @FXML private TextField txtPeso;
    @FXML private TextField txtCubagem;
    @FXML private TextField txtValor;
    @FXML private TextField txtNF;
    @FXML private TextField txtCTe;
    @FXML private ComboBox<String> cbTransportadora;
    @FXML private ComboBox<String> cbStatus;

    @FXML private TextField txtFiltroPedido;
    @FXML private Button btnVerRanking;

    private final ObservableList<Fretes> lista = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cbStatus.getItems().addAll("PENDENTE", "COTADO", "APROVADO", "ENTREGUE");
        cbTransportadora.getItems().addAll("JSL", "Braspress", "Translovato");

        configurarTabela();
        carregarFretes();
    }

    private void configurarTabela() {
        colPedido.setCellValueFactory(data -> 
            new javafx.beans.property.SimpleIntegerProperty(
                data.getValue().getNumeroPedido() == null ? 0 : data.getValue().getNumeroPedido()
            ).asObject()
        );
        colCliente.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCliente()));
        colCidade.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCidade()));
        colEstado.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEstado()));
        colPeso.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getPeso() == null ? 0 : data.getValue().getPeso()).asObject());
        colCubagem.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getCubagem() == null ? 0 : data.getValue().getCubagem()).asObject());
        colTransportadora.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTransportadora()));
        colValor.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getValor() == null ? 0 : data.getValue().getValor()).asObject());
        colNF.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNumeroNF()));
        colCTe.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNumeroCte()));
        colStatus.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus()));

        tblFretes.setItems(lista);
    }

    private void carregarFretes() {
        // Exemplo de dados iniciais
        lista.add(new Fretes() {{
            setNumeroPedido(101);
            setCliente("Cliente A");
            setCidade("São Paulo");
            setEstado("SP");
            setPeso(120.0);
            setCubagem(2.5);
            setValor(1500.0);
            setTransportadora("JSL");
            setNumeroNF("12345");
            setNumeroCte("54321");
            setStatus("PENDENTE");
        }});
    }

    @FXML
    private void salvarFrete() {
        Fretes frete = new Fretes();
        frete.setNumeroPedido(txtPedido.getText().isEmpty() ? null : Integer.parseInt(txtPedido.getText()));
        frete.setCliente(txtCliente.getText());
        frete.setCidade(txtCidade.getText());
        frete.setEstado(txtEstado.getText());
        frete.setPeso(txtPeso.getText().isEmpty() ? null : Double.parseDouble(txtPeso.getText()));
        frete.setCubagem(txtCubagem.getText().isEmpty() ? null : Double.parseDouble(txtCubagem.getText()));
        frete.setValor(txtValor.getText().isEmpty() ? null : Double.parseDouble(txtValor.getText()));
        frete.setNumeroNF(txtNF.getText());
        frete.setNumeroCte(txtCTe.getText());
        frete.setTransportadora(cbTransportadora.getValue());
        frete.setStatus(cbStatus.getValue());

        lista.add(frete);
        novoFrete();
    }

    @FXML
    private void novoFrete() {
        txtPedido.clear();
        txtCliente.clear();
        txtCidade.clear();
        txtEstado.clear();
        txtPeso.clear();
        txtCubagem.clear();
        txtValor.clear();
        txtNF.clear();
        txtCTe.clear();
        cbTransportadora.getSelectionModel().clearSelection();
        cbStatus.getSelectionModel().clearSelection();
    }

    // ===== Método do botão Buscar =====
    @FXML
    private void buscarFretes() {
        System.out.println("Botão Buscar clicado! Filtro: " + txtFiltroPedido.getText());
        // Aqui você pode filtrar a tabela 'lista' conforme o filtro digitado
    }

    // ===== Abrir ranking =====
    @FXML
    private void abrirRanking() {
        Fretes freteSelecionado = tblFretes.getSelectionModel().getSelectedItem();
        if (freteSelecionado == null) {
            System.out.println("Selecione um frete para calcular o ranking!");
            return;
        }

        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/view/ranking.fxml"));
            javafx.scene.Scene scene = new javafx.scene.Scene(loader.load());
            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.setTitle("Top 3 Recomendados");

            RankingController rankingController = loader.getController();
            rankingController.calcularCotacao(
                    freteSelecionado.getNumeroPedido() == null ? "" : freteSelecionado.getNumeroPedido().toString(),
                    freteSelecionado.getCliente(),
                    freteSelecionado.getCidade(),
                    freteSelecionado.getEstado(),
                    freteSelecionado.getPeso() == null ? 0 : freteSelecionado.getPeso(),
                    freteSelecionado.getCubagem() == null ? 0 : freteSelecionado.getCubagem()
            );

            stage.setScene(scene);
            stage.show();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}