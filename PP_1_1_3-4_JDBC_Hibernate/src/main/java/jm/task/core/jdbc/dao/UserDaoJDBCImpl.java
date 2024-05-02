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

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }

    Logger logger = Logger.getLogger(UserDaoJDBCImpl.class.getName());
    Connection connection = getConnection();
    PreparedStatement ps = null;


    public void createUsersTable() throws SQLException {

        try {
            String sql = "CREATE TABLE IF NOT EXISTS users " +
                    "(Id INT PRIMARY KEY AUTO_INCREMENT," +
                    " Name VARCHAR(20)," +
                    " lastName VARCHAR(20)," +
                    " Age INT);";
            Connection connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    logger.warning("Failed to close prepared statement");
                    logger.warning(e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.warning("Failed to close prepared statement");
                    logger.warning(e.getMessage());
                }

            }
        }
    }


    public void dropUsersTable() {

        try {
            String sql = "DROP TABLE IF EXISTS users";
            Connection connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    logger.warning("Failed to close prepared statement");
                    logger.warning(e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.warning("Failed to close prepared statement");
                    logger.warning(e.getMessage());
                }

            }
        }

    }


    public void saveUser(String name, String lastName, byte age) {

        try {
            String sql = "INSERT INTO USERS (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";
            Connection connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setInt(3, age);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    logger.warning("Failed to close prepared statement");
                    logger.warning(e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.warning("Failed to close prepared statement");
                    logger.warning(e.getMessage());
                }
            }
        }
    }


    public void removeUserById(long id) {

        try {
            String sql = "DELETE FROM users WHERE id = ?";
            Connection connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, (int) id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    logger.warning("Failed to close prepared statement");
                    logger.warning(e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.warning("Failed to close prepared statement");
                    logger.warning(e.getMessage());
                }
            }
        }
    }


    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        try {
            Connection connection = getConnection();
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
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    logger.warning("Failed to close prepared statement");
                    logger.warning(e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.warning("Failed to close prepared statement");
                    logger.warning(e.getMessage());
                }
            }
        }
        return users;
    }


    public void cleanUsersTable() {

        try {
            String sql = "DELETE FROM users";
            Connection connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    logger.warning("Failed to close prepared statement");
                    logger.warning(e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.warning("Failed to close prepared statement");
                    logger.warning(e.getMessage());
                }
            }
        }
    }
}
