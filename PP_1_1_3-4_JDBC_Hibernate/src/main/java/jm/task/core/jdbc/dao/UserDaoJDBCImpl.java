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
import java.util.logging.Logger;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    Logger logger = Logger.getLogger(UserDaoJDBCImpl.class.getName());
    private final Util util = new Util();
    PreparedStatement ps = null;

    public void createUsersTable() {

        try (Connection conn = util.getConnection()) {
            String sql = "CREATE TABLE IF NOT EXISTS users " +
                    "(Id INT PRIMARY KEY AUTO_INCREMENT," +
                    " Name VARCHAR(20)," +
                    " lastName VARCHAR(20)," +
                    " Age INT);";
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning("Error creating users table: " + e.getMessage());
        }
    }


    public void dropUsersTable() {

        try (Connection conn = util.getConnection()) {
            String sql = "DROP TABLE IF EXISTS users";
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning("Error dropping users table: " + e.getMessage());
        }
    }


    public void saveUser(String name, String lastName, byte age) {

        try (Connection conn = util.getConnection()) {
            String sql = "INSERT INTO USERS (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setInt(3, age);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning("Error saving user: " + e.getMessage());
        }
    }

    public void removeUserById(long id) {

        try (Connection conn = util.getConnection()) {
            String sql = "DELETE FROM users WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, (int) id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning("Error deleting user with id: " + id + e.getMessage());
        }
    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        try (Connection conn = util.getConnection()) {
            Statement statement = conn.createStatement();
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
            logger.warning("Error getting all users: " + e.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {

        try (Connection conn = util.getConnection()) {
            String sql = "DELETE FROM users";
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.warning("Error cleaning users table: " + e.getMessage());
        }
    }
}
