
package com.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.biblioteca.model.Pais;

public interface PaisRepository extends JpaRepository<Pais, Integer>{
    
}
