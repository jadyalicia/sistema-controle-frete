package com.fretes.front.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML
    private Label lblTotalFretes;

    @FXML
    public void initialize() {
        lblTotalFretes.setText("Sistema iniciado ✅");
    }
}