package edu.javeriana.brands.repository;

import edu.javeriana.brands.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Brand.
 */
@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

    /**
     * Busca una marca por su nombre.
     *
     * @param name El nombre de la marca a buscar.
     * @return La marca encontrada o null si no se encuentra ninguna coincidencia.
     */
    Brand findByName(String name);

}
