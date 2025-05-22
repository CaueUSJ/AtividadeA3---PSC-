
package com.biblioteca.controller;

import com.biblioteca.model.Pais;
import com.biblioteca.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pais")
public class PaisController {
    
    @Autowired
    private PaisService paisService;
    
    //Get - Listar todos os paises
    @GetMapping
    public List<Pais> listarTodos(){
        return paisService.listarTodos();
    }
    
    //Get - Buscar um pais pelo ID
    @GetMapping("/{id}")
    public Optional<Pais> buscarPorId(@PathVariable Integer id){
        return paisService.buscarPorId(id);
    }
    
    // Post - Adicionar um novo pais
    @PostMapping
    public Pais adicinar(@RequestBody Pais pais) {
        return paisService.salvar(pais);
    }
  
    // Put - Atualizar um pais existente
    @PutMapping("/{id}")
    public Pais atualizar(@PathVariable Integer id, @RequestBody Pais pais) {
        return paisService.atualizar(id, pais);
    }
    
    // Delete - Remover um pais
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id){
        paisService.deletar(id);
    }
    
}
