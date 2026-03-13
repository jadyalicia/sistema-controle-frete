package com.fretes.front.controller;

import com.fretes.front.model.Transportadora;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TransportadoraController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCnpj;

    @FXML
    private TextField txtContato;

    @FXML
    private TableView<Transportadora> tblTransportadoras;

    @FXML
    private TableColumn<Transportadora, String> colNome;

    @FXML
    private TableColumn<Transportadora, String> colCnpj;

    @FXML
    private TableColumn<Transportadora, String> colContato;

    private ObservableList<Transportadora> lista =
            FXCollections.observableArrayList();

    // ================= INIT =================
    @FXML
    public void initialize() {

        configurarTabela();
        carregarMock();
    }

    // ================= CONFIG TABLE =================
    private void configurarTabela() {

        colNome.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getNome()));

        colCnpj.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getCnpj()));

        colContato.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getContato()));

        tblTransportadoras.setItems(lista);
    }

    // ================= MOCK DATA =================
    private void carregarMock() {

        lista.addAll(
                new Transportadora("JSL", "00.000.000/0001-00", "Carlos"),
                new Transportadora("Braspress", "11.111.111/0001-11", "Ana")
        );
    }

    // ================= AÇÕES =================

    @FXML
    private void novo() {
        limparCampos();
    }

    @FXML
    private void salvar() {

        Transportadora t = new Transportadora(
                txtNome.getText(),
                txtCnpj.getText(),
                txtContato.getText()
        );

        lista.add(t);
        limparCampos();
    }

    @FXML
    private void excluir() {

        Transportadora selecionada =
                tblTransportadoras.getSelectionModel().getSelectedItem();

        if (selecionada != null) {
            lista.remove(selecionada);
        }
    }

    private void limparCampos() {
        txtNome.clear();
        txtCnpj.clear();
        txtContato.clear();
    }
}