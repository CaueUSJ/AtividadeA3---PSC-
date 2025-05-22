
package com.biblioteca.service;

import com.biblioteca.model.Usuario;
import com.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public List<Usuario> listarTodos(){
        return usuarioRepository.findAll();
    }
    
    public Optional<Usuario> buscarPorId(Integer id){
        return usuarioRepository.findById(id);
    }
    
    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    
    public Usuario atualizar(Integer id, Usuario usuario){
        if (usuarioRepository.existsById(id)){
            usuario.setId_usuario(id);
            return usuarioRepository.save(usuario);
        } else {
            return null;
        }
    }
    
    public void deletar(Integer id){
        usuarioRepository.deleteById(id);
    }
}
