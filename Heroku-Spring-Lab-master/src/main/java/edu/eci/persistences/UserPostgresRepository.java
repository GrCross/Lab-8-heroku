package edu.eci.persistences;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.eci.models.User;
import edu.eci.persistences.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Qualifier("UserPostgresRepository")
public class UserPostgresRepository implements IUserRepository {

    private String dbUrl = "jdbc:postgresql://ec2-54-83-61-142.compute-1.amazonaws.com:5432/d1fj7ertdkl1t6?user=hxggtwviliasav&password=dd0755d8678f97fdfe68b739c588607b38c7e17a2f38af4ae119a3b6db4e2b5d&sslmode=require"
    ;

    @Autowired
    private DataSource dataSource;

    @Override
    public User getUserByUserName(String userName) {
        return null;
    }

    @Override
    public List<User> findAll() {
        String query = "SELECT * FROM users";
        List<User> users=new ArrayList<>();

        try(Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                User user = new User();
                user.setName(rs.getString("name"));
                user.setId(UUID.fromString(rs.getString("id")));
                users.add(user);
            }
            return users;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public User find(UUID id) {
    	 String idString = id.toString(); 
         String query = "SELECT * FROM users WHERE id ='"+idString+"';";
         System.out.println(query);
         User user = null;
         try(Connection connection = dataSource.getConnection()) {
        	 Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query);
             rs.next();
             System.out.println();
             user = new User();
             user.setName(rs.getString("name"));
             user.setId(UUID.fromString(rs.getString("id")));
             
         } catch (Exception e) {
             e.printStackTrace();
             
         }         
         return user;
    }

    @Override
    public UUID save(User entity) {
        String name = entity.getName();
        String uuid = entity.getId().toString();
        String query = "INSERT INTO users (id,name)values('"+uuid+"','"+name+"');";
        
        try(Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity.getId();
    }

    @Override
    public void update(User entity) {
    	 String name = entity.getName();
         String uuid = entity.getId().toString();
         String query = "UPDATE users SET name='"+name+"'WHERE id ='"+uuid+"';";
         try(Connection connection = dataSource.getConnection()) {
             System.out.println(name);
        	 Statement stmt = connection.createStatement();
             int rs = stmt.executeUpdate(query);

         } catch (Exception e) {
             e.printStackTrace();
         }
    }

    @Override
    public void delete(User o) {
        String uuid = o.getId().toString();
        String query = "DELETE FROM users WHERE id='"+uuid+"';";
        try(Connection connection = dataSource.getConnection()){
            Statement stmt = connection.createStatement();
            int a = stmt.executeUpdate(query);
        }catch (Exception e) {
            
            

        }
    }

    @Override
    public void remove(Long id) {

    }

    @Bean
    public DataSource dataSource() throws SQLException {
        if (dbUrl == null || dbUrl.isEmpty()) {
            return new HikariDataSource();
        } else {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(dbUrl);
            return new HikariDataSource(config);
        }
    }
}
