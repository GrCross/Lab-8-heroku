/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.services;

import edu.eci.models.Car;
import edu.eci.persistences.repositories.ICarRepository;
import edu.eci.services.contracts.ICarService;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author daniel
 */
@Component
public class CarService implements ICarService {
    
    @Autowired
    @Qualifier("CarMemoryRepository")
    ICarRepository CarRepository;


    @Override
    public List<Car> list(){
        return CarRepository.findAll();
    }

    @Override
    public Car create(Car car) {
        if(null == car.getLicencePlate()){
            System.out.println(car.getBrand());
            throw new RuntimeException("Id invalid");
            
        }else if(CarRepository.find(car.getLicencePlate()) != null){
            Car caraaa = CarRepository.find(car.getLicencePlate());
            System.out.println(car.getBrand()+"aaaaaaaaaaaaaaaaaaaaa");
            System.out.println(CarRepository.find(car.getLicencePlate()));
            throw new RuntimeException("The user exists");
        }else
            CarRepository.save(car);
        return car;
    }

    @Override
    public Car get(String licencePlate) {
        return CarRepository.getUserBylicencePlate(licencePlate);
    }

    @Override
    public Car update(Car entity) {
        //System.out.println(entity.getBrand());
        if (CarRepository.find(entity.getLicencePlate()) == null){
            this.create(entity);
        }else {
            CarRepository.update(entity);
        }
        return entity;
    }

    @Override
    public void delete(String id) {
        if (CarRepository.find(id) == null){
            throw new RuntimeException("The car dont exist");
        }else {
            CarRepository.delete(CarRepository.find(id));
        }
    }

  
    
}
