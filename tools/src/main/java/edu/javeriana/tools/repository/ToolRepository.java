package edu.javeriana.tools.repository;

import edu.javeriana.tools.entity.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Integer> {
    Tool findByName(String name);

}
