package ru.laptseu.bankapp.dao;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public class DaoFactory {
    private Map<Class, IMaintainableDAO> factoryMap = new HashMap<>();

    // TODO: 14.09.2021 ask фабрике конструктор не нужен, но а если вот такой? 
    public DaoFactory(IMaintainableDAO... iMaintainableDAOS) {
        Arrays.stream(iMaintainableDAOS).forEach(iMaintainableDAO -> {
            factoryMap.put(iMaintainableDAO.getEntity().getClass(), iMaintainableDAO);
        });
    }

    public IMaintainableDAO get(Class clazz) {
        return factoryMap.get(clazz);
    }
}
