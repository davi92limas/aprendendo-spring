package com.davi.aprendendospring.controller;

import com.davi.aprendendospring.business.UsuarioService;
import com.davi.aprendendospring.controller.dtos.UsuarioDTO;
import com.davi.aprendendospring.infrastructure.entity.Usuario;
import com.davi.aprendendospring.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
  
  private final UsuarioService usuarioService;
  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;
  
  @PostMapping
  public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario) {
    return ResponseEntity.ok(usuarioService.salvarUsuario(usuario));
  }
  
  @PostMapping("/login")
  public String login(@RequestBody UsuarioDTO usuarioDTO) {
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(), usuarioDTO.getSenha()));
    
    return jwtUtil.generateToken(authentication.getName());
    
  }
  
  @GetMapping
  public ResponseEntity<Usuario> buscarPorEmail(@RequestParam("email") String email) {
    return ResponseEntity.ok(usuarioService.buscarPorEmail(email));
  }
  
  @DeleteMapping("/{email}")
  public ResponseEntity<Void> deletar(@PathVariable String email) {
    usuarioService.deletar(email);
    return ResponseEntity.ok().build();
  }
  
}
