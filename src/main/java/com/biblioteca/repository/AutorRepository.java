
package com.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.biblioteca.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Integer> {
    
}
