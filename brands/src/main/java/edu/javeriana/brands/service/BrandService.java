package edu.javeriana.brands.service;

import edu.javeriana.brands.entity.Brand;
import edu.javeriana.brands.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BrandService {

    @Autowired
    BrandRepository brandRepository;

    public List<Brand> list(){
        List<Brand> brands = new ArrayList<>();
        brandRepository.findAll().forEach(brands::add);
        return brands;

    }

    public Brand getOneById(int id) {

        Brand brand = null;
        brand = brandRepository.findById(id).get();

        return brand;
    }

    public Brand getOneByName(String name) {

        Brand brand = null;
        brand = brandRepository.findByName(name);

        return brand;
    }
    public void save(Brand tool){
        brandRepository.save(tool);
    }

    public void delete(int id){
        brandRepository.deleteById(id);
    }

    public boolean existById(int id){
        return brandRepository.existsById(id);
    }

    public boolean existByName(String name){
        return brandRepository.findByName(name) == null ? false : true;
    }
}
