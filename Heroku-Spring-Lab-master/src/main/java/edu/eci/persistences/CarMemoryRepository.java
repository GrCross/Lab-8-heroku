package edu.eci.persistences;


import edu.eci.models.Car;
import edu.eci.persistences.repositories.ICarRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;
@Component
@Qualifier("CarMemoryRepository")
public class CarMemoryRepository implements ICarRepository{

    public static List<Car> carsContainer;

    public static List<Car> getContainer(){
        if(CarMemoryRepository.carsContainer == null)
        CarMemoryRepository.carsContainer = new ArrayList<>();
        return CarMemoryRepository.carsContainer;
    }

    @Override
    public List<Car> findAll() {
        return CarMemoryRepository.getContainer();
    }

    @Override
    public Car find(String id) {
        Optional<Car> answer = CarMemoryRepository.getContainer()
            .stream().filter(u -> id.equals(u.getBrand())).findFirst();
        return answer.isPresent() ? answer.get() : null;
    }

    @Override
    public String save(Car entity) {
        CarMemoryRepository.getContainer().add(entity);
        return entity.getLicencePlate();
    }

    @Override
    public void update(Car entity) {
        CarMemoryRepository.carsContainer = CarMemoryRepository.getContainer().stream()
        .map(u -> u.getLicencePlate().equals(entity.getLicencePlate())? entity:u)
        .collect(toList());

    }

    @Override
    public void delete(Car o) {
        CarMemoryRepository.carsContainer = CarMemoryRepository.getContainer()
        .stream()
        .filter(u -> !u.getLicencePlate().equals(o.getLicencePlate()))
        .collect(toList());
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Car getUserBylicencePlate(String licencePlate) {
        return CarMemoryRepository.getContainer()
        .stream()
        .filter(u -> u.getLicencePlate().equals(licencePlate))
        .findFirst().get();
         
    }






}