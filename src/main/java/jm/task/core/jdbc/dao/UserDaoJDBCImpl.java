//package jm.task.core.jdbc.dao;
//
//import jm.task.core.jdbc.model.User;
//import jm.task.core.jdbc.util.Util;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDaoJDBCImpl implements UserDao {
//    private Connection connection = Util.getConnection();
//
//    public UserDaoJDBCImpl() {
//    }
//
//    public void createUsersTable() {
//        try (Statement st = connection.createStatement()) {
//            String sql = "CREATE TABLE IF NOT EXISTS user(" +
//                    "id INT NOT NULL AUTO_INCREMENT," +
//                    "name VARCHAR(45), " +
//                    "lastName VARCHAR(45), " +
//                    "age INT, " +
//                    "PRIMARY KEY (id)" +
//                    ");";
//            st.executeUpdate(sql);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//        public void dropUsersTable() {
//        try (Statement st = connection.createStatement()){
//            st.executeUpdate("DROP TABLE IF EXISTS user");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void saveUser(String name, String lastName, byte age) {
//        String sql = "INSERT INTO user (name, lastName, age) VALUES(?, ?, ?);";
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            preparedStatement.setString(1, name);
//            preparedStatement.setString(2, lastName);
//            preparedStatement.setInt(3, age);
//            preparedStatement.executeUpdate();
//            System.out.println("User с именем – "+ name + " добавлен в базу данных ");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void removeUserById(long id) {
//        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id = ?;")) {
//            preparedStatement.setLong(1, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<User> getAllUsers() {
//        List<User> users = new ArrayList<>();
//        try (Statement st = connection.createStatement()){
//            ResultSet rs = st.executeQuery("SELECT * FROM user");
//            while (rs.next()) {
//                User user = new User(rs.getString("name"), rs.getString("lastName"),
//                        rs.getByte("age"));
//                user.setId(rs.getLong("id"));
//                users.add(user);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return users;
//    }
//
//    public void cleanUsersTable() {
//        try (Statement st = connection.createStatement()){
//            st.executeUpdate("TRUNCATE user;");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
