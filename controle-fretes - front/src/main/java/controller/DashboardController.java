package controller;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Transportadora;
import service.ApiService;

public class DashboardController {

    @FXML
    private TableView<Transportadora> tblRanking; // Generic TableView
    @FXML
    private TableColumn<Transportadora, String> colTransportadora; // Generic TableColumn
    @FXML
    private TableColumn<Transportadora, Integer> colQtdFretes;    // Generic TableColumn

    private final ObservableList<Transportadora> rankingList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configura colunas
        colTransportadora.setCellValueFactory(data -> data.getValue().nomeProperty());
        colQtdFretes.setCellValueFactory(data -> data.getValue().qtdFretesProperty().asObject());

        // Carrega ranking ao iniciar
        loadRanking();
    }

    private void loadRanking() {
        try {
            String json = ApiService.get("ranking"); // seu endpoint
            JSONArray array = new JSONArray(json);

            rankingList.clear();
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                Transportadora t = new Transportadora(
                        obj.getInt("id"),
                        obj.getString("nome"),
                        obj.getInt("qtdFretes")
                );
                rankingList.add(t);
            }

            tblRanking.setItems(rankingList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}