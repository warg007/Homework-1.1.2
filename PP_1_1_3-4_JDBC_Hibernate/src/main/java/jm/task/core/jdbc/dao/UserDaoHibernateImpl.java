package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(Id INT PRIMARY KEY AUTO_INCREMENT," +
                " Name VARCHAR(20)," +
                " lastName VARCHAR(20)," +
                " Age INT);";
        Transaction tx = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.getSession().createSQLQuery(sql).addEntity(User.class).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }

    }


    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        Transaction tx = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.getSession().createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction tx = null;
        try (Session session = Util.getSessionFactory().openSession()) {
                tx = session.beginTransaction();
                session.save(new User(name, lastName, age));
                tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }


    @Override
    public void removeUserById(long id) {

        Transaction tx = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(session.get(User.class, id));
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }

    }


    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        Transaction tx = null;
        List<User> users = new ArrayList<>();
        try (Session session = Util.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Query q = session.createSQLQuery(sql).addEntity(User.class);
            users = q.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {

        Transaction tx = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.createSQLQuery("DELETE FROM users").executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }
}
