package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement st = connection.createStatement()) {
            String sql = "CREATE TABLE user(" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(45), " +
                    "lastName VARCHAR(45), " +
                    "age INT, " +
                    "PRIMARY KEY (id)" +
                    ");";
            st.executeUpdate(sql);
        } catch (SQLException e) {
        }
    }

        public void dropUsersTable() {
        try (Statement st = connection.createStatement()){
            st.executeUpdate("DROP TABLE user");
        } catch (SQLException e) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement st = connection.createStatement()){
            st.executeUpdate("INSERT INTO user(name, lastName, age) VALUES('" + name + "', '" +
                                  lastName + "', " + age + ");");
            System.out.println("User с именем – "+ name + " добавлен в базу данных ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement st = connection.createStatement()){
            st.executeUpdate("DELETE FROM user WHERE id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement st = connection.createStatement()){
            ResultSet rs = st.executeQuery("SELECT * FROM user");
            while (rs.next()) {
                User user = new User(rs.getString("name"), rs.getString("lastName"),
                        Byte.valueOf("" + rs.getInt("age")));
                user.setId(rs.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement st = connection.createStatement()){
            st.executeUpdate("TRUNCATE user;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
