package edu.eci.controllers;

import edu.eci.models.Car;
import edu.eci.services.contracts.ICarService;
import org.springframework.http.HttpStatus;
import edu.eci.persistences.repositories.ICarRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.UUID;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private ICarService carServices;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getCar(){
        try{
            return new ResponseEntity<>(carServices.list(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createCar(@RequestBody Car car){
        try{
            
            return new ResponseEntity<>(carServices.create(car), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateCar(@RequestBody Car car){
        try{
            return new ResponseEntity<>(carServices.update(car), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @DeleteMapping("{licencePlate}")
    public ResponseEntity<?> deleteCar(@PathVariable String licencePlate){
        try{
            carServices.delete(licencePlate);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
