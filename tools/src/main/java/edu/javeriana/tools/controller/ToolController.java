package edu.javeriana.tools.controller;

import edu.javeriana.tools.entity.Tool;
import edu.javeriana.tools.service.ToolService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para la entidad Tool.
 */
@RestController
@RequestMapping("/tool")
public class ToolController {

    @Autowired
    private ToolService toolService;

    /**
     * Método de ejemplo para verificar que el controlador está funcionando.
     *
     * @return El mensaje "Hola".
     */
    @GetMapping("/")
    public String hola(){
        return "Hola";
    }

    /**
     * Obtiene una lista de todas las herramientas.
     *
     * @return La lista de herramientas.
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/list")
    public ResponseEntity<List<Tool>> list(){
        List<Tool> list = toolService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Obtiene una herramienta por su ID.
     *
     * @param id El ID de la herramienta.
     * @return La herramienta encontrada o un mensaje de error si no existe.
     */
    @GetMapping("/detail/{id}")
    public ResponseEntity<Tool> getById(@PathVariable("id") int id){
        if(!toolService.existById(id)){
            return new ResponseEntity("No existe", HttpStatus.NOT_FOUND);
        }
        Tool tool = toolService.getOneById(id);
        return new ResponseEntity<Tool>(tool, HttpStatus.OK);
    }

    /**
     * Obtiene una herramienta por su nombre.
     *
     * @param name El nombre de la herramienta.
     * @return La herramienta encontrada o un mensaje de error si no existe.
     */
    @GetMapping("/detailname/{name}")
    public ResponseEntity<Tool> getByName(@PathVariable("name") String name){
        if(!toolService.existByName(name)){
            return new ResponseEntity("No existe", HttpStatus.NOT_FOUND);
        }
        Tool tool = toolService.getOneByName(name);
        return new ResponseEntity<Tool>(tool, HttpStatus.OK);
    }

    /**
     * Crea una nueva herramienta.
     *
     * @param tool La herramienta a crear.
     * @return Un mensaje de éxito si se crea correctamente o un mensaje de error si no se puede crear.
     */
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Tool tool){
        if(StringUtils.isBlank(tool.getName()))
            return new ResponseEntity("Se requiere el nombre", HttpStatus.BAD_REQUEST);
        if(tool.getPrice()<0)
            return new ResponseEntity("El precio debe ser mayor que cero", HttpStatus.BAD_REQUEST);
        if(toolService.existByName(tool.getName()))
            return new ResponseEntity("El producto ya existe en la base de datos", HttpStatus.BAD_REQUEST);
        Tool toolTmp = new Tool(tool.getIdBrand(), tool.getName(), tool.getDescription(), tool.getPrice(), tool.getImg(), tool.getCountry(), tool.getCities(), tool.getQuantity());
        try{
            toolService.save(toolTmp);
            return new ResponseEntity("Producto guardado", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Actualiza una herramienta existente.
     *
     * @param id   El ID de la herramienta a actualizar.
     * @param tool La herramienta con los nuevos datos.
     * @return Un mensaje de éxito si se actualiza correctamente o un mensaje de error si no se puede actualizar.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Tool tool){
        if(!toolService.existById(id))
            return new ResponseEntity("No existe", HttpStatus.NOT_FOUND);
        if(toolService.existByName(tool.getName()) && toolService.getOneByName(tool.getName()).getId() != id)
            return new ResponseEntity("La herramienta ya existe en la base de datos", HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(tool.getName()))
            return new ResponseEntity("Se requiere el nombre", HttpStatus.BAD_REQUEST);
        if((Integer) tool.getPrice() == null || tool.getPrice()<0)
            return new ResponseEntity("Se requiere el precio", HttpStatus.BAD_REQUEST);

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
            return new ResponseEntity("Producto guardado", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Elimina una herramienta por su ID.
     *
     * @param id El ID de la herramienta a eliminar.
     * @return Un mensaje de éxito si se elimina correctamente o un mensaje de error si no se puede eliminar.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!toolService.existById(id))
            return new ResponseEntity("No existe", HttpStatus.NOT_FOUND);
        try{
            toolService.delete(id);
            return new ResponseEntity("Producto eliminado", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Filtra las herramientas por nombre.
     *
     * @param name El nombre para filtrar las herramientas.
     * @return La lista de herramientas filtradas.
     */
    @CrossOrigin
    @GetMapping("/filter")
    public ResponseEntity<List<Tool>> filterByName(@RequestParam(value="name") String name){
        List<Tool> list = toolService.filterByName(name);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Filtra las herramientas por ID de marca.
     *
     * @param brandId El ID de la marca para filtrar las herramientas.
     * @return La lista de herramientas filtradas.
     */
    @CrossOrigin
    @GetMapping("/filterByBrand")
    public ResponseEntity<List<Tool>> filterByBrandId(@RequestParam(value="brandId") int brandId){
        List<Tool> list = toolService.filterByBrandId(brandId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Obtiene una lista de herramientas paginada.
     *
     * @param page      El número de página.
     * @param size      El tamaño de la página.
     * @param order     El orden de clasificación (por defecto es "id").
     * @param ascendent Indica si la clasificación es ascendente (por defecto es true).
     * @return La lista de herramientas paginada.
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/listPaged")
    public ResponseEntity<Page<Tool>> searchPaged(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "id") String order,
            @RequestParam(defaultValue = "true") boolean ascendent
    ){
        Page<Tool> toolsListPaged = toolService.findAllPaged(PageRequest.of(page, size, Sort.by(order)));

        if(!ascendent){
            toolsListPaged = toolService.findAllPaged(PageRequest.of(page, size, Sort.by(order).descending()));
        }

        return new ResponseEntity<Page<Tool>>(toolsListPaged, HttpStatus.OK);
    }
}
