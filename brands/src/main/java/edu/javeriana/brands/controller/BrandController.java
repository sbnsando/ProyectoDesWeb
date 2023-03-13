package edu.javeriana.brands.controller;

import edu.javeriana.brands.service.BrandService;
import edu.javeriana.brands.dto.Message;
import edu.javeriana.brands.dto.BrandDto;
import edu.javeriana.brands.entity.Brand;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/")
    public String hola(){
        return "Hola";
    }

    @GetMapping("/list")
    public ResponseEntity<List<Brand>> list(){
        List<Brand> list = brandService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Brand> getById(@PathVariable("id") int id){
        if(!brandService.existById(id)){
            return new ResponseEntity(new Message("Not exist"), HttpStatus.NOT_FOUND);
        }
        Brand brand = brandService.getOneById(id);
        return new ResponseEntity<Brand>(brand, HttpStatus.OK);
    }


    @GetMapping("/detailname/{name}")
    public ResponseEntity<Brand> getByName(@PathVariable("name") String name){

        if(!brandService.existByName(name)){
            return new ResponseEntity(new Message("Not exist"), HttpStatus.NOT_FOUND);
        }
        Brand Brand = brandService.getOneByName(name);
        return new ResponseEntity<Brand>(Brand, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody BrandDto brandDto){
        if(StringUtils.isBlank(brandDto.getName()))
            return new ResponseEntity(new Message("Name is required"),HttpStatus.BAD_REQUEST);
        if(brandService.existByName(brandDto.getName()))
            return new ResponseEntity(new Message("Brand exist in data base"),HttpStatus.BAD_REQUEST);
        Brand Brand = new Brand(brandDto.getName());
        try{
            brandService.save(Brand);
            return new ResponseEntity(new Message("Product saved"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new Message("Error"+e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody BrandDto brandDto){
        if(!brandService.existById(id))
            return new ResponseEntity(new Message("not exist"), HttpStatus.NOT_FOUND);
        if(brandService.existByName(brandDto.getName()) && brandService.getOneByName(brandDto.getName()).getId() != id)
            return new ResponseEntity(new Message("Brand exist in database"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(brandDto.getName()))
            return new ResponseEntity(new Message("Name is required"),HttpStatus.BAD_REQUEST);

        Brand brand = brandService.getOneById(id);
        brand.setName(brandDto.getName());;
        try{
            brandService.save(brand);
            return new ResponseEntity(new Message("Product saved"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new Message("Error"+e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!brandService.existById(id))
            return new ResponseEntity(new Message("not exist"), HttpStatus.NOT_FOUND);
        try{
            brandService.delete(id);
            return new ResponseEntity(new Message("Product deleted"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new Message("Error"+e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
