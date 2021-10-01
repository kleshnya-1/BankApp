package ru.laptseu.bankapp.dao;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.models.BankRateListDocument;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AdapterToDaoFromMongo extends AbstractDao<BankRateListDocument> implements MongoRepository<BankRateListDocument, ObjectId> {
    //adapter demo class. no usages here, just for practice. right realisation?
    private final MongoRepository dao;

    @Override
    public BankRateListDocument create(BankRateListDocument obj) {
        return (BankRateListDocument) dao.save(obj);
    }

    @Override
    public BankRateListDocument read(int id) {
        //todo test it
        BankRateListDocument bankRateListDocumentForExample = new BankRateListDocument();
        bankRateListDocumentForExample.setBankId(id);
        Example<BankRateListDocument> example = Example.of(bankRateListDocumentForExample);
        List<BankRateListDocument> bankRateListDocumentList = dao.findAll(example);
        return bankRateListDocumentList.stream().sorted(Comparator.comparing(o -> o.getDate())).findFirst().get();
    }

    @Override
    public void update(BankRateListDocument obj) {
        save(obj);
    }

    @Override
    public void delete(BankRateListDocument obj) {
        dao.delete(obj);
    }

    //below from here are unsupported
    @Override
    public void deleteAllById(Iterable<? extends ObjectId> objectIds) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends BankRateListDocument> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends BankRateListDocument> S save(S entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends BankRateListDocument> List<S> saveAll(Iterable<S> iterable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<BankRateListDocument> findById(ObjectId objectId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean existsById(ObjectId objectId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<BankRateListDocument> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<BankRateListDocument> findAllById(Iterable<ObjectId> objectIds) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(ObjectId objectId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<BankRateListDocument> findAll(Sort sort) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<BankRateListDocument> findAll(Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends BankRateListDocument> S insert(S s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends BankRateListDocument> List<S> insert(Iterable<S> iterable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends BankRateListDocument> Optional<S> findOne(Example<S> example) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends BankRateListDocument> List<S> findAll(Example<S> example) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends BankRateListDocument> List<S> findAll(Example<S> example, Sort sort) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends BankRateListDocument> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends BankRateListDocument> long count(Example<S> example) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends BankRateListDocument> boolean exists(Example<S> example) {
        throw new UnsupportedOperationException();
    }
}
