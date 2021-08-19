package ru.laptseu.bankapp.services;

import lombok.extern.log4j.Log4j2;
import ru.laptseu.bankapp.dao.DaoFactory;
import ru.laptseu.bankapp.dao.IMaintainableDAO;

import java.sql.SQLException;

//
//
// is it good idea to use it?
//
//

@Log4j2
public class PgPersister {

    //? should i add marker-interface for  extending o?
    public int persist(Object o) throws SQLException {
        IMaintainableDAO dao = DaoFactory.get(o.getClass());
        int acId = 0;
        try {
            acId = dao.create(o);
        } catch (SQLException e) {
            log.error(e);
            throw e;
        }
        return acId;
    }

    public boolean update(Object o) throws SQLException {
        IMaintainableDAO dao = DaoFactory.get(o.getClass());
        boolean result;
        try {
            result = dao.update(o);
        } catch (SQLException e) {
            log.error(e);
            throw e;
        }
        return result;
    }
}
