package com.biblioteca.controller;

import com.biblioteca.model.Leitor;
import com.biblioteca.service.LeitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/leitor")
public class LeitorController {
    
    @Autowired
    private LeitorService leitorService;
    
    //Get - Listar todos os leitores
    @GetMapping
    public List<Leitor> listarTodos(){
        return leitorService.listarTodos();
    }
    
    // Get - Buscar um leitor por ID
    @GetMapping("/{id}")
    public Optional<Leitor> buscarPorId(@PathVariable Integer id){
        return leitorService.buscarPorId(id);
    }
    
    // Post - Adicionar um novo leitor
    @PostMapping("/cadastro")
    public Leitor cadastro(@RequestBody Leitor leitor){
        return leitorService.salvar(leitor);
    }
    
    
    // Put - Atualizar um usuario existente
    @PutMapping("/{id}")
    public Leitor atualizar(@PathVariable Integer id, @RequestBody Leitor leitor){
        return leitorService.atualizar(id, leitor);
    }
    
    // Delete - Remover um usuario
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id){
        leitorService.deletar(id);
    }
    
}
