
package com.biblioteca.controller;

import com.biblioteca.model.Usuario;
import com.biblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    //Get - Listar todos os usuarios
    @GetMapping
    public List<Usuario> listarTodos(){
        return usuarioService.listarTodos();
    }
    
    // Get - Buscar um usuario por ID
    @GetMapping("/{id}")
    public Optional<Usuario> buscarPorId(@PathVariable Integer id){
        return usuarioService.buscarPorId(id);
    }
    
    // Post - Adicionar um novo usuario
    @PostMapping
    public Usuario adicionar(@RequestBody Usuario usuario){
        return usuarioService.salvar(usuario);
    }
    
    // Put - Atualizar um usuario existente
    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Integer id, @RequestBody Usuario usuario){
        return usuarioService.atualizar(id, usuario);
    }
    
    // Delete - Remover um usuario
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id){
        usuarioService.deletar(id);
    }
    
}
