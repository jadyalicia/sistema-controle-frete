package com.fretes.front.controller;

import com.fretes.front.model.RankingCotacao;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RankingController {

    @FXML
    private TableView<RankingCotacao> tblRanking;

    @FXML
    private TableColumn<RankingCotacao, Number> colPosicao;

    @FXML
    private TableColumn<RankingCotacao, String> colTransportadora;

    @FXML
    private TableColumn<RankingCotacao, Number> colValor;

    private ObservableList<RankingCotacao> lista = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configura as colunas
        colPosicao.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getPosicao()));
        colTransportadora.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTransportadora()));
        colValor.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getValor()));

        tblRanking.setItems(lista);
    }

    // ===== Método chamado pelo botão no FXML =====
    @FXML
    private void calcularCotacao(ActionEvent event) {
        System.out.println("Botão 'Calcular Cotação' clicado!");
        // Aqui você pode adicionar lógica fixa ou exibir mensagem
    }

    // ===== Método chamado pelo FretesController com dados do pedido =====
    public void calcularCotacao(String numeroPedido, String cliente, String cidade, String estado,
                                double peso, double cubagem) {

        lista.clear();

        // Exemplo de cálculo simples para ranking
        double valorBase = peso * 10 + cubagem * 500;
        lista.addAll(
            new RankingCotacao("JSL", valorBase * 0.95, 1),
            new RankingCotacao("Braspress", valorBase * 1.00, 2),
            new RankingCotacao("Translovato", valorBase * 1.05, 3)
        );

        System.out.println("Ranking calculado para pedido: " + numeroPedido + " | " + cliente);
    }
}