package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.Callable;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/my_db?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345";
    private static SessionFactory factory;

    static {
        Properties prop= new Properties();
        prop.setProperty("hibernate.connection.url", URL);
        prop.setProperty("dialect", "org.hibernate.dialect.MySQL8Dialect");
        prop.setProperty("hibernate.connection.username", USERNAME);
        prop.setProperty("hibernate.connection.password", PASSWORD);
        prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        prop.setProperty("show_sql", "true");
        factory = new Configuration()
                .addProperties(prop)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }
    public static void close() {
        factory.close();
    }


//    private static Connection connection;
//    private static Util util;
//
//    private Util() {
//        try {
//            if (connection == null || connection.isClosed()) {
//                Class.forName("com.mysql.cj.jdbc.Driver");
//                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static Connection getConnection() {
//        if (util == null) {
//            util = new Util();
//        }
//        return connection;
//    }
//    public static void close() {
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
