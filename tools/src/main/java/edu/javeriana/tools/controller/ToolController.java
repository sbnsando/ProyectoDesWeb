package edu.javeriana.tools.controller;

import edu.javeriana.tools.dto.Message;
import edu.javeriana.tools.dto.ToolDto;
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

    @GetMapping("/list")
    public ResponseEntity<List<Tool>> list(){
        List<Tool> list = toolService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Tool> getById(@PathVariable("id") int id){
        if(!toolService.existById(id)){
            return new ResponseEntity(new Message("Not exist"), HttpStatus.NOT_FOUND);
        }
        Tool tool = toolService.getOneById(id);
        return new ResponseEntity<Tool>(tool, HttpStatus.OK);
    }


    @GetMapping("/detailname/{name}")
    public ResponseEntity<Tool> getByName(@PathVariable("name") String name){

        if(!toolService.existByName(name)){
            return new ResponseEntity(new Message("Not exist"), HttpStatus.NOT_FOUND);
        }
        Tool tool = toolService.getOneByName(name);
        return new ResponseEntity<Tool>(tool, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ToolDto toolDto){
        if(StringUtils.isBlank(toolDto.getName()))
            return new ResponseEntity(new Message("Name is required"),HttpStatus.BAD_REQUEST);
        if(toolDto.getPrice()<0)
            return new ResponseEntity(new Message("Price must be greater than zero"),HttpStatus.BAD_REQUEST);
        if(toolService.existByName(toolDto.getName()))
            return new ResponseEntity(new Message("Product exist in data base"),HttpStatus.BAD_REQUEST);
        Tool tool = new Tool(toolDto.getIdBrand(), toolDto.getName(), toolDto.getDescription(), toolDto.getPrice(),toolDto.getImg(), toolDto.getCountry(), toolDto.getListIdCities(), toolDto.getQuantity());
        try{
            toolService.save(tool);
            return new ResponseEntity(new Message("Product saved"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new Message("Error"+e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody ToolDto toolDto){
        if(!toolService.existById(id))
            return new ResponseEntity(new Message("not exist"), HttpStatus.NOT_FOUND);
        if(toolService.existByName(toolDto.getName()) && toolService.getOneByName(toolDto.getName()).getId() != id)
            return new ResponseEntity(new Message("tool exist in database"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(toolDto.getName()))
            return new ResponseEntity(new Message("Name is required"),HttpStatus.BAD_REQUEST);
        if((Integer) toolDto.getPrice() == null || toolDto.getPrice()<0)
            return new ResponseEntity(new Message("Price is required"),HttpStatus.BAD_REQUEST);

        Tool tool = toolService.getOneById(id);
        tool.setName(toolDto.getName());
        tool.setPrice(toolDto.getPrice());
        tool.setDescription(toolDto.getDescription());
        tool.setCountry(toolDto.getCountry());
        try{
            toolService.save(tool);
            return new ResponseEntity(new Message("Product saved"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new Message("Error"+e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!toolService.existById(id))
            return new ResponseEntity(new Message("not exist"), HttpStatus.NOT_FOUND);
        try{
            toolService.delete(id);
            return new ResponseEntity(new Message("Product deleted"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new Message("Error"+e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
