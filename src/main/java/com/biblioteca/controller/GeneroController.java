
package com.biblioteca.controller;

import com.biblioteca.model.Genero;
import com.biblioteca.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/genero")
public class GeneroController {
    
    @Autowired
    private GeneroService generoService;
    
    //Get - Listar todos os generos
    @GetMapping
    public List<Genero> listarTodos() {
        return generoService.listarTodos();
    }
    
    //Get - Buscar um genero pelo ID
    @GetMapping("/{id}")
    public Optional<Genero> buscarPorId(@PathVariable Integer id){
        return generoService.buscarPorId(id);
    }
    
    // Post - Adicionar um novo genero
    @PostMapping
    public Genero adicionar(@RequestBody Genero genero) {
        return generoService.salvar(genero);
    }
    
    // Put - Atualizar um genero existente
    @PutMapping("/{id}")
    public Genero atualizar(@PathVariable Integer id, @RequestBody Genero genero) {
        return generoService.atualizar(id, genero);
    }
    
    // Delete - Remover um genero
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        generoService.deletar(id);
    }
    
}
