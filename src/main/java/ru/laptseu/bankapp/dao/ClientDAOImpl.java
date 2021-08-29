package ru.laptseu.bankapp.dao;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.laptseu.bankapp.utilities.HibernateSessionFactoryUtil;
import ru.laptseu.bankapp.models.Client;

import java.sql.SQLException;

@Log4j2
public class ClientDAOImpl implements IMaintainableDAO<Client> {

    @Override
    public int create(Client obj) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(obj);
        tx1.commit();
        session.close();
        //todo check is works
        return obj.getId();
    }

    @Override
    public Client read(int key) throws SQLException {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Client.class, key);
    }

    @Override
    public void update(Client obj) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        update(obj, session);
        //todo ask. thread-safe?
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Client obj, Session s) throws SQLException {
        Session session = s;
        Transaction tx1 = session.beginTransaction();
        session.update(obj);
    }

    @Override
    public void delete(int key) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(key);
        tx1.commit();
        session.close();
    }

    @Override
    public Session getSession() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession();
    }

//    private final Connection connection = new ConnectionMaker().makeConnection();
//
//    @Override
//    public int create(Client client) throws SQLException {
//        try (Connection connection = new ConnectionMaker().makeConnection()) {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "insert into clients (name, is_natural_person) " +
//                            "values (?,?)");
//            preparedStatement.setString(1, client.getName());
//            preparedStatement.setBoolean(2, client.isNaturalPerson());
//            int affectedRows = preparedStatement.executeUpdate();
//            if (affectedRows == 0) throw new SQLException("Creating failed, no rows affected.");
//            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
//            if (generatedKeys.next()) {
//                client.setId(generatedKeys.getInt(1));
//            } else {
//                throw new SQLException("Creating user failed, no ID obtained.");
//            }
//            generatedKeys.close();
//        } catch
//        (SQLException e) {
//            log.error(e);
//            throw e;
//        }
//        return client.getId();
//    }
//
//    //todo have to be fixed
//    @Override
//    public Client read(int key) {
//        return null;
//    }
//
//    //todo have to be fixed
//    @Override
//    public void update(Client obj) {
//    }
//
//    @Override
//    public void update(Client obj, Connection conn) throws SQLException {
//
//    }
//
//    //todo have to be fixed
//    @Override
//    public void delete(int key) {
//    }
//
//    @Override
//    public Connection getSession() {
//        return new ConnectionMaker().makeConnectionWithFalseAutoCommit();
//    }
}
