package app.back.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import app.back.entities.Brand;

public class BrandsControl {

    List<Brand> lBrands;

    public BrandsControl(){
        lBrands = new ArrayList<Brand>();
        Brand br = new Brand("Pretul");
        lBrands.add(br);
        br.setName("DeWalt");
        lBrands.add(br);
        br.setName("Black & Decker");
        lBrands.add(br);
        br.setName("Bosh");
        lBrands.add(br);
    }

    public boolean isEmpty(){
        return lBrands.isEmpty();
    }

    public List<Brand> searchAll(){
        return lBrands;
    }

    public boolean existByName(String name){
        boolean exist = false;
        Iterator<Brand> iter = lBrands.iterator();
        while (iter.hasNext()){
            if(iter.next().getName().equals(name))
                exist = true;
        }
        return exist;
    }

    public Brand searchByName(String name){

        if(existByName(name)){
            Brand br = new Brand();
            Iterator<Brand> iter = lBrands.iterator();
            while (iter.hasNext()){
                if(iter.next().getName().equals(name))
                    br = iter.next();
            }
            return br;
        }else{
            return null;
        }
        
    }

} 
