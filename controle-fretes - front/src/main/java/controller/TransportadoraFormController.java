package controller;

import java.io.IOException;

import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Transportadora;
import service.ApiService;

public class TransportadoraFormController {

    @FXML private TextField txtNome;
    @FXML private Button btnSalvar;
    @FXML private Button btnCancelar;

    private Integer transportadoraId = null; // null = novo

    public void setTransportadora(Transportadora t) {
        this.transportadoraId = t.getId();
        txtNome.setText(t.getNome());
    }

    @FXML
    private void salvar() {
        String nome = txtNome.getText();

        if (nome.isEmpty()) {
            mostrarAlerta("Informe o nome da transportadora");
            return;
        }

        try {
            JSONObject json = new JSONObject();
            json.put("nome", nome);

            if (transportadoraId == null) {
                ApiService.post("transportadoras", json.toString());
            } else {
                ApiService.put("transportadoras/" + transportadoraId, json.toString());
            }

            fecharForm();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Erro ao salvar transportadora");
        }
    }

    @FXML
    private void cancelar() {
        fecharForm();
    }

    private void fecharForm() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg);
        alert.showAndWait();
    }
}