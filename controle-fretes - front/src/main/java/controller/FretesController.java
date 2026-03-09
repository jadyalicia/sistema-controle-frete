package controller;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Frete;
import service.ApiService;

public class FretesController {

    @FXML private TableView<Frete> tblFretes;
    @FXML private TableColumn<Frete, Integer> colId;
    @FXML private TableColumn<Frete, String> colTransportadora;
    @FXML private TableColumn<Frete, Double> colValor;

    private final ObservableList<Frete> fretesList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> data.getValue().idProperty().asObject());
        colTransportadora.setCellValueFactory(data -> data.getValue().transportadoraProperty());
        colValor.setCellValueFactory(data -> data.getValue().valorProperty().asObject());

        carregarTabela();
    }

    private void carregarTabela() {
        try {
            String json = ApiService.get("fretes");
            JSONArray array = new JSONArray(json);

            fretesList.clear();
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                Frete f = new Frete(
                        obj.getInt("id"),
                        obj.getString("transportadora"),
                        obj.getDouble("valor")
                );
                fretesList.add(f);
            }

            tblFretes.setItems(fretesList);

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Erro ao carregar fretes");
        }
    }

    @FXML
    private void novoFrete() {
        abrirForm("/view/frete_form.fxml", "Novo Frete");
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

            carregarTabela(); // Atualiza tabela
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