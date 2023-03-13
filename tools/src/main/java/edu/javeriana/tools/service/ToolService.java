package edu.javeriana.tools.service;

import edu.javeriana.tools.entity.Tool;
import edu.javeriana.tools.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ToolService {

    @Autowired
    ToolRepository toolRepository;

    public List<Tool> list(){
        List<Tool> tools = new ArrayList<>();
        toolRepository.findAll().forEach(tools::add);
        return tools;

    }

    public Tool getOneById(int id) {

        Tool tool = null;
        tool = toolRepository.findById(id).get();

        return tool;
    }

    public Tool getOneByName(String name) {

        Tool tool = null;
        tool = toolRepository.findByName(name);

        return tool;
    }
    public void save(Tool tool){
        toolRepository.save(tool);
    }

    public void delete(int id){
        toolRepository.deleteById(id);
    }

    public boolean existById(int id){
        return toolRepository.existsById(id);
    }

    public boolean existByName(String name){
        return toolRepository.findByName(name) == null ? false : true;
    }
}
