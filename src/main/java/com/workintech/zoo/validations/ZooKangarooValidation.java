package com.workintech.zoo.validations;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class ZooKangarooValidation {

    public static void isIdValid(Integer id) {
        if (id == null || id <0 ) {
            throw new ZooException("id is not valid: "+id, HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkKangarooExist(Map<Integer, Kangaroo> kangaroos, int id, boolean b) {
        if (b){
            if (!kangaroos.containsKey(id)){
                throw new ZooException("Kangaroo is not exist with this id=" + id,HttpStatus.NOT_FOUND);
            }
        }else{
            if (kangaroos.containsKey(id)){
                throw new ZooException("Already in list",HttpStatus.BAD_REQUEST);
            }
        }
    }
}
