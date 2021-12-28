package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        // реализуйте алгоритм здесь

        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();

        userDaoHibernate.createUsersTable();

        User user1 = new User("Ivan", "Ivanov", (byte) 22);
        User user2 = new User("Jenny", "Petrova", (byte) 99);
        User user3 = new User("Zaur", "Zaurov", (byte) 35);
        User user4 = new User("Ann", "Weins", (byte) 18);
        User user5 = new User("Eliza", "Bornth", (byte)45);

        userDaoHibernate.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userDaoHibernate.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userDaoHibernate.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userDaoHibernate.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        userDaoHibernate.saveUser(user5.getName(), user5.getLastName(), user5.getAge());

        System.out.println(userDaoHibernate.getAllUsers());
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();
    }
}