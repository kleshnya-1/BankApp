package ru.laptseu.bankapp.models.testModels;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.laptseu.bankapp.models.BankRateListDocument;

@Log4j2
@Getter
@Setter
@Document(collection = "Documents")
public class BankRateListDocumentForTest extends BankRateListDocument {

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BankRateListDocumentForTest)) {
            return false;
        }
        BankRateListDocumentForTest c = (BankRateListDocumentForTest) o;
        return this.getId().equals(c.getId());
    }
}
