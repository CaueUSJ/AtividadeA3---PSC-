
package com.biblioteca.controller;

import com.biblioteca.model.Autor;
import com.biblioteca.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autor")
public class AutorController {
    
    @Autowired
    private AutorService autorService;
    
    //Get - Listar todos os autores
    @GetMapping
    public List<Autor> listarTodos() {
        return autorService.listarTodos();
    }
    
    //Get - Buscar um autor pelo ID
    @GetMapping("/{id}")
    public Optional<Autor> buscarPorId(@PathVariable Integer id){
        return autorService.buscarPorId(id);
    }
    
    // Post - Adicionar um novo autor
    @PostMapping
    public Autor adicionar(@RequestBody Autor autor) {
        return autorService.salvar(autor);
    }
    
    // Put - Atualizar um autor existente
    @PutMapping("/{id}")
    public Autor atualizar(@PathVariable Integer id, @RequestBody Autor autor) {
        return autorService.atualizar(id, autor);
    }
    
    // Delete - Remover um autor
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        autorService.deletar(id);
    }
    
}
