
package com.biblioteca.service;

import com.biblioteca.model.Emprestimo;
import com.biblioteca.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {
    
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    
    public List<Emprestimo> listarTodos(){
        return emprestimoRepository.findAll();
    }
    
    public Optional<Emprestimo> buscarPorId(Integer id){
        return emprestimoRepository.findById(id);
    }
    
    public Emprestimo salvar(Emprestimo emprestimo){
        return emprestimoRepository.save(emprestimo);
    }
    
    public Emprestimo atualizar(Integer id, Emprestimo emprestimo){
        if (emprestimoRepository.existsById(id)) {
            emprestimo.setId_emprestimo(id);
            return emprestimoRepository.save(emprestimo);
        } else {
            return null;
        }
    }
}
