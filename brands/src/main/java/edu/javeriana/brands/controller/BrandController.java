package edu.javeriana.brands.controller;

import edu.javeriana.brands.service.BrandService;
import edu.javeriana.brands.entity.Brand;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * Método de ejemplo que devuelve un saludo.
     *
     * @return Un saludo.
     */
    @GetMapping("/")
    public String hola(){
        return "Hola";
    }

    /**
     * Obtiene la lista de marcas.
     *
     * @return Una respuesta HTTP con la lista de marcas y el estado OK.
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/list")
    public ResponseEntity<List<Brand>> list(){
        List<Brand> list = brandService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Obtiene los detalles de una marca por su ID.
     *
     * @param id El ID de la marca.
     * @return Una respuesta HTTP con los detalles de la marca y el estado OK si existe, o un mensaje de "No encontrado" y el estado NOT_FOUND si no existe.
     */
    @GetMapping("/detail/{id}")
    public ResponseEntity<Brand> getById(@PathVariable("id") int id){
        if(!brandService.existById(id)){
            return new ResponseEntity("No encontrado", HttpStatus.NOT_FOUND);
        }
        Brand brand = brandService.getOneById(id);
        return new ResponseEntity<Brand>(brand, HttpStatus.OK);
    }

    /**
     * Obtiene los detalles de una marca por su nombre.
     *
     * @param name El nombre de la marca.
     * @return Una respuesta HTTP con los detalles de la marca y el estado OK si existe, o un mensaje de "No encontrado" y el estado NOT_FOUND si no existe.
     */
    @GetMapping("/detailname/{name}")
    public ResponseEntity<Brand> getByName(@PathVariable("name") String name){
        if(!brandService.existByName(name)){
            return new ResponseEntity("No encontrado", HttpStatus.NOT_FOUND);
        }
        Brand brand = brandService.getOneByName(name);
        return new ResponseEntity<Brand>(brand, HttpStatus.OK);
    }

    /**
     * Crea una nueva marca.
     *
     * @param brand La marca a crear.
     * @return Una respuesta HTTP con un mensaje de éxito si la marca se crea correctamente, o un mensaje de error y el estado BAD_REQUEST si hay algún problema.
     */
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Brand brand){
        if(StringUtils.isBlank(brand.getName()))
            return new ResponseEntity("Se requiere el nombre",HttpStatus.BAD_REQUEST);
        if(brandService.existByName(brand.getName()))
            return new ResponseEntity("La marca ya existe en la base de datos",HttpStatus.BAD_REQUEST);
        Brand brandTmp = new Brand(brand.getName());
        try{
            brandService.save(brandTmp);
            return new ResponseEntity("Marca guardada", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Actualiza los detalles de una marca existente.
     *
     * @param id    El ID de la marca a actualizar.
     * @param brand La marca con los nuevos detalles.
     * @return Una respuesta HTTP con un mensaje de éxito si la marca se actualiza correctamente, o un mensaje de error y el estado BAD_REQUEST si hay algún problema.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Brand brand){
        if(!brandService.existById(id))
            return new ResponseEntity("No existe", HttpStatus.NOT_FOUND);
        if(brandService.existByName(brand.getName()) && brandService.getOneByName(brand.getName()).getId() != id)
            return new ResponseEntity("La marca ya existe en la base de datos", HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(brand.getName()))
            return new ResponseEntity("Se requiere el nombre",HttpStatus.BAD_REQUEST);

        Brand brandTmp = brandService.getOneById(id);
        brandTmp.setName(brand.getName());
        try{
            brandService.save(brandTmp);
            return new ResponseEntity("Marca guardada", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Elimina una marca existente.
     *
     * @param id El ID de la marca a eliminar.
     * @return Una respuesta HTTP con un mensaje de éxito si la marca se elimina correctamente, o un mensaje de error y el estado BAD_REQUEST si hay algún problema.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!brandService.existById(id))
            return new ResponseEntity("No existe", HttpStatus.NOT_FOUND);
        try{
            brandService.delete(id);
            return new ResponseEntity("Marca eliminada", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
