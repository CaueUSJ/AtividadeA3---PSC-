
package com.biblioteca.service;

import com.biblioteca.model.Editora;
import com.biblioteca.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditoraService {
    
    @Autowired
    private EditoraRepository editoraRepository;

    public List<Editora> listarTodos() {
        return editoraRepository.findAll();
    }

    public Optional<Editora> buscarPorId(int id) {
        return editoraRepository.findById(id);
    }

    public Editora salvar(Editora editora) {
        return editoraRepository.save(editora);
    }

    public Editora atualizar(Integer id, Editora editora) {
        if (editoraRepository.existsById(id)) {
            editora.setId_editora(id);
            return editoraRepository.save(editora);
        } else {
            return null;
        }
    }

    public void deletar(int id) {
        editoraRepository.deleteById(id);
    }
    
}