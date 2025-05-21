
package com.biblioteca.service;

import com.biblioteca.model.Livro;
import com.biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    
    @Autowired
    private LivroRepository livroRepository;
    
    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }
    
    public Optional<Livro> buscarPorId(Integer id) {
        return livroRepository.findById(id);        
    }
    
    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }
    
    public Livro atualizar(Integer id, Livro livro) {
        if (livroRepository.existsById(id)){
            livro.setId_livro(id); // Ajusta o ID para garantir que é uma atualização.
            return livroRepository.save(livro);
        } else {
            return null; // Ou pode lançar uma exceção.
        }
    }
    
    public void deletar(Integer id) {
        livroRepository.deleteById(id);
    }
    
}