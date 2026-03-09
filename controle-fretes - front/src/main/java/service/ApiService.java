package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiService {

    private static final String BASE_URL = "http://localhost:8080/api/"; // ajuste para seu backend

    // ===========================
    // MÉTODOS GENÉRICOS
    // ===========================

    public static String get(String endpoint) throws IOException {
        URL url = new URL(BASE_URL + endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        return lerResposta(con);
    }

    public static String post(String endpoint, String jsonBody) throws IOException {
        URL url = new URL(BASE_URL + endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        try (OutputStream os = con.getOutputStream()) {
            os.write(jsonBody.getBytes());
            os.flush();
        }

        return lerResposta(con);
    }

    public static String put(String endpoint, String jsonBody) throws IOException {
        URL url = new URL(BASE_URL + endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        try (OutputStream os = con.getOutputStream()) {
            os.write(jsonBody.getBytes());
            os.flush();
        }

        return lerResposta(con);
    }

    public static String delete(String endpoint) throws IOException {
        URL url = new URL(BASE_URL + endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("DELETE");
        con.setRequestProperty("Content-Type", "application/json");

        return lerResposta(con);
    }

    // ===========================
    // MÉTODOS ESPECÍFICOS
    // ===========================

    public static String getFretes() throws IOException {
        return get("fretes");
    }

    public static String getRankingTransportadoras() throws IOException {
        return get("ranking-transportadoras");
    }

    public static String login(String usuarioJson) throws IOException {
        return post("login", usuarioJson);
    }

    // ===========================
    // MÉTODO PRIVADO DE RESPOSTA
    // ===========================

    private static String lerResposta(HttpURLConnection con) throws IOException {
        int status = con.getResponseCode();
        InputStream is = (status < HttpURLConnection.HTTP_BAD_REQUEST) ? con.getInputStream() : con.getErrorStream();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            StringBuilder response = new StringBuilder();
            String linha;
            while ((linha = br.readLine()) != null) {
                response.append(linha);
            }
            return response.toString();
        }
    }
}