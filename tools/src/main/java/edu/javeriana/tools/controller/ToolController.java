package edu.javeriana.tools.controller;

import edu.javeriana.tools.entity.Tool;
import edu.javeriana.tools.service.ToolService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tool")
public class ToolController {

    @Autowired
    private ToolService toolService;

    @GetMapping("/")
    public String hola(){
        return "Hola";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/list")
    public ResponseEntity<List<Tool>> list(){
        List<Tool> list = toolService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Tool> getById(@PathVariable("id") int id){
        if(!toolService.existById(id)){
            return new ResponseEntity("Not exist", HttpStatus.NOT_FOUND);
        }
        Tool tool = toolService.getOneById(id);
        return new ResponseEntity<Tool>(tool, HttpStatus.OK);
    }


    @GetMapping("/detailname/{name}")
    public ResponseEntity<Tool> getByName(@PathVariable("name") String name){

        if(!toolService.existByName(name)){
            return new ResponseEntity("Not exist", HttpStatus.NOT_FOUND);
        }
        Tool tool = toolService.getOneByName(name);
        return new ResponseEntity<Tool>(tool, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Tool tool){
        if(StringUtils.isBlank(tool.getName()))
            return new ResponseEntity("Name is required",HttpStatus.BAD_REQUEST);
        if(tool.getPrice()<0)
            return new ResponseEntity("Price must be greater than zero",HttpStatus.BAD_REQUEST);
        if(toolService.existByName(tool.getName()))
            return new ResponseEntity("Product exist in data base",HttpStatus.BAD_REQUEST);
        Tool toolTmp = new Tool(tool.getIdBrand(), tool.getName(), tool.getDescription(), tool.getPrice(),tool.getImg(), tool.getCountry(), tool.getCities(), tool.getQuantity());
        try{
            toolService.save(toolTmp);
            return new ResponseEntity("Product saved", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error"+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Tool tool){
        if(!toolService.existById(id))
            return new ResponseEntity("not exist", HttpStatus.NOT_FOUND);
        if(toolService.existByName(tool.getName()) && toolService.getOneByName(tool.getName()).getId() != id)
            return new ResponseEntity("tool exist in database", HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(tool.getName()))
            return new ResponseEntity("Name is required",HttpStatus.BAD_REQUEST);
        if((Integer) tool.getPrice() == null || tool.getPrice()<0)
            return new ResponseEntity("Price is required",HttpStatus.BAD_REQUEST);

        Tool toolTmp = toolService.getOneById(id);
        toolTmp.setIdBrand(tool.getIdBrand());
        toolTmp.setName(tool.getName());
        toolTmp.setPrice(tool.getPrice());
        toolTmp.setDescription(tool.getDescription());
        toolTmp.setCountry(tool.getCountry());
        toolTmp.setCities(tool.getCities());
        toolTmp.setQuantity(tool.getQuantity());
        toolTmp.setImg(tool.getImg());
        try{
            toolService.save(toolTmp);
            return new ResponseEntity("Product saved", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error"+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!toolService.existById(id))
            return new ResponseEntity("not exist", HttpStatus.NOT_FOUND);
        try{
            toolService.delete(id);
            return new ResponseEntity("Product deleted", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error"+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
