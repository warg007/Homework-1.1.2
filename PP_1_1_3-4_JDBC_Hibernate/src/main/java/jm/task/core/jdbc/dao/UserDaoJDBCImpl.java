package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }

    Connection connection = getConnection();
    PreparedStatement ps = null;

    public void createUsersTable() {

        try {
            String sql = "CREATE TABLE IF NOT EXISTS users " +
                    "(Id INT PRIMARY KEY AUTO_INCREMENT," +
                    " Name VARCHAR(20)," +
                    " lastName VARCHAR(20)," +
                    " Age INT);";
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void dropUsersTable() {

        try {
            String sql = "DROP TABLE IF EXISTS users";
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void saveUser(String name, String lastName, byte age) {

        try {
            String sql = "INSERT INTO USERS (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setInt(3, age);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        try {
            String sql = "DELETE FROM users WHERE id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, (int) id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;

    }

    public void cleanUsersTable() {

        try {
            String sql = "DELETE FROM users";
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
