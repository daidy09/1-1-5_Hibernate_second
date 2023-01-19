package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {
    private static final Logger log = Logger.getGlobal();
    private static final UserDao userDao = new UserDaoHibernateImpl();

    public void createUsersTable() {
        log.info("create table");
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        log.info("drop table");
        userDao.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {
        log.info("save user");
        userDao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        log.info("remove user by id");
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        log.info("get all users");
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        log.info("clean table");
        userDao.cleanUsersTable();
    }
}
