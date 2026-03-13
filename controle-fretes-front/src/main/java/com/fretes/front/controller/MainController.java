package com.fretes.front.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class MainController {

    @FXML
    private AnchorPane contentArea;

    // ================= CACHE DE TELAS =================
    private final Map<String, Node> telas = new HashMap<>();

    // ================= INIT =================
    @FXML
    public void initialize() {
        abrirDashboard();
    }

    // ================= MENU =================

    @FXML
    private void abrirDashboard() {
        carregarTela("dashboard.fxml");
    }

    @FXML
    private void abrirFretes() {
        carregarTela("fretes.fxml");
    }

    @FXML
    private void abrirRelatorios() {
        carregarTela("relatorio.fxml");
    }

    @FXML
    private void abrirTransportadora() {
        carregarTela("transportadora.fxml");
    }

    @FXML
    private void abrirTabeladeFrete() {
        carregarTela("tabeladefrete.fxml");
    }

    @FXML
    private void abrirRanking() {
        carregarTela("ranking.fxml");
    }

        @FXML
    private void abrirPedido() {
        carregarTela("pedido.fxml");
    }

    // ================= NAVEGAÇÃO PROFISSIONAL =================

    private void carregarTela(String arquivoFXML) {

        try {

            Node tela;

            // ✅ se já carregou, reutiliza (instantâneo)
            if (telas.containsKey(arquivoFXML)) {

                tela = telas.get(arquivoFXML);

            } else {

                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/view/" + arquivoFXML)
                );

                tela = loader.load();

                // guarda em memória
                telas.put(arquivoFXML, tela);
            }

            // troca conteúdo central
            contentArea.getChildren().setAll(tela);

            // ocupa toda área
            AnchorPane.setTopAnchor(tela, 0.0);
            AnchorPane.setBottomAnchor(tela, 0.0);
            AnchorPane.setLeftAnchor(tela, 0.0);
            AnchorPane.setRightAnchor(tela, 0.0);

        } catch (IOException e) {
            System.err.println("Erro ao carregar tela: " + arquivoFXML);
            e.printStackTrace();
        }
    }
}