
package com.biblioteca.service;

import com.biblioteca.model.Leitor;
import com.biblioteca.repository.LeitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeitorService {
    
    @Autowired
    private LeitorRepository leitorRepository;
    
    public List<Leitor> listarTodos(){
        return leitorRepository.findAll();
    }
    
    public Optional<Leitor> buscarPorId(Integer id){
        return leitorRepository.findById(id);
    }
    
    public Leitor salvar(Leitor leitor){
        return leitorRepository.save(leitor);
    }
    
    public Leitor atualizar(Integer id, Leitor leitor){
        if (leitorRepository.existsById(id)){
            leitor.setId_usuario(id);
            return leitorRepository.save(leitor);
        } else {
            return null;
        }
    }
    
    public void deletar(Integer id){
        leitorRepository.deleteById(id);
    }
    
}
