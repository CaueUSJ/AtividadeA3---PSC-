
package com.biblioteca.controller;

import com.biblioteca.model.Emprestimo;
import com.biblioteca.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {
    
    @Autowired
    private EmprestimoService emprestimoService;
    
    //Get - Listar todos os emprestimos
    @GetMapping
    public List<Emprestimo> listarTodos(){
        return emprestimoService.listarTodos();
    }
    
    //Get - Buscar um emprestimo pelo ID
    @GetMapping("/{id}")
    public Optional<Emprestimo> buscarPorId(@PathVariable Integer id){
        return emprestimoService.buscarPorId(id);
    }   
    
    // Post - Adicionar um novo emprestimo
    @PostMapping
    public Emprestimo adicionar(@RequestBody Emprestimo emprestimo){
        return emprestimoService.salvar(emprestimo);
    }
    
    // Put - Atualizar um emprestimo existente
    @PutMapping("/{id}")
    public Emprestimo atualizar(@PathVariable Integer id, @RequestBody Emprestimo emprestimo){
        return emprestimoService.atualizar(id, emprestimo);
    }
        
    // Delete - Remover um emprestimo
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id){
        emprestimoService.deletar(id);
    }
}
