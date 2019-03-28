/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.services.contracts;

import edu.eci.models.Car;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author daniel
 */
@Service
public interface ICarService {
    List<Car> list();
    Car create(Car car);
    Car get(String licencePlate);
    Car update(Car entity);
    void delete(String id);
}

