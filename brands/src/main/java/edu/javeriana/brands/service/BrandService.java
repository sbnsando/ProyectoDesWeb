package edu.javeriana.brands.service;

import edu.javeriana.brands.entity.Brand;
import edu.javeriana.brands.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para la entidad Brand.
 */
@Service
@Transactional
public class BrandService {

    @Autowired
    BrandRepository brandRepository;

    /**
     * Obtiene una lista de todas las marcas.
     *
     * @return La lista de marcas.
     */
    public List<Brand> list(){
        List<Brand> brands = new ArrayList<>();
        brandRepository.findAll().forEach(brands::add);
        return brands;
    }

    /**
     * Obtiene una marca por su ID.
     *
     * @param id El ID de la marca.
     * @return La marca encontrada o null si no se encuentra ninguna coincidencia.
     */
    public Brand getOneById(int id) {
        Optional<Brand> brandOptional = brandRepository.findById(id);
        return brandOptional.orElse(null);
    }


    /**
     * Obtiene una marca por su nombre.
     *
     * @param name El nombre de la marca.
     * @return La marca encontrada o null si no se encuentra ninguna coincidencia.
     */
    public Brand getOneByName(String name) {
        Brand brand = null;
        brand = brandRepository.findByName(name);
        return brand;
    }

    /**
     * Guarda una marca.
     *
     * @param brand La marca a guardar.
     */
    public void save(Brand brand){
        brandRepository.save(brand);
    }

    /**
     * Elimina una marca por su ID.
     *
     * @param id El ID de la marca a eliminar.
     */
    public void delete(int id){
        brandRepository.deleteById(id);
    }

    /**
     * Verifica si una marca existe por su ID.
     *
     * @param id El ID de la marca.
     * @return true si la marca existe, false de lo contrario.
     */
    public boolean existById(int id){
        return brandRepository.existsById(id);
    }

    /**
     * Verifica si una marca existe por su nombre.
     *
     * @param name El nombre de la marca.
     * @return true si la marca existe, false de lo contrario.
     */
    public boolean existByName(String name){
        return brandRepository.findByName(name) == null ? false : true;
    }
}
