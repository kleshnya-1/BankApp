package ru.laptseu.bankapp.services;

import org.springframework.data.repository.CrudRepository;
import ru.laptseu.bankapp.models.Entity;
import ru.laptseu.bankapp.models.dto.EntityDto;
import ru.laptseu.bankapp.models.mappers.MapperFactory;
import ru.laptseu.bankapp.models.mappers.MapperInterface;
import ru.laptseu.bankapp.repositories.RepositoryFactory;

import java.util.List;
import java.util.stream.Collectors;


public interface IMaintainableService {
    Entity getEntity();

    RepositoryFactory getRepositoryFactory();

    ServiceFactory getServiceFactory();

    MapperFactory getMapperFactory();

    default CrudRepository getDao() {
        return getRepositoryFactory().get(getEntity().getClass());
    }

    default IMaintainableService getService() {
        return getServiceFactory().get(getEntity().getClass());
    }

    default MapperInterface getMapper() {
        return getMapperFactory().get(getEntity().getClass());
    }



    default <T> T save(T obj) {
        return (T) getDao().save(obj);
    }

    default <T extends Entity> T read(int id) {
        return (T) getDao().findById(id).get();
    }

    default List<? extends Entity> read() {
        return (List<? extends Entity>) getDao().findAll();
    }

    default <T> void update(T obj) {
        //update = save
        getDao().save(obj);
    }

    default void delete(int key) {
        getDao().delete(read(key));
    }

    default <T> void delete(T key) {
        getDao().delete(key);
    }

    default List readDto() {
        List l = read();
        return (List) read().stream().map(entityDto -> getMapper().map(entityDto)).collect(Collectors.toList());
    }

    default <T extends EntityDto> T readDto(int id) {
        return (T) getMapper().map(read(id));

    }

    default Entity fromDto(EntityDto newb) {
        return getMapper().map(newb);
    }
}
