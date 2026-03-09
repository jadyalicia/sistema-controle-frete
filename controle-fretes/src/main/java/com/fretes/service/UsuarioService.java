package com.fretes.service;

import com.fretes.entity.Usuario;
import com.fretes.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean validarLogin(String username, String senha) {
        Optional<Usuario> userOpt = usuarioRepository.findByUsername(username);
        if(userOpt.isPresent()) {
            Usuario user = userOpt.get();
            // Aqui você pode usar BCrypt para senha
            return user.getSenha().equals(senha);
        }
        return false;
    }

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Outros métodos conforme necessidade
}