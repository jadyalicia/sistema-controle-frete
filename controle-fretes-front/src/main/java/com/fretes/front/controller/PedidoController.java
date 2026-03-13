package com.fretes.front.controller;

import java.io.IOException;

import com.fretes.front.model.Pedido;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PedidoController {

    // Campos do formulário
    @FXML public TextField txtNumeroPedido;
    @FXML public TextField txtCliente;
    @FXML public TextField txtCidade;
    @FXML public TextField txtEstado;
    @FXML public TextField txtPeso;
    @FXML public TextField txtCubagem;

    // Tabela
    @FXML public TableView<Pedido> tblPedidos;
    @FXML public TableColumn<Pedido, Number> colNumeroPedido;
    @FXML public TableColumn<Pedido, String> colCliente;
    @FXML public TableColumn<Pedido, String> colCidade;
    @FXML public TableColumn<Pedido, String> colEstado;
    @FXML public TableColumn<Pedido, Number> colPeso;
    @FXML public TableColumn<Pedido, Number> colCubagem;

    private final ObservableList<Pedido> listaPedidos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configurar colunas da tabela
        colNumeroPedido.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getNumeroPedido()));
        colCliente.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCliente()));
        colCidade.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCidade()));
        colEstado.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEstado()));
        colPeso.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getPeso()));
        colCubagem.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getCubagem()));

        tblPedidos.setItems(listaPedidos);
    }

    // Limpar formulário
    @FXML
    public void novoPedido() {
        txtNumeroPedido.clear();
        txtCliente.clear();
        txtCidade.clear();
        txtEstado.clear();
        txtPeso.clear();
        txtCubagem.clear();
    }

    // Cadastrar pedido
    @FXML
    public void cadastrarPedido() {
        Pedido pedido = new Pedido();
        pedido.setNumeroPedido(txtNumeroPedido.getText().isEmpty() ? 0 : Integer.parseInt(txtNumeroPedido.getText()));
        pedido.setCliente(txtCliente.getText());
        pedido.setCidade(txtCidade.getText());
        pedido.setEstado(txtEstado.getText());
        pedido.setPeso(txtPeso.getText().isEmpty() ? 0 : Double.parseDouble(txtPeso.getText()));
        pedido.setCubagem(txtCubagem.getText().isEmpty() ? 0 : Double.parseDouble(txtCubagem.getText()));

        listaPedidos.add(pedido);
        novoPedido();

        abrirRanking(pedido);
    }

    // Excluir pedido selecionado
    @FXML
    public void excluirPedido() {
        Pedido selecionado = tblPedidos.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            listaPedidos.remove(selecionado);
        }
    }

    // Abrir ranking (exemplo)
    private void abrirRanking(Pedido pedido) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ranking.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Top 3 Recomendados");

            // Passar dados para o controller do ranking
            RankingController rankingController = loader.getController();
            rankingController.calcularCotacao(
                    String.valueOf(pedido.getNumeroPedido()),
                    pedido.getCliente(),
                    pedido.getCidade(),
                    pedido.getEstado(),
                    pedido.getPeso(),
                    pedido.getCubagem()
            );

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}