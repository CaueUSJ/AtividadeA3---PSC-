
package com.biblioteca.controller;

import com.biblioteca.model.Editora;
import com.biblioteca.service.EditoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/editora")
public class EditoraController {
    
    @Autowired
    private EditoraService editoraService;
    
    //Get - Listar todos as editora
    @GetMapping
    public List<Editora> listarTodos() {
        return editoraService.listarTodos();
    }
    
    //Get - Buscar uma editora pelo ID
    @GetMapping("/{id}")
    public Optional<Editora> buscarPorId(@PathVariable Integer id){
        return editoraService.buscarPorId(id);
    }
    
    // Post - Adicionar uma nova editora
    @PostMapping
    public Editora adicionar(@RequestBody Editora editora) {
        return editoraService.salvar(editora);
    }
    
    // Put - Atualizar uma editora existente
    @PutMapping("/{id}")
    public Editora atualizar(@PathVariable Integer id, @RequestBody Editora editora) {
        return editoraService.atualizar(id, editora);
    }
    
    // Delete - Remover uma editora
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        editoraService.deletar(id);
    }
    
}