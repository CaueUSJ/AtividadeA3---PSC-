
package com.biblioteca.service;

import com.biblioteca.model.Autor;
import com.biblioteca.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {
    
    @Autowired
    private AutorRepository autorRepository;
    
    public List<Autor> listarTodos() {
        return autorRepository.findAll();
    }
    
    public Optional<Autor> buscarPorId(Integer id){
        return autorRepository.findById(id);
    }
    
    public Autor salvar(Autor autor) {
        return autorRepository.save(autor);
    }
    
    public Autor atualizar(Integer id, Autor autor) {
        if (autorRepository.existsById(id)) {
            autor.setId_autor(id);
            return autorRepository.save(autor);
        } else {
            return null;
        }
    }
    
    public void deletar(Integer id) {
        autorRepository.deleteById(id);
    }
    
}
