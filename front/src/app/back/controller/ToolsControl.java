package app.back.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import app.back.entities.Tool;

public class ToolsControl{

    List<Tool> lTools;

    public ToolsControl(){
        lTools = new ArrayList<Tool>();
        Tool tl1 = new Tool("Pretul","Juego de 85pz Copas","Juego De Herramienta 85pz Copas Reparación Carro Moto Pretul",103990,"https://http2.mlstatic.com/D_NQ_NP_2X_984848-MCO45519587296_042021-F.webp");
        lTools.add(tl1);
        Tool tl2 = new Tool("Pretul","Taladro inalámbrico","Taladro atornillador inalámbrico de 9.5mm Pretul TALI-12P 12V + accesorios con caja de cartón 127V 60Hz",154990,"https://http2.mlstatic.com/D_NQ_NP_2X_953762-MLA40774216140_022020-F.webp");
        lTools.add(tl2);
        Tool tl3 = new Tool("Bosh","Taladro Percutor","Taladro percutor atornillador eléctrico de 13mm Bosch GSB 13 RE 650W + accesorios con caja de cartón 127V",269990,"https://http2.mlstatic.com/D_NQ_NP_2X_606344-MLA51146359144_082022-F.webp");
        lTools.add(tl3);
        
    }

    public List<Tool> searchAll(){
        return lTools;
    }

    public boolean isEmpty(){
        return lTools.isEmpty();
    }

    public Tool searchByName(String name){
        if(existByName(name)){
            Tool tmp = new Tool();
            Iterator<Tool> iter = lTools.iterator();
            while (iter.hasNext()){
                if(iter.next().getName().equals(name))
                    tmp = iter.next();
            }
            return tmp;
        }else{
            return null;
        }
        
    }

    public boolean existByName(String name){
        boolean exist = false;
        Iterator<Tool> iter = lTools.iterator();
        while (iter.hasNext()){
            if(iter.next().getName().equals(name))
                exist = true;
        }
        return exist;
    }

    public List<Tool> searchByBrand(String brand){
        if(existByBrand(brand)){
            List<Tool> listTools = new ArrayList<Tool>();
            Iterator<Tool> iter = lTools.iterator();
            while (iter.hasNext()){
                if(iter.next().getBrand().equals(brand))
                    listTools.add(iter.next());
            }
            return listTools;
        }else{
            return null;
        }
        
    }

    public boolean existByBrand(String brand){
        boolean exist = false;
        Iterator<Tool> iter = lTools.iterator();
        while (iter.hasNext()){
            if(iter.next().getBrand().equals(brand))
                exist = true;
        }
        return exist;
    }

}