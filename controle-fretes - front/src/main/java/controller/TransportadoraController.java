package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.ApiService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import model.Transportadora;

public class TransportadoraController {

    @FXML private TableView<Transportadora> tblTransportadoras;
    @FXML private TableColumn<Transportadora, Integer> colId;
    @FXML private TableColumn<Transportadora, String> colNome;

    private final ObservableList<Transportadora> transportadorasList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> data.getValue().idProperty().asObject());
        colNome.setCellValueFactory(data -> data.getValue().nomeProperty());

        carregarTabela();
    }

    private void carregarTabela() {
        try {
            String json = ApiService.get("transportadoras");
            JSONArray array = new JSONArray(json);

            transportadorasList.clear();
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                Transportadora t = new Transportadora(
                        obj.getInt("id"),
                        obj.getString("nome")
                );
                transportadorasList.add(t);
            }

            tblTransportadoras.setItems(transportadorasList);

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Erro ao carregar transportadoras");
        }
    }

    @FXML
    private void novoTransportadora() {
        abrirForm("/view/transportadora_form.fxml", "Adicionar Transportadora");
    }

    @FXML
    private void editarTransportadora() {
        Transportadora selecionada = tblTransportadoras.getSelectionModel().getSelectedItem();
        if (selecionada == null) {
            mostrarAlerta("Selecione uma transportadora para editar");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/transportadora_form.fxml"));
            Parent root = loader.load();
            TransportadoraFormController formController = loader.getController();
            formController.setTransportadora(selecionada);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Editar Transportadora");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            carregarTabela();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Erro ao abrir formulário de edição");
        }
    }

    @FXML
    private void deletarTransportadora() {
        Transportadora selecionada = tblTransportadoras.getSelectionModel().getSelectedItem();
        if (selecionada == null) {
            mostrarAlerta("Selecione uma transportadora para deletar");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Deseja realmente deletar?");
        confirm.showAndWait().ifPresent(resp -> {
            if (resp == ButtonType.OK) {
                try {
                    ApiService.delete("transportadoras/" + selecionada.getId());
                    carregarTabela();
                } catch (IOException e) {
                    e.printStackTrace();
                    mostrarAlerta("Erro ao deletar transportadora");
                }
            }
        });
    }

    private void abrirForm(String fxmlPath, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            carregarTabela();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Erro ao abrir formulário");
        }
    }

    private void mostrarAlerta(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg);
        alert.showAndWait();
    }
}