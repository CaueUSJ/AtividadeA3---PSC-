
package com.biblioteca.service;

import com.biblioteca.model.Genero;
import com.biblioteca.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroService {
    
    @Autowired
    private GeneroRepository generoRepository;

    public List<Genero> listarTodos() {
        return generoRepository.findAll();
    }

    public Optional<Genero> buscarPorId(int id) {
        return generoRepository.findById(id);
    }

    public Genero salvar(Genero genero) {
        return generoRepository.save(genero);
    }

    public Genero atualizar(Integer id, Genero genero) {
        if (generoRepository.existsById(id)) {
            genero.setId_genero(id);
            return generoRepository.save(genero);
        } else {
            return null;
        }
    }

    public void deletar(int id) {
        generoRepository.deleteById(id);
    }
    
}
