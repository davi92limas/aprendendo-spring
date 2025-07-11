package com.davi.aprendendospring.business;

import com.davi.aprendendospring.infrastructure.entity.Usuario;
import com.davi.aprendendospring.infrastructure.exceptions.ConflitExeption;
import com.davi.aprendendospring.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
  
  private final UsuarioRepository usuarioRepository;
  private final PasswordEncoder passwordEncoder;
  
  public Usuario salvarUsuario(Usuario usuario) {
    try {
      emailExiste(usuario.getEmail());
      usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
      return usuarioRepository.save(usuario);
      
    } catch (ConflitExeption e) {
      throw new ConflitExeption("Email já cadastrado no sistema" + e.getCause());
    }
  }
  
  public void emailExiste(String email) {
    try {
      boolean existe = verificarEmailExiste(email);
      if (existe) {
        throw new ConflitExeption("Email já cadastrado no sistema" + email);
      }
    } catch (ConflitExeption e) {
      throw new ConflitExeption("Email já cadastrado no sistema" + e.getCause());
    }
  }
  
  public boolean verificarEmailExiste(String email) {
    return usuarioRepository.existsByEmail(email);
  }
  
  public Usuario buscarPorEmail(String email) {
    return usuarioRepository.findByEmail(email).orElseThrow(() -> new ConflitExeption("Email nao encontrado" + email));
  }
  
  public void deletar(String email) {
    usuarioRepository.deleteByEmail(email);
  }
  
}
