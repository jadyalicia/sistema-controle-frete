package com.fretes.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fretes.service.UsuarioService;

@RestController
@RequestMapping("/login")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody Map<String, String> login) {
        String username = login.get("username");
        String senha = login.get("senha");

        boolean valido = usuarioService.validarLogin(username, senha);
        if(valido) {
            return ResponseEntity.ok("Login efetuado com sucesso");
        } else {
            return ResponseEntity.status(401).body("Usuário ou senha inválidos");
        }
    }
}