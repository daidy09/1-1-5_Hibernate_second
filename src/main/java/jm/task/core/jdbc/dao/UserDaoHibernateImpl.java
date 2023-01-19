package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoHibernateImpl implements UserDao {
    private static final Logger logger = Logger.getLogger("INFO LOGGER");
    SessionFactory sessionFactory;
    {
        try {
            sessionFactory = Util.getSessionFactory();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users" +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "name varchar(255) NOT NULL," +
                    "lastName varchar(255) NOT NULL," +
                    " age TINYINT NOT NULL" + ")").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.getCurrentSession()) {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            logger.log(Level.INFO, "user с именем {0} добавлен в базу", new String[]{user.getName()});
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            User user = new User();
            user.setId(id);
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<User> user = session.createQuery("FROM User").getResultList();
            for (User u : user) {
                System.out.println(u);
            }
            session.getTransaction().commit();
            return user;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE  users").executeUpdate();
            session.getTransaction().commit();
        }
    }
}