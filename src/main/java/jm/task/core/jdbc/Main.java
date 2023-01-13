package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = Util.getSessionFactory();

        try {
            Session session = sessionFactory.getCurrentSession();

//Сессия по созданию таблицы
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users" +
                            "(id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                            "name varchar(255) NOT NULL," +
                            "lastName varchar(255) NOT NULL," +
                            " age TINYINT NOT NULL" + ")").executeUpdate();

            session.getTransaction().commit();

// Сессия по добавлению данных в таблицу
            session = sessionFactory.getCurrentSession();
            User user1 = new User("Bruce", "Lee", (byte) 33);
            User user2 = new User("Mike", "Tyson", (byte) 54);
            User user3 = new User("Chuck", "Norris", (byte) 99);
            User user4 = new User("Jackie", "Chan", (byte) 34);
            session.beginTransaction();
            session.save(user1);
            System.out.println("User с именем – " + user1.getName() +  " добавлен в базу данных");
            session.save(user2);
            System.out.println("User с именем – " + user2.getName() +  " добавлен в базу данных");
            session.save(user3);
            System.out.println("User с именем – " + user3.getName() +  " добавлен в базу данных");
            session.save(user4);
            System.out.println("User с именем – " + user4.getName() +  " добавлен в базу данных");
            session.getTransaction().commit();

// Сессия по получению всех работников
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            List<User> user = session.createQuery("FROM User ")
                            .getResultList();
            for (User u: user) {
                System.out.println(u);
            }
            session.getTransaction().commit();

// //Сессия по очистке таблицы
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("DELETE FROM  User").executeUpdate();
            session.getTransaction().commit();

//Сессия по удалению таблицы
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();


        }
        finally {
            sessionFactory.close();
        }
    }
}