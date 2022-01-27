package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {}

    public void createUsersTable() {
        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS User(id mediumint not null auto_increment," +
                    "user_name CHAR(30) NOT NULL, lastname CHAR(30) NOT NULL, age INT, PRIMARY KEY(id)) ");
            System.out.println("База данных создана.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS User");
            System.out.println("База данных удалена.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection connection = Util.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO User(user_name, lastname, age) " +
                    "VALUES (?, ?, ?);")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try(Connection connection = Util.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM User WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM User");
            while(resultSet.next()) {
                String user_name = resultSet.getString("user_name");
                String lastname = resultSet.getString("lastname");
                byte age = resultSet.getByte("age");
                list.add(new User(user_name, lastname, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE User");
            System.out.println("База данных очищена.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
