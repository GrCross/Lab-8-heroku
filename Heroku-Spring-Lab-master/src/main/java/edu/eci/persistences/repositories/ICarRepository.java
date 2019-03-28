/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.persistences.repositories;

import edu.eci.models.Car;
import edu.eci.models.User;
import java.util.UUID;
import org.springframework.stereotype.Repository;

/**
 *
 * @author daniel
 */
@Repository
public interface ICarRepository extends DAO<Car, String>{
    Car getUserBylicencePlate(String licencePlate);
}
