package com.workintech.zoo.validations;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class ZooKoalaValidation {
    public static void isIdValid(Integer id) {
        if (id == null || id <0 ) {
            throw new ZooException("id is not valid: "+id, HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkKoalaExist(Map<Integer, Koala> koalas, int id, boolean b) {
        if (b){
            if (!koalas.containsKey(id)){
                throw new ZooException("Kangaroo is not exist with this id=" + id,HttpStatus.NOT_FOUND);
            }
        }else{
            if (koalas.containsKey(id)){
                throw new ZooException("Already in list",HttpStatus.BAD_REQUEST);
            }
        }
    }
}
