package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import service.ApiService;
import org.json.JSONObject;

import java.io.IOException;

public class FreteFormController {

    @FXML private TextField txtTransportadora;
    @FXML private TextField txtValor;
    @FXML private Button btnSalvar;
    @FXML private Button btnCancelar;

    private Integer freteId = null; // null significa novo frete

    /**
     * Configura o formulário com um frete existente (para edição)
     */
    public void setFrete(Frete frete) {
        this.freteId = frete.getId();
        txtTransportadora.setText(frete.getTransportadora());
        txtValor.setText(String.valueOf(frete.getValor()));
    }

    @FXML
    private void salvar() {
        String transportadora = txtTransportadora.getText();
        String valorStr = txtValor.getText();

        if (transportadora.isEmpty() || valorStr.isEmpty()) {
            mostrarAlerta("Preencha todos os campos");
            return;
        }

        try {
            double valor = Double.parseDouble(valorStr);
            JSONObject json = new JSONObject();
            json.put("transportadora", transportadora);
            json.put("valor", valor);

            if (freteId == null) {
                ApiService.post("fretes", json.toString());
            } else {
                ApiService.put("fretes/" + freteId, json.toString());
            }

            fecharForm();

        } catch (NumberFormatException e) {
            mostrarAlerta("Valor inválido");
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Erro ao salvar frete");
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