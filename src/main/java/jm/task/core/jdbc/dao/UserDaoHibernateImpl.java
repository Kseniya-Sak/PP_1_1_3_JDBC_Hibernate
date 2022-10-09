package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory = Util.getSessionFactory();
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        String sql = "CREATE TABLE IF NOT EXISTS user(" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(45), " +
                    "lastName VARCHAR(45), " +
                    "age INT, " +
                    "PRIMARY KEY (id)" +
                    ")";
        session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new User(name, lastName, age));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(session.get(User.class, id));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        users = session.createSQLQuery("SELECT * FROM user").addEntity(User.class).list();
        session.getTransaction().commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("TRUNCATE user").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
