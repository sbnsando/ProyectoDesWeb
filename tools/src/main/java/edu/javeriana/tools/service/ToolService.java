package edu.javeriana.tools.service;

import edu.javeriana.tools.entity.Tool;
import edu.javeriana.tools.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para la entidad Tool.
 */
@Service
@Transactional
public class ToolService {

    @Autowired
    ToolRepository toolRepository;

    /**
     * Filtra las herramientas por nombre.
     *
     * @param name El nombre a filtrar.
     * @return La lista de herramientas que coinciden con el nombre.
     */
    public List<Tool> filterByName(String name){
        String lowerCName = name.toLowerCase();
        List<Tool> tools = new ArrayList<>();
        toolRepository.findAll().forEach(tools::add);
        tools = tools.stream()
                .filter(t -> t.getName().toLowerCase().contains(lowerCName))
                .collect(Collectors.toList());
        return tools;
    }

    /**
     * Filtra las herramientas por ID de marca.
     *
     * @param brandId El ID de la marca a filtrar.
     * @return La lista de herramientas que pertenecen a la marca especificada.
     */
    public List<Tool> filterByBrandId(int brandId){
        List<Tool> tools = new ArrayList<>();
        toolRepository.findAll().forEach(tools::add);
        tools = tools.stream()
                .filter(t -> t.getIdBrand() == brandId)
                .collect(Collectors.toList());
        return tools;
    }

    /**
     * Obtiene una lista de todas las herramientas.
     *
     * @return La lista de herramientas.
     */
    public List<Tool> list(){
        List<Tool> tools = new ArrayList<>();
        toolRepository.findAll().forEach(tools::add);
        return tools;
    }

    /**
     * Obtiene una herramienta por su ID.
     *
     * @param id El ID de la herramienta.
     * @return La herramienta encontrada o null si no se encuentra ninguna coincidencia.
     */
    public Tool getOneById(int id) {
        Tool tool = null;
        tool = toolRepository.findById(id).get();
        return tool;
    }

    /**
     * Obtiene una herramienta por su nombre.
     *
     * @param name El nombre de la herramienta.
     * @return La herramienta encontrada o null si no se encuentra ninguna coincidencia.
     */
    public Tool getOneByName(String name) {
        Tool tool = null;
        tool = toolRepository.findByName(name);
        return tool;
    }

    /**
     * Guarda una herramienta.
     *
     * @param tool La herramienta a guardar.
     */
    public void save(Tool tool){
        toolRepository.save(tool);
    }

    /**
     * Elimina una herramienta por su ID.
     *
     * @param id El ID de la herramienta a eliminar.
     */
    public void delete(int id){
        toolRepository.deleteById(id);
    }

    /**
     * Verifica si una herramienta existe por su ID.
     *
     * @param id El ID de la herramienta.
     * @return true si la herramienta existe, false de lo contrario.
     */
    public boolean existById(int id){
        return toolRepository.existsById(id);
    }

    /**
     * Verifica si una herramienta existe por su nombre.
     *
     * @param name El nombre de la herramienta.
     * @return true si la herramienta existe, false de lo contrario.
     */
    public boolean existByName(String name){
        return toolRepository.findByName(name) == null ? false : true;
    }

    /**
     * Obtiene una página de herramientas.
     *
     * @param pageable Información de paginación y ordenamiento.
     * @return La página de herramientas.
     */
    public Page<Tool> findAllPaged(Pageable pageable){
        return toolRepository.findAll(pageable);
    }
}
