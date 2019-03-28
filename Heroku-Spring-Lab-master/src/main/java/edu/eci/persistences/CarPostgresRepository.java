package edu.eci.persistences;

import java.util.List;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.eci.models.Car;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import edu.eci.models.Car;
import edu.eci.persistences.repositories.ICarRepository;

@Component
@Qualifier("CarPostgresRepository")
public class CarPostgresRepository implements ICarRepository {

    private String dbUrl2 = "jdbc:postgresql://ec2-54-83-61-142.compute-1.amazonaws.com:5432/d1fj7ertdkl1t6?user=hxggtwviliasav&password=dd0755d8678f97fdfe68b739c588607b38c7e17a2f38af4ae119a3b6db4e2b5d&sslmode=require"
    ;

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Car> findAll() {
        String query = "SELECT * FROM car";
        List<Car> cars=new ArrayList<>();

        try(Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Car car = new Car();
                car.setBrand(rs.getString("brand"));
                car.setLicencePlate(rs.getString("licencePlate"));
                cars.add(car);
            }
            return cars;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Car find(String id) {
        String query = "SELECT * FROM car WHERE licencePlate = '"+id+"';";
        Car car = null;
        try(Connection connection = dataSource. getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            car = new Car();
            car.setBrand(rs.getString("brand"));
            car.setLicencePlate(rs.getString("licencePlate"));
        }catch(Exception e ){
            System.out.println(e.getMessage());
        }
        return car;
    }

    @Override
    public String save(Car entity) {
        String licencePlate = entity.getLicencePlate();
        String brand = entity.getBrand();
        String query = "INSERT INTO car (licencePlate,brand)values('"+licencePlate+"','"+brand+"');";        
        System.out.println(query);
        try(Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            int rs = stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity.getLicencePlate();
    }

    @Override
    public void update(Car entity) {
        String licencePlate = entity.getLicencePlate();
        String brand = entity.getBrand();
        String query = "UPDATE car SET name='"+brand+"'WHERE id ='"+licencePlate+"';";
        try(Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            int rs = stmt.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Car o) {

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Car getUserBylicencePlate(String licencePlate) {
        return null;
    }


    /*@Bean
    public DataSource dataSource() throws SQLException {
        if (dbUrl2 == null || dbUrl2.isEmpty()) {
            return new HikariDataSource();
        } else {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(dbUrl2);
            return new HikariDataSource(config);
        }
    }*/
    

}