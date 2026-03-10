package com.fretes.front.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class MainController {

    @FXML
    private AnchorPane contentArea;

    @FXML
    public void initialize() {
        abrirDashboard();
    }

    @FXML
    private void abrirDashboard() {
        carregarTela("/view/dashboard.fxml");
    }

    @FXML
    private void abrirRelatorios() {
        carregarTela("/view/relatorios.fxml");
    }

    @FXML
    private void abrirRanking() {
        carregarTela("/view/ranking.fxml");
    }

    @FXML
    private void abrirFretes() {
        carregarTela("/view/fretes.fxml");
    }

    private void carregarTela(String caminho) {
        try {
            Node tela = FXMLLoader.load(getClass().getResource(caminho));

            contentArea.getChildren().setAll(tela);

            AnchorPane.setTopAnchor(tela, 0.0);
            AnchorPane.setBottomAnchor(tela, 0.0);
            AnchorPane.setLeftAnchor(tela, 0.0);
            AnchorPane.setRightAnchor(tela, 0.0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}