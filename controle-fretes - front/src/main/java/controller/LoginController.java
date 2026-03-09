package controller;

import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.ApiService;

public class LoginController {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblMessage;

    @FXML
    private void handleLogin() {
        try {
            JSONObject body = new JSONObject();
            body.put("username", txtUsername.getText());
            body.put("password", txtPassword.getText());

            String response = ApiService.login(body.toString());

            if (!response.isEmpty()) {
                lblMessage.setText("Login OK");
                // Aqui você pode abrir a dashboard
            } else {
                lblMessage.setText("Usuário ou senha incorretos");
            }
        } catch (Exception e) {
            lblMessage.setText("Erro: " + e.getMessage());
        }
    }
}