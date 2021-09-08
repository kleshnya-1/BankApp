//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import ru.laptseu.bankapp.dao.BankDAOImpl;
//import ru.laptseu.bankapp.models.Bank;
//
//import java.sql.SQLException;
//import java.util.Calendar;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * AccountDAOImpl Tester.
// *
// * @author <Authors name>
// * @version 1.0
// * @since <pre>����. 1, 2021</pre>
// */
//@DisplayName(" DAO Impl Test")
//class DAOImplTest {
//    BankDAOImpl bankDAO = new BankDAOImpl();
//    Bank b1;
//    Bank b2;
//    Bank b1Fdb;
//    Bank b2Fdb;
//    int n1;
//    int n2;
//
//    @BeforeEach
//    void before() throws Exception {
//        b1 = new Bank();
//        b2 = new Bank();
//        b1.setName("TestBank1" + Calendar.getInstance().getTime());
//        b2.setName("TestBank2");
//        b1.setTransferFeeInPercentForNotNaturalPersons(70);
//        b2.setTransferFeeInPercentForNotNaturalPersons(70);
//        n1 = bankDAO.save(b1);
//        n2 = bankDAO.save(b2);
//    }
//
//    @AfterEach
//    void after() throws Exception {
//
//        bankDAO.delete(b1);
//        bankDAO.delete(b2);
//    }
//
//    /**
//     * Method: read(int key)
//     */
//    @Test
//    @DisplayName("Test Read")
//    void testRead() throws Exception {
//        // TODO: Test goes here...
//    }
//
//    @Test
//    void saveAndRead() throws SQLException {
//        b1Fdb = bankDAO.read(n1);
//        b2Fdb = bankDAO.read(n2);
//        assertEquals(b1, b1Fdb);
//        assertEquals(b2, b2Fdb);
//    }
//
//    @Test
//    void updateAndRead() throws SQLException {
//        b1.setTransferFeeInPercent(50);
//        b2.setTransferFeeInPercent(50);
//        bankDAO.update(b1);
//        bankDAO.update(b2);
//        assertNotEquals(b1, b1Fdb);
//        assertNotEquals(b2, b2Fdb);
//
//        b1Fdb = bankDAO.read(n1);
//        b2Fdb = bankDAO.read(n2);
//        assertEquals(b1, b1Fdb);
//        assertEquals(b2, b2Fdb);
//
//    }
//
//    @Test
//    void testUpdate() {
//    }
//
//    @Test
//    void deleteAndRead() {
//        b1Fdb = bankDAO.read(n1);
//        b2Fdb = bankDAO.read(n2);
//        assertEquals(b1, b1Fdb);
//        assertEquals(b2, b2Fdb);
//        bankDAO.delete(b1);
//        bankDAO.delete(b2);
//        b1Fdb = bankDAO.read(n1);
//        b2Fdb = bankDAO.read(n2);
//        assertNull(b1Fdb);
//        assertNull(b2Fdb);
//    }
//
//    @Test
//    void testDeleteAndReadById() {
//        b1Fdb = bankDAO.read(n1);
//        b2Fdb = bankDAO.read(n2);
//        assertEquals(b1, b1Fdb);
//        assertEquals(b2, b2Fdb);
//        bankDAO.delete(b1);
//        bankDAO.delete(b2Fdb);
//        b1Fdb = bankDAO.read(n1);
//        b2Fdb = bankDAO.read(n2);
//        assertNull(b1Fdb);
//        assertNull(b2Fdb);
//    }
//
//    @Test
//    void getSession() {
//    }
//
//    @Test
//    void read() {
//        b1Fdb = bankDAO.read(n1);
//        b2Fdb = bankDAO.read(n2);
//        assertEquals(b1, b1Fdb);
//        assertEquals(b2, b2Fdb);
//    }
//}
