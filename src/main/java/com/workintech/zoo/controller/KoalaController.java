package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Gender;
import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.validations.ZooKoalaValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {

    private Map<Integer, Koala> koalas;

    @PostConstruct
    public void init(){
        this.koalas = new HashMap<>();
        System.out.println("Post construct calıstı Koala için");
    }

    @GetMapping
    public List<Koala> getAll(){
        return this.koalas.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Koala getById(@PathVariable Integer id){
        ZooKoalaValidation.isIdValid(id);
        ZooKoalaValidation.checkKoalaExist(koalas,id,true);
        return koalas.get(id);
    }

    @PostMapping
    public Koala add(@RequestBody Koala koala){
        ZooKoalaValidation.checkKoalaExist(koalas, koala.getId(), false);
        koalas.put(koala.getId(), koala);
        return koalas.get(koala.getId());
    }

    @PutMapping("/{id}")
    public Koala addById (@PathVariable Integer id, @RequestBody Koala koala){
        ZooKoalaValidation.isIdValid(id);
        koala.setId(id);
        if(koalas.containsKey(id)){
            koalas.put(id,koala);
            return koalas.get(id);
        }else{
            return add(koala);
        }
    }

    @DeleteMapping("/{id}")
    public Koala del(@PathVariable Integer id){
        ZooKoalaValidation.isIdValid(id);
        ZooKoalaValidation.checkKoalaExist(koalas,id,true);
        return koalas.remove(id);
    }



}
