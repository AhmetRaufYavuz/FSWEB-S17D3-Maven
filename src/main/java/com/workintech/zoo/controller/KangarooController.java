package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Gender;
import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.validations.ZooKangarooValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {

    public Map<Integer,Kangaroo> kangaroos;

    @PostConstruct
    public void init(){
        this.kangaroos = new HashMap<>();
        System.out.println("Post construct calıstı Kanguru için");
    }

    @GetMapping
    public List<Kangaroo> find(){
        return this.kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo find(@PathVariable Integer id){
        ZooKangarooValidation.isIdValid(id);
        ZooKangarooValidation.checkKangarooExist(kangaroos,id,true);
        return this.kangaroos.get(id);
    }

    @PostMapping
    public Kangaroo add (@RequestBody Kangaroo kangaroo){
        ZooKangarooValidation.checkKangarooExist(kangaroos, kangaroo.getId(), false);
        kangaroos.put(kangaroo.getId(),kangaroo);
        return kangaroos.get(kangaroo.getId());
    }

    @PutMapping("/{id}")
    public Kangaroo addById(@PathVariable Integer id, @RequestBody Kangaroo kangaroo){
        ZooKangarooValidation.isIdValid(id);
        kangaroo.setId(id);
        if (kangaroos.containsKey(id)){
            kangaroos.put(id, kangaroo);
            return kangaroos.get(id);
        }else {
            return add(kangaroo);
        }
    }

    @DeleteMapping("/{id}")
    public Kangaroo del(@PathVariable Integer id){
        ZooKangarooValidation.isIdValid(id);
        ZooKangarooValidation.checkKangarooExist(kangaroos,id,true);
        return kangaroos.remove(id);
    }


}
