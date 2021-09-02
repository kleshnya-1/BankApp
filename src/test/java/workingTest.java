import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.junit.jupiter.api.Test;
import ru.laptseu.bankapp.dao.*;
import ru.laptseu.bankapp.models.*;
import ru.laptseu.bankapp.services.AccountService;
import ru.laptseu.bankapp.services.BankService;
import ru.laptseu.bankapp.services.ClientService;
import ru.laptseu.bankapp.services.CurrencyRateService;
import ru.laptseu.bankapp.utilities.MongoClientFactoryAndSetUp;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Locale;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.jupiter.api.Assertions.*;

@Log4j2
public class workingTest {


    @SneakyThrows
    @Test
    public void testBankCRUD() {
        BankDAOImpl bankDAO = new BankDAOImpl();
        Bank b1 = new Bank();
        Bank b2 = new Bank();
        b1.setName("TestBank1" + Calendar.getInstance().getTime());
        b2.setName("TestBank2");
        b1.setTransferFeeInPercentForNotNaturalPersons(70);
        b2.setTransferFeeInPercentForNotNaturalPersons(70);
        int n1 = bankDAO.save(b1);
        int n2 = bankDAO.save(b2);
        Bank b1Fdb = bankDAO.read(n1);
        Bank b2Fdb = bankDAO.read(n2);
        assertEquals(b1, b1Fdb);
        assertEquals(b2, b2Fdb);

        b1.setTransferFeeInPercent(50);
        b2.setTransferFeeInPercent(50);
        bankDAO.update(b1);
        bankDAO.update(b2);
        assertNotEquals(b1, b1Fdb);
        assertNotEquals(b2, b2Fdb);

        b1Fdb = bankDAO.read(n1);
        b2Fdb = bankDAO.read(n2);
        assertEquals(b1, b1Fdb);
        assertEquals(b2, b2Fdb);

        bankDAO.delete(b1);
        bankDAO.delete(b2);
        b1Fdb = bankDAO.read(n1);
        b2Fdb = bankDAO.read(n2);
        assertNull(b1Fdb);
        assertNull(b2Fdb);
    }
}
