package ru.laptseu.bankapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import ru.laptseu.bankapp.models.Entity;
import ru.laptseu.bankapp.models.dto.EntityDto;
import ru.laptseu.bankapp.repositories.RepositoryFactory;

import java.util.List;


public abstract class AbstractService {
    @Autowired
    private RepositoryFactory repositoryFactory;

    abstract Class getResourceEntityClass();

    abstract List readDto();

    abstract EntityDto readDto(int id);

    public CrudRepository getRepository() {
        return repositoryFactory.get(getResourceEntityClass());
    }

    public <T> T save(T obj) {
        return (T) getRepository().save(obj);
    }

    public <T extends Entity> T read(int id) {
        return (T) getResourceEntityClass().cast(getRepository().findById(id).get());
    }

    public List<? extends Entity> read() {
        return (List<? extends Entity>) getRepository().findAll();
    }

    public <T> void update(T obj) {  //update = save
        getRepository().save(obj);
    }

    public void delete(int key) {
        getRepository().delete(read(key));
    }
}
