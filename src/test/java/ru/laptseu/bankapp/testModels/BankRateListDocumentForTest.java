package ru.laptseu.bankapp.testModels;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.laptseu.bankapp.models.BankRateListDocument;

@Getter
@Setter
@Document(collection = "Documents")
public class BankRateListDocumentForTest extends BankRateListDocument {

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BankRateListDocument)) {
            return false;
        }
        BankRateListDocument c = (BankRateListDocument) o;
        return this.getId().equals(c.getId());
    }
}
