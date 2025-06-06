
package com.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.biblioteca.model.Leitor;

public interface LeitorRepository extends JpaRepository<Leitor, Integer> {
    
}
