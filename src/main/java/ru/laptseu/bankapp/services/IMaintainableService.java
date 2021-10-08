package ru.laptseu.bankapp.services;

import org.springframework.data.repository.CrudRepository;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.Entity;
import ru.laptseu.bankapp.models.dto.EntityDto;
import ru.laptseu.bankapp.models.mappers.AccountMapper;
import ru.laptseu.bankapp.models.mappers.MapperInterface;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public interface IMaintainableService
{

//    private final CrudRepository dao = factory.get(entity.getClass());
//    private final MapperInterface mapper = mapperFactory.get(entity.getClass());



    //IMaintainableService<Entity> mapperFactory = ;
//something go wrong. i have a question

    Entity getEntity();
  //  CrudRepository getDao();
  //  MapperInterface getMapper();

    default <T> T save(T obj) {
        return (T) getDao().save(obj);
    }

    default <T> T read(int id) {
        return (T) getDao().findById(id).get();
    }

    default List<T> read() {
        return (List<T>) getDao().findAll();
    }

    default void update(T obj) {
        //update = save
        getDao().save(obj);
    }

    default void delete(int key) {
        getDao().delete(read(key));
    }

    default void delete(T key) {
        getDao().delete(key);
    }

    default List<? extends EntityDto> getAllDto(){
        return read().stream().map(b -> getMapper().map(b)).collect(Collectors.toList());
    };
   default EntityDto getDto(int id ){
       Entity b = serviceFactory.getService().read(id);
       EntityDto dt = getMapper().map(b);
   };
   default Entity createFromDto (EntityDto newb ){
       Account newEntity = MapperInterface.map(newb);
     return newEntity;
   };



}
