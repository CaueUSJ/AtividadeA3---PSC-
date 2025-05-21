
package com.biblioteca.controller;

import com.biblioteca.model.Livro;
import com.biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livro")
public class LivroController {
    
    @Autowired
    private LivroService livroService;
    
    //Get - Listar todos os livros
    @GetMapping
    public List<Livro> listarTodos() {
        return livroService.listarTodos();
    }
    
    //Get - Buscar um livro pelo ID
    @GetMapping("/{id}")
    public Optional<Livro> buscarPorId(@PathVariable Integer id){
        return livroService.buscarPorId(id);
    }
    
    // Post - Adicionar um novo livro
    @PostMapping
    public Livro adicionar(@RequestBody Livro livro) {
        return livroService.salvar(livro);
    }
    
    // Put - Atualizar um livro existente
    @PutMapping("/{id}")
    public Livro atualizar(@PathVariable Integer id, @RequestBody Livro livro) {
        return livroService.atualizar(id, livro);
    }
    
    // Delete - Remover um livro
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        livroService.deletar(id);
    }
    
}
