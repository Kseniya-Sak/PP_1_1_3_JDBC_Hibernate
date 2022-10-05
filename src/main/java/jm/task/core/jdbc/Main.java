package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Mari", "Ivanova", Byte.valueOf("" + 24));
        service.saveUser("Sveta", "Pavlova", Byte.valueOf("" + 19));
        service.saveUser("Max", "Volkov", Byte.valueOf("" + 35));
        service.saveUser("Nikita", "Morozov", Byte.valueOf("" + 11));

        List<User> users = service.getAllUsers();

        service.cleanUsersTable();
        service.dropUsersTable();

        Util.closeConnection();
    }
}
