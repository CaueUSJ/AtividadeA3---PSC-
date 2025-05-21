
package com.biblioteca.service;

import com.biblioteca.model.Pais;
import com.biblioteca.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaisService {
    
    @Autowired
    private PaisRepository paisRepository;
    
    public List<Pais> listarTodos(){
        return paisRepository.findAll();
    }
    
    public Optional<Pais> buscarPorId(Integer id){
        return paisRepository.findById(id);
    }
    
    public Pais salvar(Pais pais){
        return paisRepository.save(pais);
    }
    
    public Pais atualizar(Integer id, Pais pais) {
        if (paisRepository.existsById(id)){
            pais.setId_pais(id);
            return paisRepository.save(pais);
        } else {
            return null;
        }
    }
    
    public void deletar (Integer id){
        paisRepository.deleteById(id);
    }
    
}
