package edu.javeriana.tools.repository;

import edu.javeriana.tools.entity.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Tool.
 */
@Repository
public interface ToolRepository extends JpaRepository<Tool, Integer> {

    /**
     * Busca una herramienta por su nombre.
     *
     * @param name El nombre de la herramienta a buscar.
     * @return La herramienta encontrada o null si no se encuentra ninguna coincidencia.
     */
    Tool findByName(String name);

}
